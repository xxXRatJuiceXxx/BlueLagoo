package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Sheep extends Herbivorous {
    public static final int maxAmountOnTheCell = 140;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Sheep instance;

    private Sheep() {
        super();
        this.weight = 70;
        this.speed = 3;
        this.maxSaturation = 15;
        Sheep.count.getAndIncrement();
    }

    public synchronized static Sheep returnSheep(){
        if (count.get() <= Sheep.maxAmountOnTheCell) {
            instance = new Sheep();
        }
        return instance;
    }
}
