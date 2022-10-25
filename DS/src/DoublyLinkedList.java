import java.util.Scanner;

/**
 * @author omidTarabavar
 * NOTE: halat khas : agar starting value find shod vali ending find nashod -> az starting ta akhar list cut mikone
 *                    agar ending find shod vali starting find nashod -> az aval list ta ending (va khod ending) cut mikone
 */
public class DoublyLinkedList<E>{
    private static class Node<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e,Node<E> p,Node<E> n){
            element = e;
            prev = p;
            next = n;
        }
        public E getElement(){ return element;}
        public Node<E> getPrev(){ return prev;}
        public Node<E> getNext(){ return next;}
        public void setPrev(Node<E> p) { prev = p;}
        public void setNext(Node<E> n) { next = n;}
        public String toString(){return ""+element;}
    }
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    public DoublyLinkedList(){
        header = new Node<>(null,null,null);
        trailer = new Node<>(null,header,null);
        header.setNext(trailer);
    }
    public int size(){ return size;}
    public boolean isEmpty(){ return size == 0;}
    public E first(){
        if(isEmpty()) return null;
        return header.getNext().getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return trailer.getPrev().getElement();
    }
    public void addFirst(E e){
        addBetween(e, header,header.getNext());
    }
    public void addLast(E e){
        addBetween(e, trailer.getPrev(),trailer);
    }
    public E removeFirst(){
        if (isEmpty()) return null;
        return remove(header.getNext());
    }
    public E removeLast(){
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }
    private void addBetween(E e , Node<E> predecessor,Node<E> successor){
        Node<E> newest = new Node<>(e,predecessor,successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
    private E remove(Node<E> node){
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addLast(1);
        doublyLinkedList.addLast(3);
        doublyLinkedList.addLast(5);
        doublyLinkedList.addLast(8);
        doublyLinkedList.addLast(9);
        System.out.println("Original List: ");
        doublyLinkedList.printDoublyLinkedList();
        System.out.print("\nEnter subValue 1: ");
        int number1 = keyboard.nextInt();
        System.out.print("Enter subValue 2: ");
        int number2 = keyboard.nextInt();

        DoublyLinkedList<Integer> subedList = doublyLinkedList.subList(number1,number2);
        System.out.println("\nOriginal List After Cutting: ");
        doublyLinkedList.printDoublyLinkedList();
        System.out.println("\nSubbed List: ");
        subedList.printDoublyLinkedList();
    }
    public void printDoublyLinkedList(){
        System.out.print("[");
        Node<E> current = header.getNext();
        while (current != trailer){
            if(current != trailer.getPrev())
                System.out.print(current+" <-> ");
            else
                System.out.print(current);
            current = current.getNext();
        }
        System.out.print("]\n");
    }
    public DoublyLinkedList<E> subList(E num1,E num2){
        DoublyLinkedList<E> subedList = new DoublyLinkedList<>();
        Node<E> current = header.getNext();
        boolean flagFindFirst= false;
        while (current != trailer){
            if(flagFindFirst){
                subedList.addLast(current.getElement());
                remove(current);
            }
            else if(current.getElement() == num1){
                flagFindFirst = true;
                subedList.addLast(current.getElement());
                remove(current);
            }
            if(current.getElement() == num2){
                if(flagFindFirst)
                    break;
                else {
                    Node<E> current2 = header.getNext();
                    while (current2 != current.getNext()){
                        subedList.addLast(current2.getElement());
                        remove(current2);
                        current2 = current2.getNext();
                    }
                    break;
                }
            }
            current = current.getNext();
        }
        return subedList;
    }
}
