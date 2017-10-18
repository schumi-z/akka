package pz.learn.akka.persistence;

public class Command {

    public Operation o   ;

    public Command(Operation o){
        this.o = o;
    }
}
