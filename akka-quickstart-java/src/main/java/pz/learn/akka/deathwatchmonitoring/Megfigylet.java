package pz.learn.akka.deathwatchmonitoring;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

public class Megfigylet extends AbstractActor {


    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(Megfigylet.class);
    }

    @Override
    public Receive createReceive() {
        return new ReceiveBuilder()
                .match(Terminated.class, this::onTerminate)
                .matchEquals("stop", s -> {
                    getContext().stop(getSelf());
                })
                .build();
    }

    private void onTerminate(Terminated actor1) {
        log.info("Megfigylet terminated");
    }
}
