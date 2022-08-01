package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Caterpillar extends Herbivorous {
    public static final int maxAmountOnTheCell = 1000;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Caterpillar instance;

    private Caterpillar() {
        super();
        this.weight = 0.01F;
        this.speed = 0;
        this.maxSaturation = 0;
        Caterpillar.count.getAndIncrement();
    }

    public synchronized static Caterpillar returnCaterpillar(){
        if (count.get() <= Caterpillar.maxAmountOnTheCell) {
            instance = new Caterpillar();
        }
        return instance;
    }
}
