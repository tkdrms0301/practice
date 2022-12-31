package hf.dp.abstract_factory;

public class PepperoniPizza extends Pizza{
    PizzaIngredientFactory ingredientFactory;
    public PepperoniPizza(PizzaIngredientFactory ingredientFactory){
        this.ingredientFactory = ingredientFactory;
    }
    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough... ");ingredientFactory.createDough();
        System.out.println("Adding sauce... ");ingredientFactory.createSauce();
        System.out.println("Adding cheese... ");ingredientFactory.createCheese();
    }
}
