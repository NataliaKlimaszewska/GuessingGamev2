import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FastFoodCSVLoader {

    public static List<FastFoodItem> loadCSV(String path) {
        List<FastFoodItem> menuItems = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Pomijanie nagłówka
                    continue;
                }
                String[] values = line.split(";");
                if (values.length == 6) {
                    try {
                        int id = Integer.parseInt(values[0].trim());
                        String name = values[1].trim();
                        int calories = Integer.parseInt(values[2].trim());
                        int fat = Integer.parseInt(values[3].trim());
                        int carbs = Integer.parseInt(values[4].trim());
                        int protein = Integer.parseInt(values[5].trim());
                        menuItems.add(new FastFoodItem(name, calories, fat, carbs, protein));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                        continue;
                    }
                } else {
                    System.out.println("Invalid data in row: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuItems;
    }
}
