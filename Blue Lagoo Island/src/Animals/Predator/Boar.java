package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Boar extends Herbivorous {
    public static final int maxAmountOnTheCell = 50;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Boar instance;

    private Boar() {
        super();
        this.weight = 400;
        this.speed = 2;
        this.maxSaturation = 50;
        Boar.count.getAndIncrement();
    }

    public synchronized static Boar returnBoar(){
        if (count.get() <= Boar.maxAmountOnTheCell) {
            instance = new Boar();
        }
        return instance;
    }
}
