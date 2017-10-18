package pz.learn.akka.sample;

import akka.actor.ActorRef;
import akka.testkit.javadsl.TestKit;
import pz.learn.akka.iot.temperature.AbstractAkkaTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AkkaQuickstartTest extends AbstractAkkaTest{

    @Test
    public void testGreeterActorSendingOfGreeting() {
        final TestKit testProbe = new TestKit(system);
        final ActorRef helloGreeter = system.actorOf(Greeter.props("Hello", testProbe.getRef()));
        helloGreeter.tell(new Greeter.WhoToGreet("Akka"), ActorRef.noSender());
        helloGreeter.tell(new Greeter.Greet(), ActorRef.noSender());
        Printer.Greeting greeting = testProbe.expectMsgClass(Printer.Greeting.class);
        assertEquals("Hello, Akka", greeting.message);
    }
}
