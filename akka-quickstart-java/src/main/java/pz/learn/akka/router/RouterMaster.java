package pz.learn.akka.router;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;


public class RouterMaster extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


    Router router;
    public RouterMaster(){
        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 5; i++) {
            ActorRef r = getContext().actorOf(Props.create(Worker.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Work.class, message -> {
                    router.route(message, getSender());
                })
                .matchEquals("killOneRoutee", s->{
                    router.routees().head().send(PoisonPill.getInstance(), self());
                })
                .match(Terminated.class, message -> {
                    ActorRef actor = message.actor();
                    log.info("Actor terminated: "+actor.path());
                    router = router.removeRoutee(actor);
                    ActorRef r = getContext().actorOf(Props.create(Worker.class));
                    getContext().watch(r);
                    router = router.addRoutee(new ActorRefRoutee(r));
                    log.info("Actor created: "+r.path());
                })
                .build();
    }

    private void killoneRoutee(Object o) {

    }
}