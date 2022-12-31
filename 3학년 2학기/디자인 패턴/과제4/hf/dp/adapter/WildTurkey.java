package hf.dp.adapter;

public class WildTurkey implements Turkey{

    @Override
    public void gobble() {
        System.out.println("Gooble Gooble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
