package searchpaths;

/**
 *
 * @author Nands
 */

/*This class creates a timer which calculates the time spent between two times*/
public class Timer {
    long startTime = 0;                             //started time of the function
    long stopTime = 0;                              //finished time of the function
    boolean running = false;                        //function is running

    /* Start the timer */
    public void start() {
        this.startTime = System.nanoTime();
        this.running = true;                        //function has started running
    }

    /* Stop the timer*/
    public void stop() {
        this.stopTime = System.nanoTime();
        this.running = false;                       //function has finished running
    }

    /* calculates elaspsed time  */
    public long getElapsedTime() {
        long elapsed;                               //elapsed time
        
        //if function is still running
        if (running) {
            elapsed = (System.nanoTime() - startTime);
        }
        //if function is stopped
        else {
            elapsed = (stopTime - startTime);       //calculates elapsed time
        }
        return elapsed;
    }    
}
