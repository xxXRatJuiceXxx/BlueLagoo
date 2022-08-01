package Animals.Carnivore;

import Animals.Carnivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Boa extends Carnivorous {

    public static final int maxAmountOnTheCell = 30;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Boa instance;


    private Boa() {
        super();
        this.weight = 15;
        this.speed = 1;
        this.maxSaturation = 3;
        Boa.count.getAndIncrement();
    }

    public synchronized static Boa returnBoa(){
        if (count.get() <= Boa.maxAmountOnTheCell) {
            instance = new Boa();
        }
        return instance;
    }
}
