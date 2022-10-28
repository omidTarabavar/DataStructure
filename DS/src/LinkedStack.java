/**
 * @author omidTarabavar
 */
public class LinkedStack <E> implements Stack<E>{
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedStack(){}

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E element) {
        list.addFirst(element);
    }

    @Override
    public E top() {
        return list.first();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    public void showPushOrder(){
        Stack<E> newStack = new LinkedStack<>();
        int size = size();
        for(int i = 0 ; i < size;i++){
            E element = pop();
            newStack.push(element);
        }
        System.out.print("Push Order: ");
        for(int j = 0 ; j < size;j++){
            E element = newStack.pop();
            System.out.print(element);
            System.out.print(j != size-1?"-":"\n");
            push(element);
        }
    }

    @Override
    public void printStack() {
        Stack<E> newStack = new LinkedStack<>();
        int size = size();
        for(int i = 0 ; i < size;i++){
            E element = pop();
            System.out.println("|"+element+"|");
            newStack.push(element);
        }
        for(int j = 0 ; j < size;j++){
            push(newStack.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> integerStack = new LinkedStack<>();
        integerStack.push(2);
        integerStack.push(5);
        integerStack.push(1);
        integerStack.push(3);
        integerStack.push(3);
        System.out.println("Original Stack: ");
        integerStack.printStack();
        System.out.println();
        integerStack.showPushOrder();
        System.out.println("\nOriginal Stack after printing push order: ");
        integerStack.printStack();
    }
}
