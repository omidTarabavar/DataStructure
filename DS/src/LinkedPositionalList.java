import java.util.Scanner;

public class LinkedPositionalList<E> implements PositionalList<E> {

    private static class Node<E> implements Position<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e,Node<E> p,Node<E> n){
            element = e;
            prev = p;
            next = n;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if(next == null)
                throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public Node<E> getPrev(){
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public String toString(){return ""+element;}
    }
    private Node<E> header;
    private Node<E> trailer;
    private int size=0;

    public LinkedPositionalList(){
        header = new Node<>(null,null,null);
        trailer = new Node<>(null,header,null);
        header.setNext(trailer);
    }
    private Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node =(Node<E>) p;
        if(node.getNext() == null)
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    private Position<E> position(Node<E> node){
        if(node == header || node == trailer)
            return null;
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
        return position(header.getNext());
    }

    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e,Node<E> pred,Node<E> succ){
        Node<E> newest = new Node<>(e,pred,succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e,header,header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e,trailer.getPrev(),trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node= validate(p);
        return addBetween(e,node.getPrev(),node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e,node,node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }
    public void merge(LinkedPositionalList<E> list2){
        this.trailer.getPrev().setNext(validate(list2.first()));
        validate(list2.first()).setPrev(trailer.getPrev());
        this.trailer = list2.trailer;
        size += list2.size();
    }
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }

    @Override
    public void printPositionalList() {
        Node<E> currentItem = header.getNext();
        System.out.print("[");
        for(int i = 0 ; i < size;i++){
            System.out.print(i != size-1 ? currentItem+",":currentItem);
            currentItem = currentItem.getNext();
        }
        System.out.println("]");
    }
    public void join(LinkedPositionalList<E> list2){
        trailer.getPrev().setNext(list2.header.getNext());
        list2.header.getNext().setPrev(trailer.getPrev());
        trailer = list2.trailer;
    }
    public static void main(String[] args) {
        PositionalList<Integer> positionalList = new LinkedPositionalList<>();
        Scanner keyboard= new Scanner(System.in);
//        System.out.print("Enter item you want to store: ");
//        int item = keyboard.nextInt();
//        Position<Integer> positionItem = positionalList.addLast(item);
//        positionalList.addLast(2);
//        positionalList.addLast(3);
//        positionalList.addLast(1);
//        positionalList.addFirst(4);
//        positionalList.addFirst(6);
//        System.out.print("Original List: ");
//        positionalList.printPositionalList();
//        System.out.print("Enter item you want to add before "+item+": ");
//        int beforeItem = keyboard.nextInt();
//        System.out.print("Enter item you want to add after "+item+": ");
//        int afterItem = keyboard.nextInt();
//        positionalList.addBefore(positionItem,beforeItem);
//        positionalList.addAfter(positionItem,afterItem);
//        System.out.print("\nFinal List: ");
//        positionalList.printPositionalList();
        LinkedPositionalList<Integer> list1 = new LinkedPositionalList<>();
        list1.addLast(1);
        list1.addLast(2);
        LinkedPositionalList<Integer> list2 = new LinkedPositionalList<>();
        list2.addLast(3);
        list2.addLast(4);
        list1.merge(list2);
        list1.printPositionalList();
    }
}
