package Animals.Carnivore;

import Animals.Carnivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Bear extends Carnivorous {
    public static final int maxAmountOnTheCell = 5;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Bear instance;


    private Bear() {
        super();
        this.weight = 500;
        this.speed = 2;
        this.maxSaturation = 80;
        Bear.count.getAndIncrement();
    }

    public synchronized static Bear returnBear(){
        if (count.get() <= Bear.maxAmountOnTheCell) {
            instance = new Bear();
        }
        return instance;
    }

}
