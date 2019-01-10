/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EulerTour;

import Position.Position;
import Tree.BinaryTree;

/**
 *
 * @author MIPC
 */
public class PrintExpressionTour extends EulerTour<ExpressionTerm, String>{
    @Override
    public String execute(BinaryTree<ExpressionTerm> T){
        init(T);
        System.out.print("Expression: ");
        eulerTour(T.root());
        System.out.println();
        return null;    //nothing to return
    }
    @Override
    protected void visitLeft(Position<ExpressionTerm> v, TourResult<String> r){
        if(tree.isInternal(v)) System.out.print("(");
    }
    @Override
    protected void visitBelow(Position<ExpressionTerm> v, TourResult<String> r){
        System.out.print(v.getElement());
    }
    @Override
    protected void visitRight(Position<ExpressionTerm> v, TourResult<String> r){
        if(tree.isInternal(v)) System.out.print(")");
    }
}
