package pz.learn.akka.failurehandling;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.Test;
import pz.learn.akka.iot.temperature.AbstractAkkaTest;

import static org.junit.Assert.assertEquals;

public class SupervisorActorTest extends AbstractAkkaTest {

    @Test
    public void echoTest() {
        final TestKit testProbe = new TestKit(system);
        final ActorRef supervisor = system.actorOf(Props.create(SupervisorActor.class), "supervising-actor");
        supervisor.tell(new EchoRequest(), testProbe.getRef());
        EchoResponse echoResponse = testProbe.expectMsgClass(EchoResponse.class);
        assertEquals("Echo Response content", echoResponse.getMsg());
        assertEquals(testProbe.getLastSender(), supervisor);
    }

}