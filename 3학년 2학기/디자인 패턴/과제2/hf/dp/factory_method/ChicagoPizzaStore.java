package hf.dp.factory_method;

public class ChicagoPizzaStore extends PizzaStore {
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if(type.equals("cheese")){
            pizza = new ChicagoStyleCheesePizza();
        }else if(type.equals("veggie")){
            pizza = new ChicagoStyleVeggiePizza();
        }
        return pizza;
    }
}