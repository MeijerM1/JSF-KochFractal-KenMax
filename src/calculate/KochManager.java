package calculate;

import javafx.application.Application;
import jsf31kochfractalfx.JSF31KochFractalFX;
import timeutil.TimeStamp;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Max Meijer
 * Creation date 15/03/2017.
 */
public class KochManager implements Observer {

    private KochFractal kf;
    private JSF31KochFractalFX application;
    private ArrayList<Edge> edges;

    public KochManager(JSF31KochFractalFX application) {
        kf = new KochFractal();
        kf.addObserver(this);
        this.application = application;
        edges = new ArrayList<>();
    }

    public void changeLevel(int nxt) {
        kf.setLevel(nxt);
        drawEdges();
    }

    public void drawEdges() {
        TimeStamp ts = new TimeStamp();
        ts.setBegin("Start draw edges");
        edges.clear();

        application.clearKochPanel();
        kf.generateBottomEdge();
        kf.generateRightEdge();
        kf.generateLeftEdge();

        application.setTextNrEdges(String.valueOf(kf.getNrOfEdges()));
        for(Edge e: edges) {
            application.drawEdge(e);
        }
        ts.setEnd("Done drawing");
        System.out.println(ts);
    }

    @Override
    public void update(Observable o, Object arg) {
        edges.add((Edge) arg);
    }
}
