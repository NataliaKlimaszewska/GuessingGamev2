import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

abstract class FastFoodGameMode {
    protected List<FastFoodItem> menuItems;
    protected int lifePoints = 100;

    public FastFoodGameMode(List<FastFoodItem> menuItems) {
        this.menuItems = menuItems;
    }

    public abstract void askQuestion(FastFoodItem item);

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        List<FastFoodItem> remainingItems = new ArrayList<>(menuItems);

        while (!remainingItems.isEmpty() && lifePoints > 0) {
            int randomIndex = new Random().nextInt(remainingItems.size());
            FastFoodItem item = remainingItems.get(randomIndex);

            askQuestion(item);

            remainingItems.remove(randomIndex);

            if (lifePoints <= 0) {
                System.out.println("Koniec gry! Straciłeś wszystkie punkty życia.");
                break;
            }

            System.out.println("Pozostałe punkty życia: " + lifePoints);
            System.out.println("Wciśnij Enter, aby kontynuować, lub wpisz: exit aby zakończyć ");
            String userInput = scanner.nextLine().trim().toLowerCase();
            if (userInput.equals("exit")) {
                System.out.println("Dziękujemy za grę. Do zobaczenia wkrótce.");
                break;
            }
        }
        if (lifePoints > 0) {
            System.out.println("Gratulacje! Ukończyłeś grę.");
        }
    }
}
