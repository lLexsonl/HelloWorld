/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EulerTour;

/**
 * Class for a variable of an arithmetic expression.
 * @author MIPC
 */
public class ExpressionVariable extends ExpressionTerm{
    protected Integer var;
    public ExpressionVariable(Integer x){ var = x;}
    public void setVariable(Integer x){ var = x;}
    @Override
    public Integer getValue(){ return var;}
    @Override
    public String toString(){ return var.toString();}
}
