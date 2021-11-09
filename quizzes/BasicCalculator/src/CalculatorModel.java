public class CalculatorModel {

    private Integer previousNumber;
    private String currentNumber;
    private char operator;
    private boolean clearInputNextNumber;

    public CalculatorModel() {
        clearInputNextNumber = false;
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
        pushBackCurrentNumber();
    }

    public void setOperator(String letter) {
        operator = letter.charAt(0);
    }

    public void addCurrentInt(String number) {
        if (clearInputNextNumber) {
            try {
                previousNumber = Integer.valueOf(currentNumber);
            } catch (Exception e) {
                previousNumber = null;
            }
            currentNumber = number;
            clearInputNextNumber = false;
        } else {
            currentNumber = currentNumber + number;
        }
    }

    public void clear() {
        previousNumber = null;
        currentNumber = "";
    }

    public void pushBackCurrentNumber() {
        previousNumber = null;
        clearInputNextNumber = true;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public boolean isPreviousStored() {
        return !(previousNumber==null);
    }
}