import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private Label displayField;

    CalculatorModel calculatorModel;

    public CalculatorController() {
        calculatorModel = new CalculatorModel();
        //updateDisplayField();
    }

    @FXML
    void submitClearPress(ActionEvent event) {
        calculatorModel.clear();
        updateDisplayField();
    }

    @FXML
    void submitNumberPress(ActionEvent event) {
        calculatorModel.addCurrentInt(((Button)event.getSource()).getText());
        updateDisplayField();
    }

    @FXML
    void submitOperatorPress(ActionEvent event) {
        if (calculatorModel.isPreviousStored()) {
            calculatorModel.performComputation();
        } else {
            calculatorModel.setOperator(((Button) event.getSource()).getText());
            calculatorModel.pushBackCurrentNumber();
        }
        updateDisplayField();
    }

    @FXML
    void submitEqualsPress() {
        if (calculatorModel.isPreviousStored()) {
            calculatorModel.performComputation();
            updateDisplayField();
        }
    }

    public void updateDisplayField() {
        displayField.setText(calculatorModel.getCurrentNumber());
    }
}