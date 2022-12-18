import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
    protected int n = 0;
    protected int capacity;
    private int prime;
    private long scale,shift;
    public AbstractHashMap(int cap,int p){
        prime = p;
        capacity = cap;
        Random rand = new Random();
        scale = rand.nextInt(prime-1)+1;
        shift = rand.nextInt(prime);
        createTable();
    }
    public AbstractHashMap(int cap){this(cap,109345121);}
    public AbstractHashMap(){this(17);}
    public int size(){
        return n;
    }
    public V get(K key){
        return bucketGet(hashValue(key),key);
    }
    public V remove(K key){
        return bucketRemove(hashValue(key),key);
    }
    public V put(K key,V value){
        V answer = bucketPut(hashValue(key),key,value);
        if(n > capacity/2)
            resize(2*capacity-1);
        return answer;
    }
    private int hashValue(K key){
        return (int)((Math.abs(key.hashCode()*scale+shift)% prime)%capacity);
    }
    private void resize(int newCap){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for(Entry<K,V> e:entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable();
        n = 0;
        for(Entry<K,V> e:buffer)
            put(e.getKey(),e.getValue());
    }
    protected abstract void createTable();
    protected abstract V bucketGet(int h,K k);
    protected abstract V bucketPut(int h,K k,V v);
    protected abstract V bucketRemove(int h,K k);

    public static void main(String[] args) {
        String[] hashArray = new String[20];
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> collisionChecker = new ArrayList<>();
        names.add("omidTarabavar");
        names.add("mohammadAli");
        names.add("amirHossein");
        names.add("hamidReza");
        names.add("homayoun");
        int prime = 113;
        Random rand = new Random();
        for(int i = 0 ; i < 5 ; i ++){
            String name = names.get(i);
            int num = 0;
            do {
                int scale = rand.nextInt(prime-1)+1;
                int shift = rand.nextInt(prime);
                num = (((componentSumOfString(name)*scale)+shift)%prime)%hashArray.length;
            }while (collisionChecker.contains(num));
            collisionChecker.add(num);
            hashArray[num] = name;
        }

        for(int i = 0 ;i < hashArray.length;i++){
            if(hashArray[i] != null){
                System.out.println("Name: "+hashArray[i]+"\tIndex: "+i);
            }
        }



    }
    static int componentSumOfString(String s){
        int result = 0;
        for(int i = 0 ; i < s.length();i++){
            result += s.charAt(i);
        }
        return result;
    }
}
