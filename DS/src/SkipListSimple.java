import java.util.ArrayList;
import java.util.Random;

public class SkipListSimple<K,V> {
    ArrayList<UnsortedTableMap<K,V>> listOfSkipLists;
    public SkipListSimple(){
        listOfSkipLists= new ArrayList<>();
        listOfSkipLists.add(new UnsortedTableMap<>());
    }


    public void insert(K key,V value){
        boolean flag = true;
        Random random = new Random();
        int index = 0;
        do{
            UnsortedTableMap<K,V> unsortedTableMap =listOfSkipLists.get(index);
            if(index == listOfSkipLists.size()-1){
                listOfSkipLists.add(new UnsortedTableMap<>());
            }
            unsortedTableMap.put(key,value);
            boolean coin = random.nextBoolean();
            index += 1;
            if(!coin){
                flag = false;
            }
        }while(flag);

    }
    public void remove(K key){
        int size = listOfSkipLists.size();
        for(int i= 0;i<size;i++){
            UnsortedTableMap<K,V> map = listOfSkipLists.get(i);
            V value = map.remove(key);
            if(value == null)
                break;
        }
    }
    public Entry<K,V> search(K key){
        for(Entry<K,V> entry: listOfSkipLists.get(0).entrySet()){
            if(entry.getKey() == key){
                return entry;
            }
        }
        return null;
    }
    public void printMaps(){
        for(int i = 0 ; i< listOfSkipLists.size();i++){
            System.out.print("Elements of map "+i+": ");
            int index = 0;
            for(Entry<K,V> entry : listOfSkipLists.get(i).entrySet()){
                System.out.print(entry.getKey());
                if(index != listOfSkipLists.get(i).size()-1){
                    System.out.print(" - ");
                }
                index ++;
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        SkipListSimple<Integer,String> skipListSimple = new SkipListSimple<>();
        System.out.println("Intersting: (1,omid)");
        skipListSimple.insert(1,"omid");
        skipListSimple.printMaps();
        System.out.println("\nIntersting: (2,mohammad)");
        skipListSimple.insert(2,"mohammad");
        skipListSimple.printMaps();
        System.out.println("\nIntersting: (3,ali)");
        skipListSimple.insert(3,"ali");
        skipListSimple.printMaps();
        System.out.println("\nDeleting: (2,mohammad)");
        skipListSimple.remove(2);
        skipListSimple.printMaps();
        System.out.println("\nSearching for key 1:");
        System.out.println("value is: "+skipListSimple.search(1).getValue());
    }
}
