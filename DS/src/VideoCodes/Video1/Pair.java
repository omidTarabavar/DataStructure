package VideoCodes.Video1;


public class Pair<A,B> {
    A first;
    B second;
    public Pair(A a,B b){
        first = a;
        second = b;
    }
    public A getFirst(){
        return first;
    }
    public B getSecond(){
        return second;
    }

    public static void main(String[] args) {
        Pair<Integer,Character> AsciiToChar = new Pair<>(49,'0');
        System.out.println(AsciiToChar.getFirst());
        System.out.println(AsciiToChar.getSecond());

    }
}
