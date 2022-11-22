import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AbstractTree <E> implements Tree<E>{
    @Override
    public Position<E> root() {
        return null;
    }
    @Override
    public Position<E> parent(Position<E> p) {
        return null;
    }
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        return null;
    }
    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        return 0;
    }
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) >0;
    }
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }
    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }
    @Override
    public int size() {
        return 0;
    }
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> positionIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }
    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }

    public int depth(Position<E> p){
        if(isRoot(p))
            return 0;
        else
            return 1+depth(parent(p));
    }
    public int height(Position<E> p){
        int h = 0;
        for(Position<E> c:children(p))
            h = Math.max(h,1+height(c));
        return h;
    }
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
        snapshot.add(p);
        for(Position<E> c:children(p))
            preorderSubtree(c,snapshot);
    }
    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            preorderSubtree(root(),snapshot);
        return snapshot;
    }
    private void postorderSubtree(Position<E> p,List<Position<E>> snapshot){
        for(Position<E> c:children(p))
            postorderSubtree(c,snapshot);
        snapshot.add(p);
    }
    public Iterable<Position<E>> postorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            postorderSubtree(root(),snapshot);
        return snapshot;
    }
    public Iterable<Position<E>> postorder(Position<E> position){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            postorderSubtree(position,snapshot);
        return snapshot;
    }
}
