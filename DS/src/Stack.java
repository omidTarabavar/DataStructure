/**
 * @author omidTarabavar
 */
public interface Stack<E>{
    int size();
    boolean isEmpty();
    E top();
    void push(E element);
    E pop();
    void printStack();
    void showPushOrder();
}
