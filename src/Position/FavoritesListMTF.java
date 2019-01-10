/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Position;

/**
 * Maintains a list of elements ordered with move-to-front heuristic.
 * @author MIPC
 * @param <E> Any type.
 */
public class FavoritesListMTF<E> extends FavoritesList<E> {
    /**
     * Moves accessed item at Position p to the front of the list.
     * @param p 
     */
    protected void moveUP(Position<Item<E>> p){
        if(p != list.first())
            list.addFirst(list.remove(p));
    }
    @Override
    public Iterable<E> getFavorites(int k)throws IllegalArgumentException{
        if(k < 0 || k > size())
            throw new IllegalArgumentException("Invalid k");
        PositionalList<Item<E>> temp = new LinkedPositionalList<>();
        for(Item<E> item : list)
            temp.addLast(item);
        LinkedPositionalList<E> result = new LinkedPositionalList<>();
        for(int j = 0; j < k;j++){
            Position<Item<E>> highPos = temp.first();
            Position<Item<E>> walk = temp.after(highPos);
            while(walk != null){
                if(count(walk) > count(highPos))
                    highPos = walk;
                walk = temp.after(walk);
            }
            result.addLast(value(highPos));
            temp.remove(highPos);
        }
        return result;
    }
}
