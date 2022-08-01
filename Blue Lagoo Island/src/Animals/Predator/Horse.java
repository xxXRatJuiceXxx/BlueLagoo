package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Horse extends Herbivorous {
    public static final int maxAmountOnTheCell = 20;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Horse instance;

    private Horse() {
        super();
        this.weight = 400;
        this.speed = 4;
        this.maxSaturation = 60;
        Horse.count.getAndIncrement();
    }

    public synchronized static Horse returnHorse(){
        if (count.get() <= Horse.maxAmountOnTheCell) {
            instance = new Horse();
        }
        return instance;
    }
}
