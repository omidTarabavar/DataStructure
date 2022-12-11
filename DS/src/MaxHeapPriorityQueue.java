import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class MaxHeapPriorityQueue<K,V> extends AbstractPriorityQueueMax<K,V>{
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
    public MaxHeapPriorityQueue(){super();}
    public MaxHeapPriorityQueue(Comparator<K> comp){super(comp);}
    protected int parent(int j){return (j-1)/2;}
    protected int left(int j){return 2*j+1;}
    protected int right(int j){return 2*j+2;}
    protected boolean hasLeft(int j){return left(j) < heap.size();}
    protected boolean hasRight(int j){return right(j) < heap.size();}
    protected void swap(int i,int j){
        Entry<K,V> temp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);
    }
    protected void upheap(int j){
        while (j > 0){
            int p = parent(j);
            if(compare(heap.get(j),heap.get(p)) < 0)
                break;
            swap(j,p);
            j = p;
        }
    }
    protected void downheap(int j){
        while (hasLeft(j)){
            int leftIndex = left(j);
            int biggerChildIndex = leftIndex;
            if(hasRight(j)){
                int rightIndex = right(j);
                if(compare(heap.get(leftIndex),heap.get(rightIndex)) < 0){
                    biggerChildIndex = rightIndex;
                }
            }
            if(compare(heap.get(biggerChildIndex),heap.get(j)) < 0)
                break;
            swap(j,biggerChildIndex);
            j = biggerChildIndex;
        }
    }
    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key,value);
        heap.add(newest);
        upheap(heap.size()-1);
        return newest;
    }

    @Override
    public Entry<K, V> max() {
        if(heap.isEmpty())
            return null;
        return heap.get(0);
    }

    @Override
    public Entry<K, V> removeMax() {
        if(heap.isEmpty()) return null;
        Entry<K,V> answer = heap.get(0);
        swap(0,heap.size()-1);
        heap.remove(heap.size()-1);
        downheap(0);
        return answer;
    }

    @Override
    public Iterable<Position<Entry<K, V>>> positions() {
        return null;
    }

    @Override
    public void printPriorityQueue() {

    }

    public static void main(String[] args) {
        MaxHeapPriorityQueue<Integer,Integer> maxHeapPriorityQueue = new MaxHeapPriorityQueue<>();
        Random random = new Random();
        for(int i = 0 ; i<  100 ;i ++){
            int num = random.nextInt(1000);
            maxHeapPriorityQueue.insert(num,num);
        }
        for(int i = 0 ; i< 100;i++){
            System.out.print(i !=99 ?""+ maxHeapPriorityQueue.removeMax()+"-":""+maxHeapPriorityQueue.removeMax());
        }
    }
}