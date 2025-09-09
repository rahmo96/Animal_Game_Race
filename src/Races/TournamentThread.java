 /** Rahamim Tadela  208189621 */
package Races;

import Graphics.RaceDetails;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * the tournament thread class
 */
public class TournamentThread implements Runnable {
    private Scores scores;
    private AtomicBoolean startSignal;
    private int groups;
    private RaceDetails detailsPanel;

    /**
     * Constructor
     * @param sc Scores
     * @param ss StartSignal
     * @param gp number of groups
     * @param dets Race Deatils panel
     */
    public TournamentThread(Scores sc,AtomicBoolean ss,int gp,RaceDetails dets){
        scores=sc;
        startSignal=ss;
        groups=gp;
        detailsPanel=dets;
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
        int teamsFinished=0;
        synchronized (startSignal){
            startSignal.set(true);
            startSignal.notifyAll();
        }
        while (true){
            synchronized (scores){
                try {
                    scores.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            teamsFinished++;
            detailsPanel.updateTable(scores.getAll());
            System.out.println("Scores updated");
            if(teamsFinished==groups){
                break;
            }
        }
    }
}
