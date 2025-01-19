import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class BasicGameMode extends FastFoodGameMode {

    public BasicGameMode(List<FastFoodItem> menuItems) {
        super(menuItems);
    }

    @Override
    public void askQuestion(FastFoodItem item) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zgadnij kalorie dla " + item.getName() + ":");

        int caloriesGuess = getValidInput(scanner, "Podaj kalorie: ");
        int calorieTolerance = (item.getCalories() < 100) ? 10 : 50;

        if (Math.abs(caloriesGuess - item.getCalories()) > calorieTolerance) {
            System.out.println("Błąd! Poprawna wartość to: " + item.getCalories() + " kalorie.");
            lifePoints -= 5;
        } else {
            System.out.println("Dobrze! To poprawna wartość: " + item.getCalories());
        }
    }

    private int getValidInput(Scanner scanner, String prompt) {
        int userInput = -1;
        while (true) {
            try {
                System.out.print(prompt);
                userInput = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Błąd! Proszę wprowadzić liczbę.");
                scanner.nextLine();
            }
        }
        return userInput;
    }
}
