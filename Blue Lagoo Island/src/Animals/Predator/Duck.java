package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Duck extends Herbivorous {
    public static final int maxAmountOnTheCell = 200;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Duck instance;

    private Duck() {
        super();
        this.weight = 1;
        this.speed = 4;
        this.maxSaturation = 0.15F;
        Duck.count.getAndIncrement();
    }

    public synchronized static Duck returnDuck(){
        if (count.get() <= Duck.maxAmountOnTheCell) {
            instance = new Duck();
        }
        return instance;
    }
}
