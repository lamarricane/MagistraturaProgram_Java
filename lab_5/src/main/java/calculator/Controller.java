package calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller {

    String str, first_num, second_num;
    int operator = 0;
    boolean flagFirstOperation = true, flagOfNewOperation = false;

    private void reset() {
        output.setText("");
        str = null;
        first_num = null;
        second_num = null;
        operator = 0;
        flagFirstOperation = true;
    }

    private void equally() {
        str = output.getText();
        switch (operator) {
            case 1 -> {
                second_num = str;
                first_num = String.valueOf(Double.parseDouble(first_num) + Double.parseDouble(second_num));
                output.setText(first_num);
                flagOfNewOperation = true;
            }
            case 2 -> {
                second_num = str;
                first_num = String.valueOf(Double.parseDouble(first_num) - Double.parseDouble(second_num));
                output.setText(first_num);
                flagOfNewOperation = true;
            }
            case 3 -> {
                second_num = str;
                first_num = String.valueOf(Double.parseDouble(first_num) * Double.parseDouble(second_num));
                output.setText(first_num);
                flagOfNewOperation = true;
            }
            case 4 -> {
                second_num = str;
                if (str.equals("0")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Операция запрещена!");
                    alert.showAndWait();
                    reset();
                } else {
                    first_num = String.valueOf(Double.parseDouble(first_num) / Double.parseDouble(second_num));
                    output.setText(first_num);
                    flagOfNewOperation = true;
                }
            }
            case 5 -> {
                if (flagFirstOperation) {
                    flagFirstOperation = false;
                }
                second_num = str;
                first_num = String.valueOf(Math.pow(Double.parseDouble(first_num), Double.parseDouble(second_num)));
                if (first_num.equals("NaN")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Отрицательное число под корнем!");
                    alert.showAndWait();
                    reset();
                } else {
                    output.setText(first_num);
                    flagOfNewOperation = true;
                }
            }
            case 6 -> {
                if (flagFirstOperation) {
                    flagFirstOperation = false;
                }
                str = first_num;
                if (Double.parseDouble(str) < 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Отрицательное число под корнем!");
                    alert.showAndWait();
                    reset();
                } else {
                    first_num = String.valueOf(Math.sqrt(Double.parseDouble(str)));
                    output.setText(first_num);
                    flagOfNewOperation = true;
                }
            }
            default -> {
                first_num = str;
                output.setText("");
            }
        }
        operator = 0;
    }

    @FXML
    private TextField output;

    @FXML
    void sumFunc() {
        if (isDouble(output.getText())) {
            equally();
            operator = 1;
        }
    }

    @FXML
    void minusFunc() {
        if (isDouble(output.getText())) {
            equally();
            operator = 2;
        }
    }

    @FXML
    void multiFunc() {
        if (isDouble(output.getText())) {
            equally();
            operator = 3;
        }
    }

    @FXML
    void divFunc() {
        if (isDouble(output.getText())) {
            equally();
            operator = 4;
        }
    }

    @FXML
    void degreeFunc() {
        if (isDouble(output.getText())) {
            equally();
            operator = 5;
        }
    }

    @FXML
    void sqrtFunc() {
        if (isDouble(output.getText())) {
            if (operator != 0) {
                equally();
            }
            operator = 6;
            equally();
        }
    }

    @FXML
    void resetFunc() {
        reset();
    }

    @FXML
    void equallyFunc() {
        equally();
    }

    @FXML
    void point() {
        output.setText(output.getText() + ".");
    }

    @FXML
    void zero() {
        if (flagOfNewOperation) {
            output.setText("0");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "0");
        }
    }

    @FXML
    void one() {
        if (flagOfNewOperation) {
            output.setText("1");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "1");
        }
    }

    @FXML
    void two() {
        if (flagOfNewOperation) {
            output.setText("2");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "2");
        }
    }

    @FXML
    void three() {
        if (flagOfNewOperation) {
            output.setText("3");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "3");
        }
    }

    @FXML
    void four() {
        if (flagOfNewOperation) {
            output.setText("4");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "4");
        }
    }

    @FXML
    void five() {
        if (flagOfNewOperation) {
            output.setText("5");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "5");
        }
    }

    @FXML
    void six() {
        if (flagOfNewOperation) {
            output.setText("6");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "6");
        }
    }

    @FXML
    void seven() {
        if (flagOfNewOperation) {
            output.setText("7");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "7");
        }
    }

    @FXML
    void eight() {
        if (flagOfNewOperation) {
            output.setText("8");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "8");
        }
    }

    @FXML
    void nine() {
        if (flagOfNewOperation) {
            output.setText("9");
            flagOfNewOperation = false;
        } else {
            str = output.getText();
            output.setText(str + "9");
        }
    }

    private boolean isDouble(String message) {
        try {
            double number = Double.parseDouble(message);
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка в выражении!");
            alert.showAndWait();
            reset();
            return false;
        }
    }
}