package pz.learn.akka.failurehandling;

/**
 * Created by pistarz on 2017.10.18..
 */
public class ForceStop extends Exception{

    public ForceStop(String msg){
        super(msg);
    }
}
