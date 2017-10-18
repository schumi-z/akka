package pz.learn.akka.lifecylehooks;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Parent extends AbstractActor {
    @Override
    public void preStart() {
        System.out.println("Parent started");
        System.out.println(getSelf().path());

        getContext().actorOf(Props.create(Child.class), "child");
    }

    @Override
    public void postStop() {
        System.out.println("Parent stopped");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("stop", s -> {
                    getContext().stop(getSelf());
                })
                .build();
    }
}
