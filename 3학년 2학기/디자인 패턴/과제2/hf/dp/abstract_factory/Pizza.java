package hf.dp.abstract_factory;

import hf.dp.abstract_factory.cheese.Cheese;
import hf.dp.abstract_factory.dough.Dough;
import hf.dp.abstract_factory.sauce.Sauce;
import hf.dp.abstract_factory.veggie.Veggie;

public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggie veggies[];
    Cheese cheese;

    abstract public void prepare();

    void bake(){
        System.out.println("Bake for 25 minutes at 350");
    }
    void cut(){
        System.out.println("Cutting the pizza into diagonal slices");
    }
    void box(){
        System.out.println("Place pizza in official PizzaStore box");
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.toString();
    }
}
