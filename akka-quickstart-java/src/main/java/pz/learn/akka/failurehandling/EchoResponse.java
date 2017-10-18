package pz.learn.akka.failurehandling;

public class EchoResponse {

    private String msg;

    public EchoResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
