public interface Queue<E> {
    int size();
    boolean isEmpty();
    E first();
    void enqueue(E e);
    E dequeue();
    boolean checkItem(Stack<E> stack,E item);
}
