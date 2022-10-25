/**
 * @author omidTarabvar
 * NOTE: recur
 */
public class SinglyLinkedList<E>{

    public static class Node<E>{
        private Node<E> next;
        private E element;
        Node(E element,Node<E> nextNode){
            this.next = nextNode;
            this.element = element;
        }
        public E getElement(){
            return element;
        }
        public Node<E> getNext(){
            return next;
        }
        public void setNext(Node<E> nextNode){
            this.next = nextNode;
        }

        @Override
        public String toString() {
            return ""+element;
        }
    }
    private Node<E> head= null;
    private Node<E> tail = null;
    private int size = 0 ;
    public SinglyLinkedList(){}
    public int size(){ return size;}
    public boolean isEmpty(){ return size == 0;}
    public E first(){
        if(isEmpty())
            return null;
        return head.getElement();
    }
    public E last(){
        if(isEmpty())
            return null;
        return tail.getElement();
    }
    public void addFirst(E e){
        head = new Node<>(e,head);
        if(size == 0){
            tail = head;
        }
        size++;
    }
    public void addLast(E e){
        Node<E> newest = new Node<>(e,null);
        if(isEmpty()){
            head = newest;
        }else {
            tail.setNext(newest);
        }
        tail = newest;
        size ++;
    }
    public E removeFirst(){
        if(isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size --;
        if(size == 0){
            tail = null;
        }
        return answer;
    }
    public E removeLast(){
        if(isEmpty()) return null;
        E answer = tail.getElement();
        Node<E> pointer = head;
        if(size == 1){
            head = null;
            tail = null;
        }else {
            tail = getLast(tail);
            tail.next = null;
        }
        size--;
        return answer;
    }
    public Node<E> getLast(Node<E> node){
        Node<E> currentNode = head;
        for(int i = 0 ; i < size;i++){
            if(currentNode.getNext() == node){
                return currentNode;
            }else {
                currentNode = currentNode.getNext();
            }
        }
        return null;
    }
    public void removeDuplicate(){
        Node<E> current = head;
        int originSize = size;
        for(int i = 0 ; i < originSize-1;i++){
            Node<E> next = current.getNext();
            for(int j = i + 1 ; j< originSize;j++){
                if(next.getElement() == current.getElement()){
                    if(tail == next){
                        getLast(next).next = null;
                        tail = getLast(next);
                    }else {
                        getLast(next).next = next.getNext();

                    }
                    size --;
                }
                next = next.getNext();
                if(next == null)
                    break;
            }
            current = current.getNext();
            if(current == null || current == tail)
                break;
        }
    }

    public void printLinkedList(){
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
    public void removeHeadAndTail(){
        removeFirst();
        removeLast();
    }
    public void reverseList(SinglyLinkedList<E> singlyLinkedList,Node<E> current,Node<E> previous){
        Node<E> nextNode = current.next;
        current.setNext(previous);
        if(current == tail){
            Node<E> temp = head;
            head = tail;
            tail = temp;
        }else {
            reverseList(singlyLinkedList,nextNode,current);
        }
    }
    public static void main(String[] args) {
        // yek list peyvandi ijad konid va item haii ra be an ezafe va az an hazf konid:
        SinglyLinkedList<Character> linkedList = new SinglyLinkedList<>();
        linkedList.addLast('r');
        linkedList.addLast('a');
        linkedList.addLast('v');
        linkedList.addLast('a');
        linkedList.addLast('b');
        linkedList.addLast('a');
        linkedList.addLast('r');
        linkedList.addLast('a');
        linkedList.addLast('t');
        System.out.println("Original List: ");
        linkedList.printLinkedList();
        linkedList.reverseList(linkedList,linkedList.head,null);
        System.out.println("\nAfter reverse: ");
        linkedList.printLinkedList();

    }
    public Node<E> getHead(){
        return head;
    }
    public Node<E> getTail(){
        return tail;
    }
    public void setHead(Node<E> head){
        this.head = head;
    }
    public void setTail(Node<E> tail){
        this.tail = tail;
    }
}
