package pz.learn.akka.persistence;

public class Event {

    public Operation o   ;

    public Event(Operation o){
        this.o = o;
    }
}
