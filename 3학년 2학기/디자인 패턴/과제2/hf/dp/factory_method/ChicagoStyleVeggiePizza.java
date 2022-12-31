package hf.dp.factory_method;

public class ChicagoStyleVeggiePizza extends Pizza{
    public ChicagoStyleVeggiePizza(){
        name = "Chicago Style Deep Dish and Cheese Veggie Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
    }

    @Override
    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
