package pz.learn.akka.persistence;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Main {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("IPSDEMO");
        ActorRef counterActor = system.actorOf(Props.create(Counter.class));

        counterActor.tell(new Increment(), ActorRef.noSender());
        counterActor.tell(new Increment(), ActorRef.noSender());
        counterActor.tell(new Increment(), ActorRef.noSender());
        counterActor.tell(new Decrement(), ActorRef.noSender());

        counterActor.tell("print", ActorRef.noSender());


    }
}
