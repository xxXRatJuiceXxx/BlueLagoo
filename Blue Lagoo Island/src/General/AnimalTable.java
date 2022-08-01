package General;

import java.util.HashMap;
import java.util.Map;

public class AnimalTable {

    //Таблица шансов съедания всех животных
    private static AnimalTable animalTable = null;


    protected Map<String, Map<String, Integer>> table = new HashMap<>();

    //создание нескольких таблиц для каждого типа животных
    protected HashMap<String, Integer> wolfTable = new HashMap<>(){{
            put ("Horse", 10); put ("Deer", 15); put ("Rabbit", 60); put ("Mouse", 80);
            put ("Goat", 60); put ("Sheep", 70); put ("Boar", 15); put ("Bull", 10);
            put ("Duck", 10);
        }};

    protected HashMap<String, Integer> boaTable = new HashMap<>(){{
        put ("Fox", 15); put ("Rabbit", 20); put ("Mouse", 40);
        put ("Duck", 10);
    }};

    protected HashMap<String, Integer> foxTable = new HashMap<>(){{
        put ("Rabbit", 70); put ("Mouse", 90);
        put ("Duck", 60); put ("Caterpillar", 40);
        }};

    protected HashMap<String, Integer> bearTable = new HashMap<>(){{
        put("Boa", 80); put ("Horse", 40); put ("Deer", 80); put ("Rabbit", 80); put ("Mouse", 90);
        put ("Goat", 70); put ("Sheep", 70); put ("Boar", 50); put ("Bull", 20);
        put ("Duck", 10);
    }};

    protected HashMap<String, Integer> eagleTable = new HashMap<>(){{
        put ("Fox", 10); put ("Rabbit", 90); put ("Mouse", 90);
        put ("Duck", 80);
    }};

    protected HashMap<String, Integer> horseTable = new HashMap<>(){{
        put ("Plant", 100);
    }};

    protected HashMap<String, Integer> deerTable = new HashMap<>(){{
        put ("Plant", 100);
    }};

    protected HashMap<String, Integer> rabbitTable = new HashMap<>(){{
        put ("Plant", 100);
    }};

    protected HashMap<String, Integer> mouseTable = new HashMap<>(){{
        put ("Caterpillar", 90); put ("Plant", 100);
    }};

    protected HashMap<String, Integer> goatTable = new HashMap<>(){{
        put ("Plant", 100);
    }};

    protected HashMap<String, Integer> sheepTable = new HashMap<>(){{
        put ("Plant", 100);
    }};

    protected HashMap<String, Integer> boarTable = new HashMap<>(){{
        put("Mouse", 50); put ("Caterpillar", 90); put ("Plant", 100);
    }};

    protected HashMap<String, Integer> bullTable = new HashMap<>(){{
        put ("Plant", 100);
    }};

    protected HashMap<String, Integer> duckTable = new HashMap<>(){{
        put ("Caterpillar", 90); put ("Plant", 100);
    }};

    protected HashMap<String, Integer> caterpillarTable = new HashMap<>(){{
        put ("Plant", 100);
    }};

    public static AnimalTable getInstance() {
        if (animalTable == null) {
            animalTable = new AnimalTable();

            animalTable.table.put("Wolf", animalTable.wolfTable);
            animalTable.table.put("Boa", animalTable.boaTable);
            animalTable.table.put("Fox", animalTable.foxTable);
            animalTable.table.put("Bear", animalTable.bearTable);
            animalTable.table.put("Eagle", animalTable.eagleTable);
            animalTable.table.put("Horse", animalTable.horseTable);
            animalTable.table.put("Deer", animalTable.deerTable);
            animalTable.table.put("Rabbit", animalTable.rabbitTable);
            animalTable.table.put("Mouse", animalTable.mouseTable);
            animalTable.table.put("Goat", animalTable.goatTable);
            animalTable.table.put("Sheep", animalTable.sheepTable);
            animalTable.table.put("Boar", animalTable.boarTable);
            animalTable.table.put("Bull", animalTable.bullTable);
            animalTable.table.put("Duck", animalTable.duckTable);
            animalTable.table.put("Caterpillar", animalTable.caterpillarTable);
        }
        return animalTable;
    }

    public int returnPossibility (String whoEats, String whoEaten){
       int x=0;
            if (AnimalTable.animalTable.table.get(whoEats).get(whoEaten)!=null) {
                x = AnimalTable.animalTable.table.get(whoEats).get(whoEaten);}
            return x;
    }

}
