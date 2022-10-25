public class Recursive {
    public static void main(String[] args) {
        String s = "sutechhcetus"; // -> must be true
        String m = "RaceCaR"; // -> must be false
        System.out.println(recursiveChecker(s,0,s.length()-1));
        System.out.println(recursiveChecker(m,0,m.length()-1));
    }
    public static boolean recursiveChecker(String input,int indexFirst,int indexLast){
        if(indexFirst <= indexLast) {
            if (input.charAt(indexFirst) == input.charAt(indexLast)) {
                return recursiveChecker(input, indexFirst + 1, indexLast - 1);
            } else {
                return false;
            }
        }else {
            return true;
        }
    }
}