package Animals;

import General.IslandCell;
import java.util.concurrent.Callable;


public class generalElement implements Callable <generalElement>{
//Основные поля
    protected IslandCell location;
    protected float weight;
    protected int speed;
    protected float maxSaturation;
    protected boolean isAlive;
    protected float lifeAmount;
    //методы
    public void move() {
    }

    public void eat() {
    }

    public void multiply(){
    }




    //Геттеры сеттеры
    public IslandCell getLocation() {
        return location;
    }

    public void setLocation(IslandCell location) {
        this.location = location;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public float getLifeAmount() {
        return lifeAmount;
    }

    public void setLifeAmount(float lifeAmount) {
        this.lifeAmount = lifeAmount;
    }


    //Многопоточка
    @Override
    public generalElement call() {
        eat();
        multiply();
        move();
        return this;
    }
}
