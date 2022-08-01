package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Bull extends Herbivorous {
    public static final int maxAmountOnTheCell = 10;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Bull instance;

    private Bull() {
        super();
        this.weight = 700;
        this.speed = 3;
        this.maxSaturation = 100;
        Bull.count.getAndIncrement();
    }

    public synchronized static Bull returnBull(){
        if (count.get() <= Bull.maxAmountOnTheCell) {
            instance = new Bull();
        }
        return instance;
    }
}
