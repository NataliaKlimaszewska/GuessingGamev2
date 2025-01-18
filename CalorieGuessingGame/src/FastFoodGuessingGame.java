import java.io.IOException;
import java.util.*;

class FastFoodGuessingGame implements FastFoodGame {
    private List<FastFoodItem> menuItems;
    private int lifePoints = 100;

    public FastFoodGuessingGame(List<FastFoodItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public void askQuestion(FastFoodItem item) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zgadnij wartości dla " + item.getName() + ":");

        boolean correct = true;

        // Kalorie
        int caloriesGuess = getValidInput(scanner, "Podaj kalorie: ");
        int calorieTolerance = (item.getCalories() < 100) ? 10 : 50;  // Jeśli poniżej 100 kcal, tolerancja 10 kcal, powyżej 100 kcal, tolerancja 50 kcal

        if (Math.abs(caloriesGuess - item.getCalories()) > calorieTolerance) {
            System.out.println("Błąd! Poprawna wartość to: " + item.getCalories() + " kalorie.");
            correct = false;
        }

        // Jeśli odpowiedź była błędna, odejmujemy punkty
        if (!correct) {
            lifePoints -= 5;
            System.out.println("Błędna odpowiedź. Straciłeś 5 punktów życia.");
            System.out.print("Punkty życia: " + lifePoints + " \n");
        } else {
            System.out.println("Dobrze! To poprawna wartość: " + item.getCalories());
        }

        // Resetowanie flagi dla kolejnych odpowiedzi
        correct = true;

        // Węglowodany
        int carbsGuess = getValidInput(scanner, "Podaj węglowodany (g): ");
        if (Math.abs(carbsGuess - item.getCarbs()) > 5) {  // Tolerancja 5g dla węglowodanów
            System.out.println("Błąd! Poprawna wartość to: " + item.getCarbs() + "g");
            correct = false;
        }
        if (!correct) {
            lifePoints -= 5;
            System.out.println("Błędna odpowiedź. Straciłeś 5 punktów życia.");
            System.out.print("Punkty życia: " + lifePoints + " \n");
        } else {
            System.out.println("Dobrze! To poprawna wartość: " + item.getCarbs() + "g");
        }

        // Resetowanie flagi dla kolejnych odpowiedzi
        correct = true;

        // Białko
        int proteinGuess = getValidInput(scanner, "Podaj białko (g): ");
        if (Math.abs(proteinGuess - item.getProtein()) > 5) {  // Tolerancja 5g dla białka
            System.out.println("Błąd! Poprawna wartość to: " + item.getProtein() + "g");
            correct = false;
        }
        if (!correct) {
            lifePoints -= 5;
            System.out.println("Błędna odpowiedź. Straciłeś 5 punktów życia.");
            System.out.println("Punkty życia: " + lifePoints + " \n");
        } else {
            System.out.println("Dobrze! To poprawna wartość: " + item.getProtein() + "g");
        }
    }

    // Metoda zabezpieczająca przed wprowadzeniem błędnych danych
    private int getValidInput(Scanner scanner, String prompt) {
        int userInput = -1;
        while (true) {
            try {
                System.out.print(prompt);
                userInput = scanner.nextInt(); // Próba wczytania liczby całkowitej
                scanner.nextLine(); // Konsumuje pozostały znak nowej linii
                break; // Jeśli wprowadzono liczbę, wychodzimy z pętli
            } catch (InputMismatchException e) {
                // Obsługuje przypadek, gdy użytkownik wpisuje coś, co nie jest liczbą
                System.out.println("Błąd! Proszę wprowadzić liczbę.");
                scanner.nextLine(); // Konsumuje pozostały błędny wejściowy token
            }
        }
        return userInput;
    }

    // Metoda rozpoczynająca grę
    public void startGame() throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Tworzymy kopię listy menuItems, aby przechowywać dostępne produkty
        List<FastFoodItem> remainingItems = new ArrayList<>(menuItems);

        // Losowanie pytań do momentu wyczerpania produktów lub punktów życia
        while (!remainingItems.isEmpty() && lifePoints > 0) {
            // Losowanie produktu z dostępnych
            Random rand = new Random();
            int randomIndex = rand.nextInt(remainingItems.size());
            FastFoodItem item = remainingItems.get(randomIndex);

            // Zadanie pytania
            askQuestion(item);

            // Usuwamy produkt z listy dostępnych produktów
            remainingItems.remove(randomIndex);

            // Informowanie o pozostałych punktach życia
            if (lifePoints <= 0) {
                System.out.println("Koniec gry! Straciłeś wszystkie punkty życia.");
                break;
            }

            System.out.println("Pozostałe punkty życia: " + lifePoints);
            System.out.println("Naciśnij Enter, aby kontynuować do kolejnego produktu.");
            System.out.println("Wpisz 'exit', aby zakończyć grę.");

            String userInput = scanner.nextLine().trim().toLowerCase();
            if (userInput.equals("exit")) {
                System.out.println("Dziękujemy za grę. Do zobaczenia wkrótce.");
                break;
            }
        }
        if (lifePoints > 0) {
            System.out.println("Gratulacje! Ukończyłeś grę.");
        } else if (remainingItems.isEmpty()) {
            System.out.println("Gratulacje! Ukończyłeś wszystkie pytania.");
        }
    }

    public static void main(String[] args) throws IOException {
        // Ścieżka do pliku CSV
        String csvPath = "src/menu.csv";
        List<FastFoodItem> menuItems = FastFoodCSVLoader.loadCSV(csvPath);

        FastFoodGuessingGame game = new FastFoodGuessingGame(menuItems);
        game.startGame();
    }
}
