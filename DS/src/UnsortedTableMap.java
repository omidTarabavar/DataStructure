import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K,V> extends AbstractMap<K,V>{
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
    public UnsortedTableMap(){}
    private int findIndex(K key){
        int n = table.size();
        for(int j = 0 ; j < n ; j++){
            if(table.get(j).getKey().equals(key))
                return j;
        }
        return -1;
    }
    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int j = findIndex(key);
        if(j == -1)
            return null;
        return table.get(j).getValue();
    }

    @Override
    public V put(K key, V value) {
        int j = findIndex(key);
        if(j == -1){
            table.add(new MapEntry<>(key,value));
            return null;
        }else {
            return table.get(j).setValue(value);
        }
    }

    @Override
    public V remove(K key) {
        int j = findIndex(key);
        int n = size();
        if(j == -1)
            return null;
        V answer = table.get(j).getValue();
        if(j != (n-1))
            table.set(j,table.get(n-1));
        table.remove(n-1);
        return answer;
    }

    private class EntryIterator implements Iterator<Entry<K,V>>{
        private int j = 0;
        @Override
        public boolean hasNext() {
            return j < table.size();
        }
        @Override
        public Entry<K, V> next() {
            if(j == table.size()) throw new NoSuchElementException();
            return table.get(j++);
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private class EntryIterable implements Iterable<Entry<K,V>>{
        public Iterator<Entry<K,V>> iterator() {return new EntryIterator();}
    }
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    public static void main(String[] args) {
        String text = "ABCAFC";
        UnsortedTableMap<Character,Integer> unsortedTableMap = new UnsortedTableMap<>();
        for(int i = 0 ; i < text.length();i++){
            if(unsortedTableMap.get(text.charAt(i)) == null){
                unsortedTableMap.put(text.charAt(i),1);
            }else {
                unsortedTableMap.put(text.charAt(i),unsortedTableMap.get(text.charAt(i))+1);
            }
        }
        for(Entry<Character,Integer> entry:unsortedTableMap.entrySet()){
            System.out.println(entry.getKey()+" = "+entry.getValue());
        }

    }
}
