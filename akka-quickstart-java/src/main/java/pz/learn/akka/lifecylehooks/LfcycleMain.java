package pz.learn.akka.lifecylehooks;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class LfcycleMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actorLfcycleHookSystem");
        ActorRef parentActor = system.actorOf(Props.create(Parent.class), "Parent");
        parentActor.tell("stop", ActorRef.noSender());
    }
}
