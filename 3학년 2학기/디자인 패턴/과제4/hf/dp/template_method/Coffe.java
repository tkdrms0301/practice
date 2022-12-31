package hf.dp.template_method;

public class Coffe extends CaffeineBeverage{
    @Override
    public void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and Milk");
    }
}
