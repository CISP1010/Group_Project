import java.util.HashMap;
import java.util.Map;

public class TableData extends EmployeeData {
    private static HashMap<Integer, Table> tableData = new HashMap<>();

    public TableData() {
        for (int i = 1; i <= 20; i++) {
            tableData.put(i, new Table(i));
        }
    }

    public String getTableData(int tableNumber) {
        StringBuilder sb = new StringBuilder();
        Table table = tableData.get(tableNumber);
        sb.append("Table #: ").append(table.getNumber()).append(" | ").append("Seats Filled: ").append(table.getSeatsTaken()).append(" | ").append("\n")
                .append("Dishes: ").append("\n").append(table.listDishes()).append("\n");
        return sb.toString();
    }

    public String listTables() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Table> entry : tableData.entrySet()) {
            int Table = entry.getKey();
            Table table = entry.getValue();
            sb.append("Table #: ").append(table.getNumber()).append(" | ").append("Seats Filled: ").append(table.getSeatsTaken()).append("\n")
                    .append("Dishes:").append("\n").append(table.listDishes()).append("\n");
        }
        return sb.toString();
    }

    public boolean isFilled(int tableNumber) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            return table.getSeatsTaken() >= 4;
        } else {
            throw new RuntimeException("Table not found.");
        }
    }

    public void setSeatsFilled(int tableNumber, int seatsFilled) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            table.setSeatsFilled(seatsFilled);
            tableData.put(tableNumber, table);
        } else {
            throw new RuntimeException("Table not found.");
        }
    }

    public int getEmptySeats(int tableNumber) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            return 4 - table.getSeatsTaken();
        } else throw new RuntimeException("Table not found.");
    }

    public int getFilledSeats(int tableNumber) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            return table.getSeatsTaken();
        } else throw new RuntimeException("Table not found.");
    }

    public String getDishes(int tableNumber) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            return table.listDishes();
        } else throw new RuntimeException("Table not found.");
    }

    public void addDish(int tableNumber, int seatNumber, String dish) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            table.addDish(seatNumber, dish);
            tableData.put(tableNumber, table);
        }
    }
}