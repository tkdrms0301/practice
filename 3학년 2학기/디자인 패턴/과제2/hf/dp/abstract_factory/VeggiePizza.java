package hf.dp.abstract_factory;

public class VeggiePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;
    public VeggiePizza(PizzaIngredientFactory ingredientFactory){
        this.ingredientFactory = ingredientFactory;
    }
    public void prepare() {
        System.out.println("Preparing " + name);
        ingredientFactory.createDough();System.out.println("Tossing dough... ");
        ingredientFactory.createSauce();System.out.println("Adding sauce... ");
        ingredientFactory.createCheese();System.out.println("Adding cheese... ");
    }
}
