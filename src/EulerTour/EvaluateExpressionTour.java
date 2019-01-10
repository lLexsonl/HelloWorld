/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EulerTour;

import Position.Position;
import Tree.BinaryTree;

/**
 * Compute the value of an arithmetic expression tree.
 * @author MIPC
 */
public class EvaluateExpressionTour extends EulerTour<ExpressionTerm, Integer>{
    @Override
    public Integer execute(BinaryTree<ExpressionTerm> T){
        init(T);    //calls method of superclass.
        return eulerTour(tree.root());  //returns the value of the expression.
    }
    @Override
    protected void visitRight(Position<ExpressionTerm> v, TourResult<Integer> r){
        ExpressionTerm term = v.getElement();
        if(tree.isInternal(v)){
            ExpressionOperator op = (ExpressionOperator) term;
            op.setOperand(r.left, r.right);
        }
        r.out = term.getValue();
    }
}
