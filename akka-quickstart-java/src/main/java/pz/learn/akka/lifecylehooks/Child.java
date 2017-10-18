package pz.learn.akka.lifecylehooks;


import akka.actor.AbstractActor;

public class Child extends AbstractActor {
    @Override
    public void preStart() {
        System.out.println("Child started");
        System.out.println(getSelf().path());
    }

    @Override
    public void postStop() {
        System.out.println("Child stopped");
    }

    // Actor.emptyBehavior is a useful placeholder when we don't
    // want to handle any messages in the actor.
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }
}