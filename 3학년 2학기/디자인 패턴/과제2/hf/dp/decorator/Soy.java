package hf.dp.decorator;

public class Soy extends CondimentDecorator{
    public Soy(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost(){
        return .10 + beverage.cost();
    }
}
