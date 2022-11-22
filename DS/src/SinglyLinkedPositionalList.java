import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * for-each line (260-262) -> Tamrin List mogheiat dar va takrargar ha
 * method isSubSetOf -> Tamrin barasi vojod yek list dar list digar
 */
public class SinglyLinkedPositionalList<E> implements PositionalList<E>,Iterable<E> {

    private class PositionIterator implements Iterator<Position<E>>{
        private Position<E> cursor = first();
        private Position<E> recent = null;
        @Override
        public boolean hasNext() {
            return (cursor != null);
        }

        @Override
        public Position<E> next() throws NoSuchElementException {
            if(cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() throws IllegalStateException{
            if(recent == null) throw new IllegalStateException("nothing to remove");
            SinglyLinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }

    private class PositionIterable implements Iterable<Position<E>>{

        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();
        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }
    @Override
    public Iterator<E> iterator() {
        return  new ElementIterator();
    }

    private static class Node<E> implements Position<E>{
        private Node<E> next;
        private E element;
        Node(E element, Node<E> nextNode){
            this.next = nextNode;
            this.element = element;
        }
        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }
        public Node<E> getNext(){
            return next;
        }
        public void setNext(Node<E> nextNode){
            this.next = nextNode;
        }
        public void setElement(E e){
            element = e;
        }
        private Node<E> getLast(Node<E> head,int size){
            Node<E> currentNode = head;
            for(int i = 0 ; i < size;i++){
                if(currentNode.getNext() == this){
                    return currentNode;
                }else {
                    currentNode = currentNode.getNext();
                }
            }
            return null;
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size=0;

    public SinglyLinkedPositionalList(){
        head = new Node<>(null,null);
        tail = new Node<>(null,null);
        head.next = tail;
    }
    private Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p;
        return node;
    }
    private Position<E> position(Node<E> node){
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Position<E> first() {
        return position(head);
    }

    @Override
    public Position<E> last() {
        return position(tail);
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getLast(head,size));
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    @Override
    public Position<E> addFirst(E e) {
        head = new Node<>(e,head);
        if(size == 0){
            tail = head;
        }
        size++;
        return head;
    }

    @Override
    public Position<E> addLast(E e) {
        Node<E> newest = new Node<>(e,null);
        if(isEmpty()){
            head = newest;
        }else {
            tail.setNext(newest);
        }
        tail = newest;
        size ++;
        return tail;
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> newest = new Node<>(e,node);
        Node<E> last = node.getLast(head,size);
        if(last != null){
            last.setNext(newest);
        }else {
            head = newest;
        }
        size++;
        return newest;
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> newest = new Node<>(e,node.getNext());
        node.setNext(newest);
        size++;
        return newest;
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> before = node.getLast(head,size);
        if(before != null)
            before.next = node.getNext();
        else{
            head = node.getNext();
        }
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        return answer;
    }

    @Override
    public void printPositionalList() {
        Node<E> current = head;
        System.out.print("[");
        for(int i = 0; i< size;i++){
            if(i != size -1)
                System.out.print(current.getElement()+" -> ");
            else
                System.out.print(current.getElement());
            current = current.getNext();
        }
        System.out.println("]");
    }
    public boolean isSubSetOf(SinglyLinkedPositionalList<E> list2,Node<E> currentList1,Node<E> currentList2){
        if(currentList2 == list2.tail && currentList1 != tail)
            return false;
        if(currentList1 == tail.next)
            return true;
        if(currentList1.getElement() == currentList2.getElement())
            return isSubSetOf(list2,currentList1.getNext(),currentList2.getNext());
        else
            return isSubSetOf(list2,head,currentList2.getNext());
    }
    public static void main(String[] args) {
        SinglyLinkedPositionalList<Integer> list1 = new SinglyLinkedPositionalList<>();
        list1.addLast(2);
        list1.addLast(7);
        list1.addLast(3);
        list1.addLast(8);
        SinglyLinkedPositionalList<Integer> list2 = new SinglyLinkedPositionalList<>();
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(7);
        list2.addLast(3);
        list2.addLast(8);
        list2.addLast(9);
        list2.addLast(1);
        System.out.print("List 1: ");
        list1.printPositionalList();
        System.out.print("List 2: ");
        list2.printPositionalList();
        System.out.println("\nIterating list 2 with for-each:");
        for(Integer num:list2){
            System.out.println(num);
        }
        System.out.println();
        System.out.println(list1.isSubSetOf(list2,list1.head,list2.head)? "list 1 is a subset of list 2":"list 1 is not a subset of list 2");

    }
}
