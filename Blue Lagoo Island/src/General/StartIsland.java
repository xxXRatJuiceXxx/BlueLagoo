package General;


import Animals.generalElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;

public class StartIsland {

    public static Island myIsland = Island.getInstance(); //создаём экземпляр острова
    public static volatile HashSet<generalElement> animalList = new HashSet<>(); //основной соствав всех животных/растений
    public static volatile ArrayList<generalElement> animalsOnCell = new ArrayList<>(); //список для всех сущнойстей в конкретной ячейки
    public static LifeElementFactory lifeElementFactory = new LifeElementFactory(); //экземпляр фабрики
    public static List <generalElement> endOfCycle = new ArrayList<>();
    public static volatile int counter = 0;
    public static volatile int wasBorn = 0;
    public static volatile int wasDie = 0;
    public static volatile int maxNewBorned = 0;
    public static volatile int cycles = 0;

    public static volatile int days = 0;
    public static volatile int startAmountOfAnimal = 0;
    public static ExecutorService executorService = new ScheduledThreadPoolExecutor(5); //создадим экземпля Executor с пулом 5

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        //создаём таблицу возможных событий
        AnimalTable.getInstance();

        System.out.println("Приветсвуем вас на Острове \"Голубая Логуна\"! \n" + "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n"+"Оствров размерами 10х10\n"+"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" );

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Введите, сколько новых животных может родиться (количество просмотров: 100...2000)...");
            maxNewBorned = Integer.parseInt(reader.readLine());

            System.out.println("Введите, сколько дней будет существовать Остров...");
            cycles = Integer.parseInt(reader.readLine());

            System.out.println("Введите, с какого количества животных вы хотели бы начать...");
            startAmountOfAnimal = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }




        ///запуск фабрики элементов жизни создание животных растений - больше растений за цикл
        for (int s=0; s<startAmountOfAnimal; s++){
            animalList.add(lifeElementFactory.getAnimalByType(randomAnimalTypeEnum()));
            for (int w=0; w<10; w++) {
                animalList.add(lifeElementFactory.getAnimalByType(AnimalType.PLANT));
            }
        }

        //управляемый остров циклами занавеса
        for (int run = 0; run <cycles; run++){
            oneCycle();
        }

        executorService.shutdown();
    }

    //один цикличный метод
    public static synchronized void oneCycle() throws InterruptedException {

        wasBorn=counter-wasBorn;
        if (wasBorn<0) wasBorn=-wasBorn;
        days++;

        System.out.println(
                "________________________День "+days+"______________________________" +
                "\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" +
                "\n                  Количество животных: "+animalList.size()+"" +
                "\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n" +
                "                    Родилось: "+wasBorn
                +"\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n                " +
                "      Умерло: "+wasDie+
                "\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n"
        );
        for (int i = 0; i < myIsland.blueLagoon.length; i++) {
            for (int j = 0; j < myIsland.blueLagoon[i].length; j++) {
                animalList.stream().filter(lifeElement -> !lifeElement.isAlive())
                        .filter(lifeElement -> !lifeElement.getClass().getSimpleName().equals("Plant"))
                        .forEach(lifeElement -> wasDie++);
                animalList.removeIf(lifeElement -> !lifeElement.isAlive());
                for (generalElement animal : animalList) {
                    if (animal.getLocation().equals(myIsland.blueLagoon[i][j])) {
                        animalsOnCell.add(animal);
                    }
                }

//запускат поток в animalsOnCell прежде чем перейти на другую ячейку
                executorService.invokeAll(animalsOnCell, 100, TimeUnit.MILLISECONDS);

                animalsOnCell.clear();
            }
        }
    }

    //ставит случайную ячейку для каждого объекта
    public static IslandCell randomCell(){
        int x = ThreadLocalRandom.current().nextInt(0, Island.getInstance().getLengthX());
        int y = ThreadLocalRandom.current().nextInt(0, Island.getInstance().getLengthY());
        return myIsland.blueLagoon[y][x];
    }

    //рандом для enum AnimalType
    public static AnimalType randomAnimalTypeEnum(){
        int x = AnimalType.values().length;
        int random = ThreadLocalRandom.current().nextInt(0, x);
        return AnimalType.values()[random];
    }

}
