import java.security.PrivateKey;
import java.util.Comparator;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
    public UnsortedPriorityQueue(){
        super();
    }
    public UnsortedPriorityQueue(Comparator<K> comp){
        super(comp);
    }
    private Position<Entry<K,V>> findMin(){
        Position<Entry<K,V>> small = list.first();
        for(Position<Entry<K,V>> walk: list.positions())
            if(compare(walk.getElement(),small.getElement()) < 0)
                small = walk;
        return small;
    }
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key,value);
        list.addLast(newest);
        return newest;
    }

    @Override
    public Entry<K, V> min() {
        if(list.isEmpty()) return null;
        return findMin().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if(list.isEmpty()) return null;
        return list.remove(findMin());
    }

    @Override
    public Iterable<Position<Entry<K, V>>> positions() {
        return list.positions();
    }

    @Override
    public void printPriorityQueue() {
        int size = size();
        for(int i = 0 ; i < size ; i++){
            Entry<K,V> entry = removeMin();
            System.out.println(entry.getValue()+" -> "+entry.getKey());
            printPriorityQueue();
            insert(entry.getKey(), entry.getValue());
        }
    }
}
