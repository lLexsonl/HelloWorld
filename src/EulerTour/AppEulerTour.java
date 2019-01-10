/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EulerTour;

import Position.Position;
import Tree.LinkedBinaryTree;

/**
 *
 * @author MIPC
 */
public class AppEulerTour {
    @SuppressWarnings("empty-statement")
    public static void main(String[] args){
        /*LinkedBinaryTree<String> T = new LinkedBinaryTree<>();
        Position<String> p0 = T.addRoot("-");
        Position<String> p1 = T.addLeft(p0, "/");
        Position<String> p2 = T.addRight(p0, "+");
        Position<String> p3 = T.addLeft(p1, "*");
        Position<String> p4 = T.addRight(p1, "+");
        Position<String> p5 = T.addLeft(p3, "+");
        Position<String> p6 = T.addRight(p3, "3");
        Position<String> p7 = T.addLeft(p5, "3");
        Position<String> p8 = T.addRight(p5, "1");
        Position<String> p9 = T.addLeft(p4, "-");
        Position<String> p10 = T.addLeft(p4, "2");
        Position<String> p11 = T.addLeft(p9, "9");
        Position<String> p12 = T.addRight(p9, "5");
        Position<String> p13 = T.addLeft(p2, "*");
        Position<String> p14 = T.addRight(p2, "6");
        Position<String> p15 = T.addLeft(p13, "3");
        Position<String> p16 = T.addRight(p13, "-");
        Position<String> p17 = T.addLeft(p16, "7");
        Position<String> p18 = T.addRight(p3, "4");*/
        LinkedBinaryTree<ExpressionTerm> T = new LinkedBinaryTree<>();
        MultiplicationOperator suma1 = new MultiplicationOperator();
        Position<ExpressionTerm> p0 = T.addRoot(suma1);
        ExpressionVariable var1 = new ExpressionVariable(2);
        Position<ExpressionTerm> p1 = T.addLeft(p0, var1);
        ExpressionVariable var2 = new ExpressionVariable(5);
        Position<ExpressionTerm> p2 = T.addRight(p0, var2);
        
        PrintExpressionTour T1 = new PrintExpressionTour();
        T1.execute(T);
        EvaluateExpressionTour E1 = new EvaluateExpressionTour();
        System.out.print(String.format("El resultado de desarrollar todas las operaciones es: %d.\n", E1.execute(T)));;
    }
}
