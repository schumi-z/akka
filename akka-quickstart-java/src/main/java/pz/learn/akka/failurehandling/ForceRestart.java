package pz.learn.akka.failurehandling;

public class ForceRestart extends Exception{

    public ForceRestart(String msg){
        super(msg);
    }
}
