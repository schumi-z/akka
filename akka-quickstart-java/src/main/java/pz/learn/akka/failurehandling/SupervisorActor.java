package pz.learn.akka.failurehandling;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;

import static akka.actor.SupervisorStrategy.*;


public class SupervisorActor extends AbstractActor {
    ActorRef child = getContext().actorOf(Props.create(SupervisedActor.class), "supervised-actor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("failChildWithException", f -> {
                    child.tell("failWithEx", getSelf());
                })
                .matchEquals("failChildWithForceRestart", f -> {
                    child.tell("failWitForceRestrat", getSelf());
                })
                .matchEquals("failChildWithForceStop", f -> {
                    child.tell("failWitForceStop", getSelf());
                })
                .match(EchoRequest.class, f -> {
                   getSender().tell(new EchoResponse("Echo Response content"), getSelf());
                })
                .build();
    }

    /*@Override
    public SupervisorStrategy supervisorStrategy() {
        //AllForOneStrategy
        return new OneForOneStrategy(true,
                DeciderBuilder.match(ForceRestart.class, e -> restart())
                        .match(ForceStop.class, e -> stop())
                        .matchAny(e -> resume()).build());
    }   */
}