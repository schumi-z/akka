package pz.learn.akka.iot.temperature;

import akka.actor.ActorSystem;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractAkkaTest {

    protected static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        akka.testkit.javadsl.TestKit.shutdownActorSystem(system);
        system = null;
    }
}
