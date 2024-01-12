import java.util.ArrayList;
import java.util.Comparator;

public class SortedTableMap<K,V> extends AbstractSortedMap<K,V> {

    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
    public SortedTableMap(){
        super();
    }
    public SortedTableMap(Comparator<K> comp){
        super(comp);
    }
    private int findIndex(K key,int low,int high){
        if(high < low)
            return high+1;
        int mid = (low+high) /2;
        int comp = compare(key,table.get(mid));
        if(comp == 0){
            return mid;
        }
        else if(comp < 0)
            return findIndex(key,low,mid-1);
        else
            return findIndex(key,mid+1,high);
    }
    private int findIndex(K key){
        return findIndex(key,0, table.size()-1);
    }
    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        checkKey(key);
        int j = findIndex(key);
        if(j==size() || compare(key,table.get(j)) != 0)
            return null;
        return table.get(j).getValue();
    }

    @Override
    public V put(K key, V value) {
        checkKey(key);
        int j = findIndex(key);
        if(j < size() && compare(key,table.get(j)) == 0)
            return table.get(j).setValue(value);
        table.add(j, new MapEntry<>(key, value));
        return null;
    }

    @Override
    public V remove(K key) {
        checkKey(key);
        int j = findIndex(key);
        if ( j == size() || compare(key,table.get(j)) != 0)
            return null;
        return table.remove(j).getValue();
    }

    private Entry<K,V> safeEntry(int j){
        if(j < 0 || j >= table.size())
            return null;
        return table.get(j);
    }
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return snapShot(0,null);
    }

    @Override
    public Entry<K, V> firstEntry() {
        return safeEntry(0);
    }

    @Override
    public Entry<K, V> lastEntry() {
        return safeEntry(table.size()-1);
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key));
    }

    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        int j = findIndex(key);
        if(j == size() || !key.equals(table.get(j).getKey()))
            j--;
        return safeEntry(j);
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key)-1);
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        int j = findIndex(key);
        if(j < size() && key.equals(table.get(j).getKey()))
            j++;
        return safeEntry(j);
    }
    private Iterable<Entry<K,V>> snapShot(int startIndex,K stop){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        int j = startIndex;
        while (j < table.size() && (stop == null || compare(stop,table.get(j)) > 0))
            buffer.add(table.get(j++));
        return buffer;
    }
    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        return snapShot(findIndex(fromKey),toKey);
    }
    public void removeSubMap(K k1,K k2){
        for(Entry<K,V> entry:subMap(k1,k2)){
            remove(entry.getKey());
        }
    }
    public void printMap(K k1,K k2){
        int indexFirst = findIndex(k1);
        int indexLast = findIndex(k2);
        for(int i = indexFirst ; i < indexLast+1;i++) {
            Entry<K, V> entry = table.get(i);
            System.out.print("(" + entry.getKey() + "," + entry.getValue() + ")");
            System.out.print(i != indexLast ? " - " : "\n");
        }
    }
    public void printMap(){
        printMap(firstEntry().getKey(),lastEntry().getKey());
    }
    public void printSubMap(K k1,K k2){
        int last = findIndex(k2)-1;
        int temp= 0;
        for (Entry<K,V> entry:subMap(k1,k2)){
            System.out.print("(" + entry.getKey() + "," + entry.getValue() + ")");
            temp ++;
            if(temp < last)
                System.out.print(" - ");
            else
                System.out.println();
        }
    }
    public static void main(String[] args) {
        SortedTableMap<Integer,String> sortedTableMap = new SortedTableMap<>();
        sortedTableMap.put(1,"Omid");
        sortedTableMap.put(2,"Ali");
        sortedTableMap.put(3,"Mohammad");
        sortedTableMap.put(4,"Ehsan");
        sortedTableMap.put(5,"Amir");
        sortedTableMap.put(6,"Milad");
        System.out.print("Our List: ");
        sortedTableMap.printMap();

        System.out.println("Removing subset 2-5");
        System.out.print("Subset: ");
        sortedTableMap.printSubMap(2,5);
        sortedTableMap.removeSubMap(2,5);
        System.out.print("New version of List: ");
        sortedTableMap.printMap();

    }
}