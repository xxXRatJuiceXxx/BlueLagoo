package Animals.Carnivore;

import Animals.Carnivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Eagle extends Carnivorous {

    public static final int maxAmountOnTheCell = 20;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Eagle instance;

    private Eagle() {
        super();
        this.weight = 6;
        this.speed = 3;
        this.maxSaturation = 1;
        Eagle.count.getAndIncrement();
    }

    public synchronized static Eagle returnEagle(){
        if (count.get() <= Eagle.maxAmountOnTheCell) {
            instance = new Eagle();
        }
        return instance;
    }
}
