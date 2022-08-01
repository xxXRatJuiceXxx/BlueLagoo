package General;

import Animals.Carnivore.*;
import Animals.Predator.*;
import Animals.generalElement;
import Animals.Plant;

//Создание животных

public class LifeElementFactory {
    private generalElement generalElement;

    public generalElement getAnimalByType (AnimalType type){
            this.generalElement = switch (type) {
                case BEAR -> Bear.returnBear();
                case BOA -> Boa.returnBoa();
                case EAGLE -> Eagle.returnEagle();
                case FOX -> Fox.returnFox();
                case WOLF -> Wolf.returnWolf();
                case BOAR -> Boar.returnBoar();
                case BULL -> Bull.returnBull();
                case CATERPILLAR -> Caterpillar.returnCaterpillar();
                case DEER -> Deer.returnDeer();
                case DUCK -> Duck.returnDuck();
                case GOAT -> Goat.returnGoat();
                case HORSE -> Horse.returnHorse();
                case MOUSE -> Mouse.returnMouse();
                case RABBIT -> Rabbit.returnRabbit();
                case SHEEP -> Sheep.returnSheep();
                case PLANT -> Plant.returnPlant();
            };
        return generalElement;
    }

}
