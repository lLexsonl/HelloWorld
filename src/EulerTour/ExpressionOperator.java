/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EulerTour;

/**
 * Class for an operator of an arithmetic expression.
 * @author MIPC
 */
public class ExpressionOperator extends ExpressionTerm{
    protected Integer firstOperand, secondOperand;
    public void setOperand(Integer x, Integer y){
        firstOperand = x;
        secondOperand = y;
    }
}
