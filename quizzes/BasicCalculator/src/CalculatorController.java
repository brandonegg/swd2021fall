import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    CalculatorModel calculatorModel;

    public CalculatorController() {
        calculatorModel = new CalculatorModel();
    }

    @FXML
    private Label displayField;

    @FXML
    void submitClearPress(ActionEvent event) {

    }

    @FXML
    void submitNumberPress(ActionEvent event) {
        calculatorModel.addCurrentInt(((Button)event.getSource()).getText());
        updateDisplayField();
    }

    public void updateDisplayField() {
        displayField.setText(Integer.toString(calculatorModel.getCurrentNumber()));
    }
}

/*
submitNumberPress()
submitOperatorPress()

displayField
 */