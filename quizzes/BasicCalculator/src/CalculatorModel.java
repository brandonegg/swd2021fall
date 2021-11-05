public class CalculatorModel {

    private int previousNumber;
    private int currentNumber;
    private char operator;

    public CalculatorModel() {
        previousNumber = 0;
        currentNumber = 0;
        operator = '0';
    }

    public void performComputation() {
        switch(operator){
            case '+':   currentNumber = previousNumber+currentNumber;
                        break;
            case '-':   currentNumber = previousNumber-currentNumber;
                        break;
            case '*':   currentNumber = previousNumber*currentNumber;
                        break;
            case '/':   currentNumber = previousNumber/currentNumber;
            default:    break;
        }
        operator = '0';
    }

    public void setOperator(String letter) {
        operator = letter.charAt(0);
    }

    public void addCurrentInt(String number) {
        currentNumber = Integer.parseInt(Integer.toString(currentNumber)+number);
    }

    public void clear() {
        previousNumber = 0;
        currentNumber = 0;
    }

    public void pushBackCurrentNumber() {
        previousNumber = currentNumber;
        currentNumber = 0;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

}