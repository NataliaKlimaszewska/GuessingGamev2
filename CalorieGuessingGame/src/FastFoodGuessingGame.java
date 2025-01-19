import java.io.IOException;
import java.util.*;

class FastFoodGuessingGame {
    private List<FastFoodItem> menuItems;
    private int lifePoints = 100;

    public FastFoodGuessingGame(List<FastFoodItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void selectGameMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz tryb gry:");
        System.out.println("1. Podstawowy (Zgadnij kalorie)");
        System.out.println("2. Zaawansowany (Zgadnij kalorie, tłuszcze, węglowodany, białko)");

        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            FastFoodGameMode basicGame = new BasicGameMode(menuItems);
            basicGame.startGame();
        } else if (choice.equals("2")) {
            FastFoodGameMode advancedGame = new AdvancedGameMode(menuItems);
            advancedGame.startGame();
        } else {
            System.out.println("Niepoprawny wybór! Spróbuj ponownie.");
            selectGameMode();
        }
    }



    public static void main(String[] args) throws IOException {
        // Ścieżka do pliku CSV
        String csvPath = "CalorieGuessingGame/src/menu.csv"; //tutaj musze s edawac sciezke absolutna
        List<FastFoodItem> menuItems = FastFoodCSVLoader.loadCSV(csvPath);

        FastFoodGuessingGame game = new FastFoodGuessingGame(menuItems);
        game.selectGameMode();
    }
}
