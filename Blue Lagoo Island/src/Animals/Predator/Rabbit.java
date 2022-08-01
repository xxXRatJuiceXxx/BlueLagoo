package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Rabbit extends Herbivorous {
    public static final int maxAmountOnTheCell = 150;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Rabbit instance;

    private Rabbit() {
        super();
        this.weight = 2;
        this.speed = 2;
        this.maxSaturation = 0.45F;
        Rabbit.count.getAndIncrement();
    }

    public synchronized static Rabbit returnRabbit(){
        if (count.get() <= Rabbit.maxAmountOnTheCell) {
            instance = new Rabbit();
        }
        return instance;
    }
}
