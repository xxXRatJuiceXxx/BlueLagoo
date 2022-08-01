package Animals;

import General.IslandCell;
import General.StartIsland;

import java.util.concurrent.atomic.AtomicInteger;

public class Plant extends generalElement {
    public static final int maxAmountOnTheCell = 2000;
    public volatile static AtomicInteger count = new AtomicInteger();
    private static Plant instance;

    protected IslandCell location;
    protected float weight;
    protected boolean isAlive;

    private Plant() {
        this.weight = 1;
        this.isAlive = true;
        this.lifeAmount = 100;
        this.setLocation(StartIsland.randomCell());
        Plant.count.getAndIncrement();
    }

    public synchronized static Plant returnPlant(){
        if (count.get() <= Plant.maxAmountOnTheCell) {
            instance = new Plant();
        }
        return instance;
    }



    //Геттеры сеттеры

    public void setLocation(IslandCell location) {
        this.location = location;
    }

    public IslandCell getLocation() {
        return location;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public void move() {
    }

    @Override
    public void eat() {
        super.eat();
    }

    @Override
    public void multiply() {
        super.multiply();
    }

    @Override
    public String toString() {
        return "Plant: { " +
                this.getClass().getSimpleName() +
                ", "+location +
                '}';
    }



}
