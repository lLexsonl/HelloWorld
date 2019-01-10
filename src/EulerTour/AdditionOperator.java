/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EulerTour;

/**
 * Class for the addition operator in an arithmetic expression.
 * @author MIPC
 */
public class AdditionOperator extends ExpressionOperator{
    @Override
    public Integer getValue(){
        return (firstOperand + secondOperand);  //unboxing and then autoboxing.
    }
    @Override
    public String toString(){ return "+";}
}
