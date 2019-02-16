/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ueb08;

/**
 *
 * @author alex
 */
public class Calculator {

    double number1;
    double number2;
    Double m = 0.0;
    String operator;

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    void addM(double number) {
        this.m += number;
    }

    String stringResultM() {
        return this.m.toString();
    }

    void subM(double number) {
        this.m -= number;
    }

    void resetM() {
        this.m = 0.0;
    }

    Double operateNumbers() {
        Double result = 0.0;
        switch (this.operator) {
            case "+": {
                result = this.number1 + this.number2;
                break;
            }

            case "-": {
                result = this.number1 - this.number2;
                break;
            }

            case "*": {

                result = this.number1 * this.number2;
                break;
            }

            case "/": {

                if (this.number2 == 0) {
                    throw new ArithmeticException();
                } else {
                    result = this.number1 / this.number2;
                }
                break;
            }

        }
        return result;
    }
}
