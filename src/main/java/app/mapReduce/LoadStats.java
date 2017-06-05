package app.mapReduce;

/**
 * Created by sbr on 5/31/17.
 */
public class LoadStats {
    private int loadrate;
    private boolean shutdown;
    public LoadStats(){
        loadrate = 0;
        shutdown = false;
    }
    synchronized public void increment(){
        loadrate++;
    }
    synchronized public int getLoadrate(){
        int ret = loadrate;
        loadrate = 0;
        return  ret;
    }
    synchronized public void setZero(){
        loadrate = 0;
    }
    synchronized public void setShutdown(){
        shutdown = true;
    }
    synchronized public boolean getShutdown(){
        return shutdown;
    }
}
