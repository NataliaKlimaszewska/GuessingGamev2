

class FastFoodItem  extends FastFood {

    public FastFoodItem(String name, int calories, int fat, int carbs, int protein) {
    super(name, calories, fat, carbs, protein);

    }
    @Override
    public void displayInfo() {
        System.out.println("Nazwa: " + name);
        System.out.println("Kalorie: " + calories);
        System.out.println("Tłuszcz: " + fat);
        System.out.println("Węglowodany: " + carbs);
        System.out.println("Białko: " + protein);
    }

}
