package pz.learn.akka.deathwatchmonitoring;

import akka.actor.ActorRef;


public class Regisztral {
    public final ActorRef megfigyelt;

    public Regisztral(ActorRef megfigyelt) {
        this.megfigyelt = megfigyelt;
    }
}
