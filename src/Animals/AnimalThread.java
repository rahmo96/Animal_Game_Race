
package Animals;

import Graphics.CompetitionPanel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Adapter for the animal class
 */
public class AnimalThread extends Thread implements Runnable {
    private static final int SLEEPTIME=50;
    private Animal participant;
    private double neededDistance;
    private double distanceTraveled;
    private AtomicBoolean startFlag;
    private AtomicBoolean finishFlag;

    /**
     * Constructor
     * @param ani animal to run
     * @param nD Distance
     * @param sf Start flag
     * @param ff Finish flag
     */
    public AnimalThread(Animal ani,double nD,AtomicBoolean sf,AtomicBoolean ff){
        participant =ani;
        neededDistance=nD;
        distanceTraveled=0;
        startFlag=sf;
        finishFlag=ff;
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
        //Start racing
        double moved=0;
        while(true){
            synchronized (startFlag) {
                if (!startFlag.get()) {
                    try {
                        System.out.println("Animal: " + participant.getName() + " Waiting at start line");
                        startFlag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Starting to run");
                    }
                }
            }
            synchronized (participant){
                    moved=this.participant.moveInDirection();
                    CompetitionPanel.getInstance().repaint();
                    this.distanceTraveled+=moved;
                    System.out.println("Animal: "+ participant.getName()+" Moved "+distanceTraveled );

                    if(!participant.canRun.get()){
                        System.out.println("Animal: "+ participant.getName()+" ran out of energy and stopped");
                        break;
                    }
                    if(distanceTraveled>=neededDistance) {
                        synchronized (finishFlag) {
                            finishFlag.set(true);
                            finishFlag.notifyAll();
                        }
                        System.out.println("Finish run");
                        break;
                    }
                }
                try {
                    sleep(SLEEPTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }
    }



}
