public class FastFoodItem extends FastFood {
    private String imagePath;

    public FastFoodItem(String name, int calories, int fat, int carbs, int protein, String imagePath) {
        super(name, calories, fat, carbs, protein);
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
    public int getCalories() {
        return calories;
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Calories: " + calories);
        System.out.println("Fat: " + fat + "g");
        System.out.println("Carbs: " + carbs + "g");
        System.out.println("Protein: " + protein + "g");
    }
}
