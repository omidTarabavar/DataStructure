import java.util.Comparator;

public abstract class AbstractPriorityQueueMax<K,V> implements MaxPriorityQueue<K,V>{
    protected static class PQEntry<K,V> implements Entry<K,V>{
        private K k;
        private V v;
        public PQEntry(K key,V value){
            k = key;
            v = value;
        }
        @Override
        public V getValue() {
            return v;
        }

        @Override
        public K getKey() {
            return k;
        }
        protected void setKey(K key){
            k = key;
        }
        protected void setValue(V value){
            v = value;
        }

        @Override
        public String toString() {
            return ""+v;
        }
    }
    private Comparator<K> comp;
    protected AbstractPriorityQueueMax(Comparator<K> c){
        comp = c;
    }
    protected AbstractPriorityQueueMax(){
        this(new DefaultComparator<>());
    }

    protected int compare(Entry<K,V> a,Entry<K,V> b){
        return comp.compare(a.getKey(),b.getKey());
    }
    protected boolean checkKey(K key) throws IllegalArgumentException{
        try {
            return (comp.compare(key,key) == 0);
        }catch (ClassCastException e){
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
