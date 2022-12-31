package hf.dp.factory_method;

public class NYPizzaStore extends PizzaStore {
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if(type.equals("cheese")){
            pizza = new NYStyleCheesePizza();
        }else if(type.equals("veggie")){
            pizza = new NYStyleVeggiePizza();
        }
        return pizza;
    }
}
