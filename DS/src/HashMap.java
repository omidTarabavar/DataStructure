public class HashMap<K,V> extends AbstractHashMap<K,V>{
    AbstractHashMap<K,V> hashTable;
    public HashMap(String type){
        if(type.equals("Chain")){
            hashTable = new ChainHashMap<>();
        }else if(type.equals("Probe")){
            hashTable = new ProbeHashMap<>();
        }
    }
    @Override
    protected void createTable() {
        if(hashTable != null)
            hashTable.createTable();
    }

    @Override
    protected V bucketGet(int h, K k) {
        return hashTable.bucketGet(h,k);
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        return hashTable.bucketPut(h,k,v);
    }

    @Override
    protected V bucketRemove(int h, K k) {
        return hashTable.bucketRemove(h,k);
    }

    public static void main(String[] args) {
        HashMap<String,Integer> hashMap1 = new HashMap<>("Chain");
        HashMap<String,Integer> hashMap2 = new HashMap<>("Probe");
        System.out.println("Putting\t\tKey: omid\t\tValue: 19\t\tin hashMap1");
        hashMap1.put("omid",19);
        System.out.println("Putting\t\tKey: mohammad\t\tValue: 15\t\tin hashMap2");
        hashMap2.put("mohammad",15);
        System.out.println();
        System.out.println("Searching for value of (Key: omid) in hashMap1 -> Value: "+hashMap1.get("omid"));
        System.out.println("Searching for value of (Key: mohammad) in hashMap2 -> Value: "+hashMap2.get("mohammad"));
        System.out.println();
        System.out.println("Type of hashMap1: "+hashMap1);
        System.out.println("Type of hashMap2: "+hashMap2);
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return hashTable.entrySet();
    }

    @Override
    public String toString() {
        return hashTable.toString();
    }
}
