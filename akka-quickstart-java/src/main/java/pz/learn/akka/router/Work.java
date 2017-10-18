package pz.learn.akka.router;

import java.io.Serializable;


public final class Work implements Serializable {
    private static final long serialVersionUID = 1L;
    public final String payload;
    public Work(String payload) {
        this.payload = payload;
    }
}
