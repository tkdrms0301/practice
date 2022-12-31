package hf.dp.template_method;

public class Tea extends CaffeineBeverage{

    @Override
    void brew() {
        System.out.println("Steeping the sea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }
}
