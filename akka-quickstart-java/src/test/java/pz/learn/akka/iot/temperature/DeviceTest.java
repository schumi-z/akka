package pz.learn.akka.iot.temperature;

import akka.actor.ActorRef;
import akka.testkit.TestKit;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class DeviceTest extends AbstractAkkaTest {

    @Test
    public void testReplyWithEmptyReadingIfNoTemperatureIsKnown() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));
        deviceActor.tell(new Device.ReadTemperature(42L), probe.testActor());//megszolitjuk a device actort hogy adjun nekunk homerseklete
        Device.RespondTemperature response = probe.expectMsgClass(Device.RespondTemperature.class);
        //visszaadja
        assertEquals(42L, response.requestId);
        assertEquals(Optional.empty(), response.value);
        assertEquals(probe.lastSender(), deviceActor);//egyben o lesz a lastSnder mert o hivja a tel metodust a getSenderen a self()-fel, amyleik self jelen esetben o
    }

    @Test
    public void testReplyWithLatestTemperatureReading() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));

        deviceActor.tell(new Device.RecordTemperature(1L, 24.0), probe.testActor());
        assertEquals(1L, probe.expectMsgClass(Device.TemperatureRecorded.class).requestId);

        deviceActor.tell(new Device.ReadTemperature(2L), probe.testActor());
        Device.RespondTemperature response1 = probe.expectMsgClass(Device.RespondTemperature.class);
        assertEquals(2L, response1.requestId);
        assertEquals(Optional.of(24.0), response1.value);

        deviceActor.tell(new Device.RecordTemperature(3L, 55.0), probe.testActor());
        assertEquals(3L, probe.expectMsgClass(Device.TemperatureRecorded.class).requestId);

        deviceActor.tell(new Device.ReadTemperature(4L), probe.testActor());
        Device.RespondTemperature response2 = probe.expectMsgClass(Device.RespondTemperature.class);
        assertEquals(4L, response2.requestId);
        assertEquals(Optional.of(55.0), response2.value);
    }

    @Test
    public void testReplyToRegistrationRequests() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));

        deviceActor.tell(new DeviceManager.RequestTrackDevice("group", "device"), probe.testActor());
        probe.expectMsgClass(DeviceManager.DeviceRegistered.class);
        assertEquals(deviceActor, probe.lastSender());
    }

    @Test
    public void testIgnoreWrongRegistrationRequests() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));

        deviceActor.tell(new DeviceManager.RequestTrackDevice("wrongGroup", "device"), probe.testActor());
        probe.expectNoMsg();

        deviceActor.tell(new DeviceManager.RequestTrackDevice("group", "wrongDevice"), probe.testActor());
        probe.expectNoMsg();
    }


}