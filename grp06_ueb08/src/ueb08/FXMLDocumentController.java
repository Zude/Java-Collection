/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ueb08;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author alex
 */
public class FXMLDocumentController implements Initializable {

    Calculator myResult = new Calculator();

    // Damit nach einen "=" und eingabe einer Zahl der Bildschirm geleert wird  .
    Boolean testForNewNumber = true;
    Double m = 0.0;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btnSub;
    @FXML
    private Button btn0;
    @FXML
    private Button btnDot;
    @FXML
    private Button btnCalc;
    @FXML
    private Button btmAdd;
    @FXML
    private Button btn4;
    @FXML
    private Button btnDelSav;
    @FXML
    private Button btnDel;
    @FXML
    private Button btn7;
    @FXML
    private Button btnDelAll;
    @FXML
    private Button btnReadSav;
    @FXML
    private Button btnAddSav;
    @FXML
    private Button btnSubSav;
    @FXML
    private Button btn8;
    @FXML
    private Button btn5;
    @FXML
    private Button btn9;
    @FXML
    private Button btn6;
    @FXML
    private Button btnDiv;
    @FXML
    private Button btnMult;
    @FXML
    private TextField txtFldDisplay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Hier kann etwas für den Start geschrieben werden
    }

    @FXML
    private void handleBtnDIgits(ActionEvent event) {
        if (testForNewNumber) {
            txtFldDisplay.setText(" ");
            testForNewNumber = false;
        }
        String oldtext = txtFldDisplay.getText();
        txtFldDisplay.setText(oldtext + ((Button) event.getSource()).getText());
    }

    @FXML
    private void handleBtnDot(ActionEvent event) {

        if (!txtFldDisplay.getText().contains(".")) {
            String oldtext = txtFldDisplay.getText();
            txtFldDisplay.setText(oldtext + ".");
        }
    }

    @FXML
    private void handleBtnOperators(ActionEvent event) {
        //Falls nach einem "=" ein Operator folgt, muss testForNewNumber wieder false sein
        if (testForNewNumber) {
            testForNewNumber = false;
        }
        //TODO DONE erst null-prüfen, dann Inhalt
        //TODO DONE nach Fehlermeldung muss weitergerechnet werden können (9/0 -> Fehlermeldung)
        try {
            if ((txtFldDisplay.getText() != null) && (txtFldDisplay.getText() != " ")) {
                if (myResult.getOperator() != null && myResult.getOperator() != "=") {
                    myResult.setNumber2(Double.valueOf(txtFldDisplay.getText()));

                    myResult.setNumber1(myResult.operateNumbers());
                    myResult.setNumber2(0.0);
                } //TODO DONE nur spezielle Exception abfangen
                else {
                    myResult.setNumber1(Double.valueOf(txtFldDisplay.getText()));
                }
            }

            myResult.setOperator(((Button) event.getSource()).getText());
            txtFldDisplay.setText(" ");
        } catch (ArithmeticException e) {
            txtFldDisplay.setText("ArithmeticException: " + e);
            myResult.setNumber1(0.0);
            myResult.setNumber2(0.0);
            testForNewNumber = true;
            myResult.setOperator("=");

        }
    }

    @FXML
    private void handleBtnCalc(ActionEvent event) {
        //TODO DONE erst null-prüfen, dann Inhalt

        // Für den Fall das Ohne Operator einfach "=" gedrückt wird oder nach einem "=" nochmals
        if ((myResult.getOperator() == null) || (myResult.getOperator() == "=")) {
            testForNewNumber = true;
        } else {

            try {
                myResult.setNumber2(Double.valueOf(txtFldDisplay.getText()));

                txtFldDisplay.setText(myResult.operateNumbers().toString());

            } catch (ArithmeticException e) {
                txtFldDisplay.setText("ArithmeticException: " + e);

                myResult.setNumber1(0.0);
                myResult.setNumber2(0.0);

            }
            myResult.setOperator("=");
            testForNewNumber = true;
            // Damit bei einem 2ten "=" nichts passiert siehe if 

        }
    }

    @FXML
    private void handleBtnDelSav(ActionEvent event) {
        //TODO DONE Speicher mit in Calculator verlegen
        myResult.resetM();
    }

    @FXML
    private void handleBtnDel(ActionEvent event) {

        txtFldDisplay.setText("0.0");
        testForNewNumber = true;
    }

    @FXML
    private void handleBtnDellAll(ActionEvent event) {
        txtFldDisplay.setText("0.0");
        myResult.setNumber1(0.0);
        myResult.setNumber2(0.0);
        myResult.setOperator(null);
        testForNewNumber = true;
    }

    @FXML
    private void handleBtnReadSav(ActionEvent event) {
        txtFldDisplay.setText(myResult.stringResultM());
        testForNewNumber = true;
    }

    @FXML
    private void handleBtnAddSav(ActionEvent event) {
        myResult.addM(Double.valueOf(txtFldDisplay.getText()));
        testForNewNumber = true;
    }

    @FXML
    private void handleBtnSubSav(ActionEvent event) {
        myResult.subM(Double.valueOf(txtFldDisplay.getText()));
        testForNewNumber = true;
    }

}
