/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Position.Position;

/**
 *
 * @author MIPC,Goodrich.
 * @param <E> Any type.
 */
public class ApplicationsTree<E> {
   /* private static class Coordenada {
        private int x = 0;
        private int y = 0;
        Coordenada(){}
        Coordenada(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){ return x;}
        public int getY(){ return y;}
        public void setX(int x){
            this.x = x;
        }
        public void setY(int y){
            this.y = y;
        }
    }*/
    public static void main(String[] args){
        LinkedBinaryTree<String> T = new LinkedBinaryTree<>();
        
        Position<String> p0 = T.addRoot("0");
        Position<String> p1 = T.addLeft(p0, "1");
        Position<String> p2 = T.addRight(p1,"2");
        Position<String> p3 = T.addLeft(p1, "3");
        Position<String> p4 = T.addRight(p3, "4");
        Position<String> p5 = T.addLeft(p2, "5");
        
        printPreorderIndent(T,T.root(),0);
        
        LinkedBinaryTree<String> C = new LinkedBinaryTree<>();/*
        Coordenada c0 = new Coordenada(0,0);
        Position<Coordenada> pc0 = C.addRoot(c0);
        Coordenada c1 = new Coordenada(1, 0);
        Position<Coordenada> pc1 = C.addLeft(pc0, c1);
        Coordenada c2 = new Coordenada(1, 1);
        Position<Coordenada> pc2 = C.addRight(pc1, c2);
        
        layout(C, C.root(), 0 , 0);*/
        
    }
    /**
     * Prints preorder representation of subtree of T rooted at p having depth d.
     * @param <E>
     * @param T
     * @param p
     * @param d 
     */
    public static <E> void printPreorderIndent(Tree<E> T, Position<E> p, int d){
       System.out.println(spaces(2*d) + p.getElement());
       for(Position<E> c : T.children(p))
           printPreorderIndent(T, c, d + 1);
    }
    /**
     * Produces a String of n spaces.
     * @param <E>
     * @param n
     * @return 
     */
    public static <E> String spaces(int n){
        StringBuilder space = new StringBuilder();
        for(int i = 0; i < n; i++)
            space.append(" ");
        String str = space.toString();
        return str;
    }
    /**
     * Prints parenthesized representation of subtree of T rooted at p.
     * @param <E>
     * @param T
     * @param p 
     */
    public static <E> void parenthesize(Tree<E> T, Position<E> p){
        System.out.print(p.getElement());
        if(T.isInternal(p)){
            boolean firstTime = true;
            for(Position<E> c : T.children(p)){
                System.out.print((firstTime ? " (" : ", "));
                firstTime = false;
                parenthesize(T, c);
            }
            System.out.print(")");
        }
    }/*
    public static <E> int layout(BinaryTree<E> T, Position<E> p, int d, int x){
        if(T.left(p) != null)
            x = layout(T, T.left(p), d + 1, x);
        p.getElement().setX(x + 1);
        p.getElement().setY(d);
        if(T.right(p) != null)
            x = layout(T, T.right(p), d + 1, x);
        return x;
    }*/
}
