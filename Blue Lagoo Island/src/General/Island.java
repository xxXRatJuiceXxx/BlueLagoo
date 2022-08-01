package General;

public class Island {
    //основные поля
    private static Island instance = null;
    private int lengthX = 10;
    private int lengthY =10;


    IslandCell [][] blueLagoon = new IslandCell[lengthY][lengthX];

    public static Island getInstance() {
        if (instance == null) {
            instance = new Island();

            for (int y = 0; y < instance.blueLagoon.length; y++){
                for (int x = 0; x<instance.blueLagoon[y].length; x++) {
                    instance.blueLagoon[y][x] = new IslandCell(x, y);
                }
            }

        }
        return instance;
    }


    //Геттеры Сеттеры
    public int getLengthX() {
        return lengthX;
    }

    public void setLengthX(int lengthX) {
        this.lengthX = lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public void setLengthY(int lengthY) {
        this.lengthY = lengthY;
    }

    public IslandCell[][] getBlueLagoon() {
        return blueLagoon;
    }

}
