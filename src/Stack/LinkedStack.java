/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;
import LinkedList.SinglyLinkedList;

/**
 *
 * @author MIPC
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedStack(){}
    @Override
    public int size(){return list.size();}
    @Override
    public boolean isEmpty(){return list.isEmpty();}
    @Override
    public void push(E element){list.addFirt(element);}
    @Override
    public E top(){return list.first();}
    @Override
    public E pop(){return list.removeFirst();}
    /**
     * Test if delimiters in the given expression are properly matched.
     * @param expression
     * @return 
     */
    public static boolean isMatched(String expression){
        final String opening = "({[";
        final String closing = ")}]";
        Stack<Character> buffer = new LinkedStack<>();
        for(char c:expression.toCharArray()){
            if(opening.indexOf(c) != -1)
                buffer.push(c);
            else if(closing.indexOf(c) != -1){
                if(buffer.isEmpty())
                    return false;
                if(closing.indexOf(c) != opening.indexOf(buffer.pop()))
                    return false;
            }
        }
        return buffer.isEmpty();
    }
    /**
     * TEst if every opening tag has a matching closing tag in HTML string.
     * @param html
     * @return 
     */
    public static boolean isHTMLMatched(String html){
        Stack<String> buffer = new LinkedStack<>();
        int j = html.indexOf('<');
        while(j != -1){
            int k = html.indexOf('>',j + 1);
            if(k== -1)
                return false;
            String tag = html.substring(j + 1,k);
            if(!tag.startsWith("/"))
                buffer.push(tag);
            else{
                if(buffer.isEmpty())
                    return false;
                if(!tag.substring(1).equals(buffer.pop()))
                    return false;
            }
            j = html.indexOf('<', k + 1);
        }
        return buffer.isEmpty();
    }
   /*
    public static void main(String[] args){
        String s = ")(()){([()])}";
        String r= (isMatched(s))? "Verdadero":"falso";
        System.out.print(String.format("¿Los delimitadores en la cadena %s se corresponden correctamente? %s.", s, r));
    }*/
}
