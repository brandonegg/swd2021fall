public class CalculatorModel {

    private Integer previousNumber;
    private String currentNumber;
    private char operator;

    public CalculatorModel() {
        previousNumber = null;
        currentNumber = "";
        operator = '0';
    }

    public void performComputation() {
        Integer currentNumberInteger = Integer.valueOf(currentNumber);
        switch(operator){
            case '+':   currentNumber = Integer.toString(previousNumber+currentNumberInteger);
                        break;
            case '-':   currentNumber = Integer.toString(previousNumber-currentNumberInteger);
                        break;
            case '*':   currentNumber = Integer.toString(previousNumber*currentNumberInteger);
                        break;
            case '/':   currentNumber = Integer.toString(previousNumber/currentNumberInteger);
            default:    break;
        }
        previousNumber = null;
        operator = '0';
    }

    public void setOperator(String letter) {
        operator = letter.charAt(0);
    }

    public void addCurrentInt(String number) {
        currentNumber = currentNumber+number;
    }

    public void clear() {
        previousNumber = null;
        currentNumber = "";
    }

    public void pushBackCurrentNumber() {
        previousNumber = Integer.valueOf(currentNumber);
        currentNumber = "";
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public boolean isPreviousStored() {
        return !(previousNumber==null);
    }
}