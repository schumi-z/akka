package pz.learn.akka.deathwatchmonitoring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;

//A megfigyelo is kap egy Terinated(ActorRef act) messaget
public class Main {

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("IPSDEMO");
        //ok nincsenek szulo gyerek viszonyban
        ActorRef megfigyelt = system.actorOf(Megfigylet.props(), "Megfigylet");
        ActorRef megfigyelo = system.actorOf(Props.create(Megfigyelo.class), "Megfigyelo");

        megfigyelo.tell(new Regisztral(megfigyelt), ActorRef.noSender());
        megfigyelt.tell(PoisonPill.getInstance(), ActorRef.noSender());

    }

}
