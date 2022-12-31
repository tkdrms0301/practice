package hf.dp.singleton;

public class ChocolateBoiler {
    private boolean empty;
    private boolean boiled;
    private volatile static ChocolateBoiler uniqueInstance;

    private ChocolateBoiler(){
        empty = true;
        boiled = false;
    }

    public static ChocolateBoiler getInstance(){
        if(uniqueInstance == null){
            synchronized (ChocolateBoiler.class){
                if(uniqueInstance == null){
                    System.out.println("Createing unique instance of Chocolate Boiler");
                    uniqueInstance = new ChocolateBoiler();
                }
            }
            uniqueInstance = new ChocolateBoiler();
        }
        return uniqueInstance;
    }

    public void fill(){
        if(isEmpty()){
            empty = false;
            boiled = false;
        }
    }

    public void drain(){
        if(!isEmpty() && isBoiled()){
            empty = true;
        }
    }

    public void boil(){
        if(!isEmpty() && !isBoiled()){
            boiled = true;
        }
    }

    public void reportBoilerState(){
        System.out.println("State: empty[" + empty + "] boiled[" + boiled + "]");
    }

    public boolean isEmpty() { return empty; }
    public boolean isBoiled() { return  boiled; }
}
