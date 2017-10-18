package pz.learn.akka.router;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;


public class Worker extends AbstractActor{

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return new ReceiveBuilder().match(Work.class, this::work).build();
    }

    public void work(Work w){
        log.info(String.format("Working %s worker on message: %s", getSelf(), w.payload));
    }
}
