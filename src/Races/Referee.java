
package Races;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * the referee thread
 */
public class Referee implements Runnable {
    private AtomicBoolean finisFlag;
    private String groupName;
    private Scores scores;

    /**
     * Constructor
     * @param ff Finish flag
     * @param nm Team name
     * @param sc Scores
     */
    public Referee(AtomicBoolean ff,String nm,Scores sc){
        finisFlag=ff;
        groupName=nm;
        scores=sc;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        synchronized (finisFlag){
            if(!finisFlag.get()) {
                try {
                    finisFlag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        scores.add(groupName);
        synchronized (scores){
            scores.notify();
        }
    }
}
