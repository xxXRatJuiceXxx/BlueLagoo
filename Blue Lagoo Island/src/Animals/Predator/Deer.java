package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Deer extends Herbivorous {
    public static final int maxAmountOnTheCell = 20;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Deer instance;

    private Deer() {
        super();
        this.weight = 300;
        this.speed = 4;
        this.maxSaturation = 50;
        Deer.count.getAndIncrement();
    }

    public synchronized static Deer returnDeer(){
        if (count.get() <= Deer.maxAmountOnTheCell) {
            instance = new Deer();
        }
        return instance;
    }
}
