import java.lang.Comparable;

public class InsertionOrder implements Comparable<InsertionOrder> { // Test1
    private Comparable value;
    private static int insertionOrder = 0;
    private int insertionOrderValue;

    public InsertionOrder(Comparable newValue) {
        value = newValue;
        insertionOrderValue = insertionOrder++;
    }

    public Comparable getValue() {
        return value;
    }

    public int getInsertionOrderValue() {
        return insertionOrderValue;
    }

    @Override
    public int compareTo(InsertionOrder isertionOrder) {
        return this.getValue().compareTo(isertionOrder.getValue());
    }
}
