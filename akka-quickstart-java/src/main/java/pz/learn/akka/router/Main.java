package pz.learn.akka.router;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Main {

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("IPSDEMO");
        ActorRef master = system.actorOf(Props.create(RouterMaster.class));


        master.tell(new Work("feladat1"), ActorRef.noSender());
        master.tell(new Work("feladat2"), ActorRef.noSender());
        master.tell(new Work("feladat3"), ActorRef.noSender());
        master.tell(new Work("feladat4"), ActorRef.noSender());
        master.tell(new Work("feladat5"), ActorRef.noSender());


        master.tell(new Work("feladat6"), ActorRef.noSender());
        master.tell(new Work("feladat7"), ActorRef.noSender());
        master.tell(new Work("feladat8"), ActorRef.noSender());
        master.tell(new Work("feladat9"), ActorRef.noSender());
        master.tell(new Work("feladat10"), ActorRef.noSender());

        master.tell("killOneRoutee", ActorRef.noSender());
    }
}
