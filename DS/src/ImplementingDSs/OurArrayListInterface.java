package ImplementingDSs;

public class OurArrayListInterface<E> {
    E[] customArray;
    final int MAX = 2;
    int size;
    int capacity;
    public OurArrayListInterface(int size){
        this.size = 0;
        customArray = (E[]) new Object[size];
        this.capacity = size;
    }
    public OurArrayListInterface(){
        this.size = 0;
        customArray = (E[]) new Object[MAX];
        this.capacity = MAX;
    }
    private E[] copyArray(){
        E[] newArray = (E[]) new Object[(2*capacity)];
        for(int i = 0 ; i < capacity ; i ++){
            newArray[i] = customArray[i];
        }
        return newArray;
    }
    public void add(E item){
        if(size < capacity){
            customArray[size] = item;
        }
        else {
            E[] newArray = copyArray();
            newArray[capacity] = item;
            capacity = capacity *2;
            customArray = newArray;
        }
        size++;
    }
    public void insert(E item,int index) throws IllegalArgumentException{
        if(index < 0 || index >= capacity)
            throw new IllegalArgumentException();
        if(size == capacity-1){
            customArray = copyArray();
            capacity *= 2;
        }
        for(int i = size-1 ; i >=index;i--){
            customArray[i+1] = customArray[i];
        }
        customArray[index] = item;
        size++;
    }
    public void remove(E item) throws IllegalArgumentException{
        boolean flag = false;
        for(int i = 0 ; i < size-1;i++){
            if(customArray[i] == item){
                flag = true;
            }
            if(flag){
                customArray[i] = customArray[i+1];
            }
        }
        customArray[(size-1)] = null;
        size--;
    }
    public void printArray(){
        System.out.print("[");
        for(int i = 0 ; i < size;i++){
            System.out.print(i != size-1?""+(customArray[i])+"-":""+(customArray[i]));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        OurArrayListInterface<Integer> list = new OurArrayListInterface<>();
        list.printArray();
        list.add(1);
        list.printArray();
        list.add(2);
        list.printArray();
        list.add(3);
        list.printArray();
        list.insert(4,0);
        list.printArray();

    }
}
