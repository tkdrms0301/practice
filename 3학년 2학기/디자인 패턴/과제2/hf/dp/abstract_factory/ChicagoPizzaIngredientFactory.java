package hf.dp.abstract_factory;

import hf.dp.abstract_factory.cheese.Cheese;
import hf.dp.abstract_factory.cheese.ReggianoCheese;
import hf.dp.abstract_factory.clams.Clams;
import hf.dp.abstract_factory.clams.FreshClams;
import hf.dp.abstract_factory.dough.Dough;
import hf.dp.abstract_factory.dough.ThinCrustDough;
import hf.dp.abstract_factory.pepperoni.Pepperoni;
import hf.dp.abstract_factory.pepperoni.SlicedPepperoni;
import hf.dp.abstract_factory.sauce.MarinaraSauce;
import hf.dp.abstract_factory.sauce.Sauce;
import hf.dp.abstract_factory.veggie.*;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory{
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggie[] createVeggies() {
        Veggie veggie[] = {new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
        return veggie;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FreshClams();
    }
}
