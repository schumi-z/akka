package pz.learn.akka.persistence;

public class CounterState {

    public int value;

    public CounterState(int value){
        this.value = value;
    }

    public void apply(Event e) {
        if (e.o instanceof Increment){
            value++;
        }else {

            if (e.o instanceof Decrement) {
                value--;
            } else {
                throw new UnsupportedOperationException("No Operation Handling");
            }
        }
    }

    @Override
    public String toString() {
        return value+"";
    }

    public CounterState copy() {
        return new CounterState(this.value);
    }
}
