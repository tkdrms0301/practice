public class Token {
    private int value;
    private String operator = null;

    public Token(int value) {
        this.value = value;
    }

    public Token(String operator) {
        this.operator = operator;
    }

    public int getValue() {
        return value;
    }

    public String getOperator() {
        return operator;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
