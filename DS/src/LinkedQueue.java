import java.util.Scanner;

public class LinkedQueue<E> implements Queue<E>{
    public SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedQueue(){}

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }
    @Override
    public boolean checkItem(Stack<E> stack,E item){
        int size = stack.size();
        boolean flag = false;
        int tempNum = 0;
        for(int i = 1 ; i <=size;i++){
            E currentItem = stack.pop();
            if(currentItem == item){
                flag = true;
            }
            enqueue(currentItem);
            tempNum = i;
            if(flag)
                break;
        }
        for(int j = 0 ; j < tempNum;j++){
            stack.push(dequeue());
        }
        for(int m = 0 ; m < tempNum; m++){
            enqueue(stack.pop());
        }
        for(int l = 0 ; l < tempNum ; l++){
            stack.push(dequeue());
        }
        return flag;
    }
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Stack<Integer> s = new LinkedStack<>();
        Queue<Integer> q = new LinkedQueue<>();
        s.push(3);
        s.push(3);
        s.push(6);
        s.push(2);
        s.push(9);
        System.out.println("Original Stack");
        s.printStack();
        System.out.print("\nEnter the item that you want to find: ");
        int item = keyboard.nextInt();
        System.out.println(q.checkItem(s,item)? item+" found": item+" not found");
        System.out.println("\nStack after checking item");
        s.printStack();

    }
}