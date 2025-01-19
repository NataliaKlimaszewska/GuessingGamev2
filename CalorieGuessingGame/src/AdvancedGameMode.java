import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class AdvancedGameMode extends FastFoodGameMode {

    public AdvancedGameMode(List<FastFoodItem> menuItems) {
        super(menuItems);
    }

    @Override
    public void askQuestion(FastFoodItem item) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zgadnij wartości dla " + item.getName() + ":");

        boolean correct = true;

        // Kalorie
        int caloriesGuess = getValidInput(scanner, "Podaj kalorie: ");
        int calorieTolerance = (item.getCalories() < 100) ? 10 : 50;
        if (Math.abs(caloriesGuess - item.getCalories()) > calorieTolerance) {
            System.out.println("Błąd! Poprawna wartość to: " + item.getCalories() + " kalorie.");
            correct = false;
        }

        if (!correct) {
            lifePoints -= 5;
            System.out.println("Błędna odpowiedź. Straciłeś 5 punktów życia.");
        } else {
            System.out.println("Dobrze! To poprawna wartość: " + item.getCalories());
        }

        correct = true;

        // Węglowodany
        int carbsGuess = getValidInput(scanner, "Podaj węglowodany (g): ");
        if (Math.abs(carbsGuess - item.getCarbs()) > 5) {
            System.out.println("Błąd! Poprawna wartość to: " + item.getCarbs() + "g");
            correct = false;
        }

        if (!correct) {
            lifePoints -= 5;
            System.out.println("Błędna odpowiedź. Straciłeś 5 punktów życia.");
        } else {
            System.out.println("Dobrze! To poprawna wartość: " + item.getCarbs() + "g");
        }

        correct = true;

        // Białko
        int proteinGuess = getValidInput(scanner, "Podaj białko (g): ");
        if (Math.abs(proteinGuess - item.getProtein()) > 5) {
            System.out.println("Błąd! Poprawna wartość to: " + item.getProtein() + "g");
            correct = false;
        }

        if (!correct) {
            lifePoints -= 5;
            System.out.println("Błędna odpowiedź. Straciłeś 5 punktów życia.");
        } else {
            System.out.println("Dobrze! To poprawna wartość: " + item.getProtein() + "g");
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
