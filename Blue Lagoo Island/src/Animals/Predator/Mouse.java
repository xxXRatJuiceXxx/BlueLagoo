package Animals.Predator;

import Animals.Herbivorous;

import java.util.concurrent.atomic.AtomicInteger;

public class Mouse extends Herbivorous {
    private static final int maxAmountOnTheCell = 500;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Mouse instance;

    private Mouse() {
        super();
        this.weight = 0.05F;
        this.speed = 1;
        this.maxSaturation = 0.01f;
        Mouse.count.getAndIncrement();
    }

    public synchronized static Mouse returnMouse(){
        if (count.get() <= Mouse.maxAmountOnTheCell) {
            instance = new Mouse();
        }
        return instance;
    }
}
