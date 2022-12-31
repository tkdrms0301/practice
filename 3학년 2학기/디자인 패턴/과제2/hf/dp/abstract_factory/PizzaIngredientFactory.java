package hf.dp.abstract_factory;

import hf.dp.abstract_factory.cheese.Cheese;
import hf.dp.abstract_factory.clams.Clams;
import hf.dp.abstract_factory.dough.Dough;
import hf.dp.abstract_factory.pepperoni.Pepperoni;
import hf.dp.abstract_factory.sauce.Sauce;
import hf.dp.abstract_factory.veggie.Veggie;

public interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggie[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClam();
}