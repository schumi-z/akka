package pz.learn.akka.iot.temperature;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

//the root of the application actor hierarchy
public class IOTSupervisor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(IOTSupervisor.class);
    }

    @Override
    public void preStart() {
        log.info("IoT Application started");
    }

    @Override
    public void postStop() {
        log.info("IoT Application stopped");
    }

    // No need to handle any messages
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }

}