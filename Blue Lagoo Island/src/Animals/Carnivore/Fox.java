package Animals.Carnivore;

import Animals.Carnivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Fox extends Carnivorous {
    public static final int maxAmountOnTheCell = 30;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Fox instance;

    private Fox() {
        super();
        this.weight = 8;
        this.speed = 2;
        this.maxSaturation = 2;
        Fox.count.getAndIncrement();
    }

    public synchronized static Fox returnFox(){
        if (count.get() <= Fox.maxAmountOnTheCell) {
            instance = new Fox();
        }
        return instance;
    }
}
