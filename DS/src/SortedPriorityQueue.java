import java.util.Comparator;
import java.util.Random;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
    public SortedPriorityQueue(){
        super();
    }
    public SortedPriorityQueue(Comparator<K> comp){
        super(comp);
    }
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key,value);
        Position<Entry<K,V>> walk = list.last();
        while (walk != null && compare(newest,walk.getElement()) < 0)
            walk = list.before(walk);
        if(walk == null)
            list.addFirst(newest);
        else
            list.addAfter(walk,newest);
        return newest;
    }


    @Override
    public Entry<K, V> min() {
        if(list.isEmpty()) return null;
        return list.first().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if(list.isEmpty()) return null;
        return list.remove(list.first());
    }

    @Override
    public Iterable<Position<Entry<K, V>>> positions() {
        return list.positions();
    }

    public static boolean checkTasks(PriorityQueue<Integer,String> jobScheduler){
        for(Position<Entry<Integer,String>> entry: jobScheduler.positions()){
            if(entry.getElement().getKey() != 0){
                return false;
            }
        }
        return true;
    }
    public static void usage(PriorityQueue<Integer,String> jobSch){
        Random random = new Random();
        int size = jobSch.size();
        Entry<Integer,String> min = jobSch.removeMin();
        if(min != null) {
            if(min.getKey() != 0) {
                int time = random.nextInt(min.getKey())+1;
                System.out.println(min.getValue() + " use " + time + " seconds");
                usage(jobSch);
                if (time <= min.getKey())
                    jobSch.insert(min.getKey() - time, min.getValue());
                else
                    jobSch.insert(min.getKey(), min.getValue());
            }else {
                usage(jobSch);
                jobSch.insert(min.getKey(), min.getValue());
            }
        }
    }
    @Override
    public  void printPriorityQueue(){
        int size = size();
        Entry<K,V> entry = removeMin();
        if(entry != null) {
            System.out.println(entry.getValue() + "\tRemaining:  " + entry.getKey()+" seconds");
            printPriorityQueue();
            insert(entry.getKey(), entry.getValue());
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Integer,String> jobScheduler = new SortedPriorityQueue<>();
        jobScheduler.insert(40,"Chrome");
        jobScheduler.insert(15,"Paint");
        jobScheduler.insert(80,"KmPlayer");
        jobScheduler.insert(60,"IntelliJ");
        jobScheduler.insert(30,"Adobe");
        jobScheduler.printPriorityQueue();
        System.out.println();
        while (!checkTasks(jobScheduler)){
            usage(jobScheduler);
            System.out.println("\n--------------------------------------------");
            jobScheduler.printPriorityQueue();
            System.out.println("--------------------------------------------\n");
        }

    }
}
