package pz.learn.akka.failurehandling;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

//default stop and restart
public class FauilHandlingSupervisingMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("FauilHandlingSupervisingMain");
        ActorRef supervisingActor = system.actorOf(Props.create(SupervisorActor.class), "supervising-actor");
        supervisingActor.tell("failChildWithException", ActorRef.noSender());
        //supervisingActor.tell("failChildWithForceRestart", ActorRef.noSender());
        //supervisingActor.tell("failChildWithForceStop", ActorRef.noSender());

    }
}
