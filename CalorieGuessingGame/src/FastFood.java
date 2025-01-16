
abstract class FastFood {
    protected String name;
    protected int calories;
    protected int fat;
    protected int carbs;
    protected int protein;

protected FastFood (String name, int calories, int fat, int carbs, int protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public abstract void displayInfo();
}