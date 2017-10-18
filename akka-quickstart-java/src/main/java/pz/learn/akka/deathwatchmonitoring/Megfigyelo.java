package pz.learn.akka.deathwatchmonitoring;

import akka.actor.AbstractActor;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

public class Megfigyelo extends AbstractActor{


    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Megfigyelo() {
    }

    @Override
    public Receive createReceive() {
        return new ReceiveBuilder()
                .match(Terminated.class, this::onTerminate)
                .match(Regisztral.class, p -> {
                    getContext().watch(p.megfigyelt);
                })
                .build();
    }


    private void onTerminate(Terminated actor1) {

        log.info("Megfigyelo terminalasa indukalva a " + actor1.getActor().path()+" altal ");
        log.info("Megfigyelo terminated");
    }
}
