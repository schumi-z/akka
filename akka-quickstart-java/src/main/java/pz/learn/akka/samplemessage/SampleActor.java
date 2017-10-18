package pz.learn.akka.samplemessage;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class SampleActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return new ReceiveBuilder().match(SampleMessage.class, this::onSampleMessage).build();
    }

    private void onSampleMessage(SampleMessage message) {
            System.out.println(message.content);
    }
}
