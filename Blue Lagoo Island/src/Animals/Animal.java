package Animals;

import General.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends generalElement {

    //Основные поля
    protected IslandCell location;
    protected float weight;
    protected int speed;
    protected float maxSaturation;
    protected boolean isAlive;
    ArrayList<generalElement> animalToDo;

    protected Animal() {
        this.isAlive = true;
        this.lifeAmount = 100;
        this.setLocation(StartIsland.randomCell());
    }

    //главные методы
    public synchronized void eat() {
        setAnimalToDo(StartIsland.animalsOnCell);
        while (this.animalToDo == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        float currentLife = this.lifeAmount;

        //получаем всех животных и растений на отдельной клетке
        for (generalElement toEat : animalToDo) {
            String whoEaten = toEat.getClass().getSimpleName();
            String whoEats = this.getClass().getSimpleName();
            if (whoEaten.equals(whoEats)) continue;//продолжаем в случае нахождения сородича

            //случайное число , извликается из таблицы возможности
            int randomPossibility = ThreadLocalRandom.current().nextInt(0, 101);
            int possibility = AnimalTable.getInstance().returnPossibility(whoEats, whoEaten);

            //может ли один съесть другого
            if ((possibility>randomPossibility)&&(toEat.isAlive())) {
                toEat.setAlive(false);

                if (this.lifeAmount<=0) this.setAlive(false);
                //случайное условие для еды
                if(this.lifeAmount<80 && this.lifeAmount>0) {
                    float factor = this.maxSaturation /toEat.weight;
                    float extra = (factor>=1)? 20 : (factor*10);
                    this.lifeAmount = currentLife + extra;
                    if (this.lifeAmount > 100) {
                        this.setLifeAmount(100f);
                    }
                }
            }
        }
    }



    public synchronized void move(){

        IslandCell startPoint = this.getLocation();
        int newX = 0;
        int newY = 0;
        int speed = this.getSpeed();
        //выбор направления
        int directionMove = ThreadLocalRandom.current().nextInt(1,5); //1 -up, 2 - right, 3 - down, 4 - left
        switch (directionMove) {
            case 1 -> {
                newX = startPoint.getX();
                int difference = (startPoint.getY() - speed);
                if (difference >= 0) {
                    newY = difference;
                } else {
                    newY = (startPoint.getY() - (speed - Math.abs(difference)));
                }
            }

            case 2 -> {
                newY = startPoint.getY();
                int difference = (startPoint.getX() + speed);
                if (startPoint.getX() == StartIsland.myIsland.getLengthX() - 1) {
                    newX = startPoint.getX();
                } else if (difference < StartIsland.myIsland.getLengthX()) {
                    newX = difference;
                } else {
                    newX = (startPoint.getX() + ((StartIsland.myIsland.getLengthX() - 1) - startPoint.getX()));
                }
            }

            case 3 -> {
                newX = startPoint.getX();
                int difference = (startPoint.getY() + speed);
                if (startPoint.getY() == StartIsland.myIsland.getLengthY() - 1) {
                    newY = startPoint.getY();
                } else if (difference < StartIsland.myIsland.getLengthY()) {
                    newY = difference;
                } else {
                    newY = (startPoint.getY() + ((StartIsland.myIsland.getLengthY() - 1) - startPoint.getY()));
                }
            }

            case 4 -> {
                newY = startPoint.getY();
                int difference = (startPoint.getX() - speed);
                if (difference >= 0) {
                    newX = difference;
                } else {
                    newX = (startPoint.getX() - (speed - Math.abs(difference)));
                }
            }

        }

        this.setLocation(StartIsland.myIsland.getBlueLagoon()[newY][newX]);
        this.setLifeAmount(this.lifeAmount-10); //каждый такт отнимает по 10% от времени жизни
        if (this.lifeAmount<=0) this.setAlive(false);
    }


    public synchronized void multiply(){
        setAnimalToDo(StartIsland.animalsOnCell);

        while (this.animalToDo == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (generalElement toMultiply : animalToDo) {
            String dad = toMultiply.getClass().getSimpleName();
            String mom = this.getClass().getSimpleName();

            if (dad.equals("Plant")) continue; //если не Растение то продолжаем

            //рождение с шансом 10%
            if ((dad.equals(mom)) && (StartIsland.counter<=StartIsland.maxNewBorned)) {
                if (ThreadLocalRandom.current().nextInt(0,11) == 0)
                {
                    StartIsland.animalList.add(StartIsland.lifeElementFactory.getAnimalByType(AnimalType
                            .valueOf(dad.toUpperCase())));
                    StartIsland.counter++;
                    StartIsland.wasBorn++;
                }
            }
        }
        StartIsland.wasBorn =0;
    }


    //Геттеры Сеттеры
    public ArrayList<generalElement> getAnimalToDo() {
        return animalToDo;
    }

    public synchronized void setAnimalToDo(ArrayList<generalElement> animalToDo) {
        this.animalToDo = animalToDo;
    }

    public IslandCell getLocation() {
        return location;
    }

    public void setLocation(IslandCell location) {
        this.location = location;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
