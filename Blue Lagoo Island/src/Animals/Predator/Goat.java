package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Goat extends Herbivorous {
    public static final int maxAmountOnTheCell = 140;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Goat instance;

    private Goat() {
        super();
        this.weight = 60;
        this.speed = 3;
        this.maxSaturation = 10;
        Goat.count.getAndIncrement();
    }

    public synchronized static Goat returnGoat(){
        if (count.get() <= Goat.maxAmountOnTheCell) {
            instance = new Goat();
        }
        return instance;
    }
}
