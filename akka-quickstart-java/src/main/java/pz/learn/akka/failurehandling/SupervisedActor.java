package pz.learn.akka.failurehandling;

import akka.actor.AbstractActor;

import java.util.Optional;

public class SupervisedActor extends AbstractActor {
    @Override
    public void preStart() {
        System.out.println("supervised actor started");
    }

    @Override
    public void postStop() {
        System.out.println("supervised actor stopped");
    }

    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        System.out.println("supervised actor pre restart");
    }
    

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("failWithEx", f -> {
                    System.out.println("supervised actor fails now");
                    throw new Exception("I failed!");
                })
                .matchEquals("failWitForceRestrat", f -> {
                    System.out.println("supervised actor fails now");
                    throw new ForceRestart("I failed!");
                })
                .matchEquals("failWitForceStop", f -> {
                    System.out.println("supervised actor fails now");
                    throw new ForceStop("I failed!");
                })
                .build();
    } 
}