package pz.learn.akka.persistence;

import akka.japi.pf.ReceiveBuilder;
import akka.persistence.AbstractPersistentActor;
import akka.persistence.SnapshotOffer;

public class Counter extends AbstractPersistentActor {

    public CounterState state = new CounterState(0);

    @Override
    public Receive createReceiveRecover() {
        return new ReceiveBuilder()
                .match(Event.class, this::handleEvent)
                .match(SnapshotOffer.class, ss -> state = (CounterState) ss.snapshot())
                .build();
    }

    @Override
    public Receive createReceive() {
        return new ReceiveBuilder()
                .match(Command.class, p ->{
                    persist(new Event(p.o), (Event e) ->{
                        state.apply(e);
                        getContext().getSystem().eventStream().publish(e);

                        saveSnapshot(state.copy());
                    });
                })
                .matchEquals("print", p->{
                        System.out.println("CounterState: "+this.state);
                })
                .build();
    }

    private  void handleEvent(Event event) {
            state.apply(event);

    }

    @Override
    public String persistenceId() {
        return "counter-sample";
    }
}
