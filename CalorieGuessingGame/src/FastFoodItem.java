

class FastFoodItem  extends FastFood {

    public FastFoodItem(String name, int calories, int fat, int carbs, int protein) {
    super(name, calories, fat, carbs, protein);

    }
    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

}
