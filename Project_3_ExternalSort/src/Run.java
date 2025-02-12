/**
 * @author Randy Fu
 * @version 10/26
 */
public class Run {
    private long start;
    private long end;
    private long current;
    private boolean isDone;

    /**
     * Constructor
     * 
     * @param start
     *            Starting byte position of run in file
     * @param end
     *            Ending position of run in file
     */
    public Run(long start, long end) {
        this.start = start;
        this.end = end;
        this.current = start;
        isDone = false;
    }


    /**
     * Get starting byte position
     * 
     * @return
     *         start
     */
    public long getStart() {
        return start;
    }


    /**
     * Update which byte the run is on
     * 
     * @param newPos
     *            new position in file
     */
    public void updatePosition(long newPos) {
        current = newPos;
    }


    /**
     * Check if run has reached the end
     * 
     * @return
     *         true if current position is end of run
     */
    public boolean reachedEnd() {
        return current == end;
    }


    /**
     * Get current byte
     * 
     * @return
     *         current position in file
     */
    public long getCurrent() {
        return current;
    }


    /**
     * Get ending byte position
     * 
     * @return
     *         end
     */
    public long getEnd() {
        return end;
    }


    /**
     * Mark run as exhausted
     */
    public void markDone() {
        isDone = true;
    }


    /**
     * See if run is done
     * 
     * @return
     *         is run exhausted
     */
    public boolean isDone() {
        return isDone;
    }
}
