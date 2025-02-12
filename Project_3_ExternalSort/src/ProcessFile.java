import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.io.File;
import java.io.IOException;

/**
 * @author Randy Fu
 * @version 10/26
 * 
 */
public class ProcessFile {
    private static final int HEAPSIZE = 8 * ByteFile.RECORDS_PER_BLOCK;
    private LinkedList<Run> runs;
    private Record[] heapArray;
    private Record[] inputBuffer; // maybe change to bytes to make faster
    private int mergeRecordsRead = 0;

    /**
     * Constructor
     */
    public ProcessFile() {
        runs = new LinkedList<Run>();
        heapArray = new Record[HEAPSIZE];
        inputBuffer = new Record[ByteFile.RECORDS_PER_BLOCK];
    }


    /**
     * Replacement selection
     * 
     * @param fileName
     *            input file
     * @param runFile
     *            output file
     * @throws IOException
     */
    public void createRuns(File fileName, File runFile) throws IOException {
        RandomAccessFile input = new RandomAccessFile(fileName, "r");
        input.seek(0);
        RandomAccessFile output = new RandomAccessFile(runFile, "rw");
        output.setLength(0);

        long runStartPosition = output.getFilePointer();
        Record[] outputBuffer = new Record[ByteFile.RECORDS_PER_BLOCK];
        boolean fileRead = false;
        // Create heap for replacementSelection
        MinHeap<Record> heap = new MinHeap<Record>(heapArray, 0, HEAPSIZE);
        // Temporary storage for creating runs
        LinkedList<Record> tempStore = new LinkedList<Record>();
        // fill heap
        for (int i = 0; i < 8; i++) {
            readBlock(input, input.getFilePointer(), input.length(), 0);
            for (int j = 0; j < inputBuffer.length; j++) {
                heap.insert(inputBuffer[j]);
            }
        }
        heap.buildHeap();
        int recordsRead = readBlock(input, input.getFilePointer(), input
            .length(), 0);
        if (recordsRead == 0) {
            fileRead = true;
        }
        int currentRunSize = 0;
        int inputIndex = 0;
        int outputIndex = 0;

        // Overall loop to make sure that all bytes are read
        while (heap.heapSize() > 0 || tempStore.getCount() > 0) {
            // Loop to create runs
            while (heap.heapSize() > 0) {
                Record minRecord = heap.removeMin();
                outputBuffer[outputIndex] = minRecord;
                currentRunSize += ByteFile.BYTES_PER_RECORD;
                if (!fileRead) {
                    if (inputBuffer[inputIndex].compareTo(minRecord) < 0) {
                        tempStore.insertNode(new LLNode<Record>(
                            inputBuffer[inputIndex]));
                    }
                    else {
                        heap.insert(inputBuffer[inputIndex]);
                    }
                    inputIndex++;
                    // Reload inputBuffer if exhausted
                    if (inputIndex == inputBuffer.length) {
                        recordsRead = readBlock(input, input.getFilePointer(),
                            input.length(), 0);
                        inputIndex = 0;
                        if (recordsRead == 0) {
                            fileRead = true;
                        }
                    }
                }
                outputIndex++;
                if (outputIndex == outputBuffer.length) {
                    writeBlock(output, outputBuffer, outputIndex);
                    outputIndex = 0;
                }
            }
            if (outputIndex > 0) {
                writeBlock(output, outputBuffer, outputIndex);
                outputIndex = 0;
            }
            LLNode<Run> run = new LLNode<Run>(new Run(runStartPosition,
                currentRunSize + runStartPosition));
            runs.insertNode(run);
            runStartPosition = output.getFilePointer();
            currentRunSize = 0;
            if (tempStore.getCount() > 0) {
                LLNode<Record> record = tempStore.getNode();
                while (record != null) {
                    heap.insert(record.getData());
                    record = record.getNext();
                }
            }
            heap.buildHeap();
            tempStore.clear();
        }
        input.close();
        output.close();
    }


    // Read block into inputBuffer
    private int readBlock(RandomAccessFile input, long start, long end, int run)
        throws IOException {
        if (start >= end) {
            return 0;
        }
        int recordCount = 0;
        byte[] byteBuffer = new byte[ByteFile.BYTES_PER_BLOCK];
        ByteBuffer bb = ByteBuffer.wrap(byteBuffer);
        input.seek(start);
        // Read either one block of data or any remaining blocks
        int bytesToRead = (int)Math.min(ByteFile.BYTES_PER_BLOCK, end - start);
        int bytesRead = input.read(byteBuffer, 0, bytesToRead);
        if (bytesRead <= 0) {
            return 0;
        }
        bb.limit(bytesRead);
        int recordsRead = bytesRead / ByteFile.BYTES_PER_RECORD;
        while (bb.remaining() >= ByteFile.BYTES_PER_RECORD) {
            long id = bb.getLong();
            double key = bb.getDouble();
            boolean isLastRecord = (recordCount == recordsRead - 1);
            inputBuffer[recordCount] = new Record(id, key, run, isLastRecord);
            recordCount++;
        }
        return recordCount;
    }


    private void writeBlock(
        RandomAccessFile output,
        Record[] buffer,
        int length)
        throws IOException {
        ByteBuffer blockBuffer = ByteBuffer.allocate(ByteFile.BYTES_PER_BLOCK);
        for (int i = 0; i < length; i++) {
            blockBuffer.putLong(buffer[i].getID()).putDouble(buffer[i]
                .getKey());
        }
        blockBuffer.flip();
        output.getChannel().write(blockBuffer);
    }


    // Track location in file
    private void incrementPosition(Run run) {
        run.updatePosition(run.getCurrent() + ByteFile.BYTES_PER_RECORD);
        if (run.getCurrent() == run.getEnd()) {
            run.markDone();
        }
    }


    /**
     * Merge multiple times
     * 
     * @param runFile
     *            temporary file
     * @param outputFile
     *            final output file
     * @param inputFile
     *            inputfile
     * @throws IOException
     */
    public void loopMerges(File runFile, File outputFile, File inputFile)
        throws IOException {
        int remainingRuns = runs.getCount();
        // Loop until there is at most 8 runs
        while (remainingRuns > 8) {
            mergeRuns(runFile, inputFile);
            remainingRuns = runs.getCount();
            overwriteFile(inputFile, runFile);
        }
        mergeRuns(runFile, inputFile);
    }


    /**
     * Helper method to overwrite runFile with data from inputFile
     * 
     * @param inputFile
     *            The file to be overwritten
     * @param runFile
     *            The file from which data is copied
     * @throws IOException
     *             If an error occurs during file operations
     */
    private void overwriteFile(File inputFile, File runFile)
        throws IOException {
        try (RandomAccessFile input = new RandomAccessFile(inputFile, "rw");
            RandomAccessFile run = new RandomAccessFile(runFile, "r")) {

            // Set the file length of the inputFile to 0 before writing new data
            input.setLength(0);

            byte[] buffer = new byte[512]; // You can adjust the buffer size as
                                           // needed
            int bytesRead;

            // Read from runFile and write to inputFile
            while ((bytesRead = run.read(buffer)) != -1) {
                input.write(buffer, 0, bytesRead);
            }
        }
    }


    /**
     * Merge runs
     * 
     * @param inputFile
     *            input file
     * @param outputFile
     *            output file
     * @throws IOException
     */
    public void mergeRuns(File inputFile, File outputFile) throws IOException {
        RandomAccessFile input = new RandomAccessFile(inputFile, "r");
        input.seek(0);
        RandomAccessFile output = new RandomAccessFile(outputFile, "rw");
        output.setLength(0);
        MinHeap<Record> heap = new MinHeap<Record>(heapArray, 0, HEAPSIZE);
        int numOfRuns = Math.min(runs.getCount(), 8);
        Run[] runsArray = new Run[numOfRuns];
        inputBuffer = new Record[ByteFile.RECORDS_PER_BLOCK];
        Record[] outputBuffer = new Record[ByteFile.RECORDS_PER_BLOCK];
        LLNode<Run> head = runs.getNode();
        // Add all run data into array for easy access
        int runIndex = 0;
        while (head != null && runIndex < 8) {
            Run run = head.getData();
            runsArray[runIndex] = run;
            head = head.getNext();
            runIndex++;
            runs.removeNode();
        }

        LLNode<Run> mergedRuns = new LLNode<Run>(new Run(runsArray[0]
            .getStart(), runsArray[numOfRuns - 1].getEnd()));
        runs.insertNode(mergedRuns);
        runIndex = 0;
        int inputIndex = 0;
        // Initial fill of heap
        while (heap.heapSize() < HEAPSIZE) {
            if (!runsArray[runIndex].isDone()) {
                mergeRecordsRead = readBlock(input, runsArray[runIndex]
                    .getCurrent(), runsArray[runIndex].getEnd(), runIndex);
                for (inputIndex = 0; inputIndex < mergeRecordsRead && heap
                    .heapSize() < HEAPSIZE; inputIndex++) {
                    heap.insert(inputBuffer[inputIndex]);
                    incrementPosition(runsArray[runIndex]);
                }
            }
            runIndex++;
            if (runIndex == numOfRuns || runIndex == 8) {
                runIndex = 0;
            }
        }
        heap.buildHeap();
        int outputIndex = 0;
        while (heap.heapSize() > 0) {
            Record minRecord = heap.removeMin();
            outputBuffer[outputIndex] = minRecord;
            outputIndex++;
            if (outputIndex == ByteFile.RECORDS_PER_BLOCK) {
                writeBlock(output, outputBuffer, ByteFile.RECORDS_PER_BLOCK);
                outputIndex = 0;
            }

            boolean isLast = minRecord.isLast();
            int recordRun = minRecord.getRun();
            if (isLast) {
                if (!runsArray[recordRun].isDone()) {
                    // If inputBuffer has records from another run
                    if (recordRun != inputBuffer[0].getRun()) {
                        inputBuffer = new Record[ByteFile.RECORDS_PER_BLOCK];
                    }
                    inputIndex = refillHeap(input, heap, runsArray[recordRun]
                        .getCurrent(), runsArray[recordRun].getEnd(), recordRun,
                        inputIndex, runsArray);
                }
                else {
                    int nextRun = (recordRun + 1) % numOfRuns;
                    int startIndex = nextRun;
                    while (runsArray[nextRun].isDone()) {
                        nextRun = (nextRun + 1) % numOfRuns;
                        if (nextRun == startIndex) {
                            break;
                        }
                    }
                    inputIndex = refillHeap(input, heap, runsArray[nextRun]
                        .getCurrent(), runsArray[nextRun].getEnd(), nextRun,
                        inputIndex, runsArray);
                }
            }
            else {
                if (!runsArray[recordRun].isDone()) {
                    inputIndex = refillHeap(input, heap, runsArray[recordRun]
                        .getCurrent(), runsArray[recordRun].getEnd(), recordRun,
                        inputIndex, runsArray);
                }
                else {
                    int nextRun = (recordRun + 1) % numOfRuns;
                    int startIndex = nextRun;
                    while (runsArray[nextRun].isDone()) {
                        nextRun = (nextRun + 1) % numOfRuns;
                        if (nextRun == startIndex) {
                            break;
                        }
                    }
                    inputIndex = refillHeap(input, heap, runsArray[nextRun]
                        .getCurrent(), runsArray[nextRun].getEnd(), nextRun,
                        inputIndex, runsArray);
                }
            }
        }
        if (outputIndex > 0) {
            writeBlock(output, outputBuffer, outputIndex);
        }

        // Write runs not used to output
        if (runs.getCount() > 1) {
            input.seek(runs.getNode().getData().getStart());
            byte[] buffer = new byte[ByteFile.BYTES_PER_BLOCK];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        input.close();
        output.close();
    }


    private int refillHeap(
        RandomAccessFile input,
        MinHeap<Record> heap,
        long start,
        long end,
        int runIndex,
        int inputIndex,
        Run[] runsArray)
        throws IOException {
        // Fill inputBuffer if empty or used up
        if (inputIndex >= mergeRecordsRead || inputBuffer[0] == null) {
            mergeRecordsRead = readBlock(input, start, end, runIndex);
            if (mergeRecordsRead == 0) {
                return 0;
            }
            inputIndex = 0;
        }
        while (inputIndex < mergeRecordsRead && heap.heapSize() < HEAPSIZE) {
            Record record = inputBuffer[inputIndex];
            if (record == null) {
                break;
            }
            incrementPosition(runsArray[record.getRun()]);
            heap.insert(record);
            inputIndex++;
        }
        return inputIndex;
    }


    /**
     * Print first record in each block
     * 
     * @param sortedFile
     *            Sorted file
     * @throws IOException
     */
    public void printRecords(File sortedFile) throws IOException {
        RandomAccessFile input = new RandomAccessFile(sortedFile, "r");
        inputBuffer = new Record[ByteFile.RECORDS_PER_BLOCK];
        StringBuilder sb = new StringBuilder();
        int printCount = 0;
        while (input.getFilePointer() < input.length()) {
            int recordsRead = readBlock(input, input.getFilePointer(), input
                .length(), 0);
            if (recordsRead > 0) {
                sb.append(inputBuffer[0].getID()).append(" ").append(
                    inputBuffer[0].getKey()).append(" ");
                printCount++;
                if (printCount == 5) {
                    System.out.println(sb.toString().trim());
                    sb.setLength(0);
                    printCount = 0;
                }
            }
        }
        if (sb.length() > 0) {
            System.out.println(sb.toString().trim());
        }
    }
}
