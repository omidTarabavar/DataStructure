import static java.lang.System.nanoTime;

/**
 * @author omidTarabavar
 * NOTE: bedalil inke bazi az tavabe, Exectuation time balaii dashtn, header outer loop ro taghir dadm
 *       masalan baraye 2^x = x < 1000000000 ro be x < 100 tabdil kardm
 */
public class Tahlil {
    public static void main(String[] args) {
        String[] funcs = {"10x^2   ","x^2     ","2^x     ","7xlog(x)","5log(x) "};
        System.out.println("Time base: Nano Seconds\nFunc Name\t\tavg try 1\t\tavg try 2");
        System.out.println();
        for(int m = 0 ; m< funcs.length;m++) {
            String outPut = funcs[m];
            for (int i = 0; i < 2; i++) {
                switch (m){
                    case 0:{
                        outPut += "\t\t"+tenXPower2();
                        break;
                    }
                    case 1:{
                        outPut += "\t\t"+xPower2();
                        break;
                    }
                    case 2:{
                        outPut += "\t\t"+twoPowerX();
                        break;
                    }
                    case 3:{
                        outPut += "\t\t"+sevenXLogX();
                        break;
                    }
                    case 4:{
                        outPut += "\t\t"+fiveLogX();
                        break;
                    }
                }
            }
            System.out.println(outPut);
        }
    }
    public static long tenXPower2(){
        int t = 0;
        long sum = 0;
        int n = 0;
        for(int x = 1 ; x < 1000000000;x = x*10){
            long startTime = nanoTime();
            for(int y = 1 ; y < 10*x*x;y++){
                t += 1;
            }
            long endTime = nanoTime();
            sum += (endTime - startTime);
            n+=1;
        }
        return sum/n;
    }
    public static long xPower2(){
        int t = 0;
        long sum = 0;
        int n = 0;
        for(int x = 1 ; x < 1000000000;x = x*10){
            long startTime = nanoTime();
            for(int y = 1 ; y < x*x;y++){
                t += 1;
            }
            long endTime = nanoTime();
            sum += (endTime - startTime);
            n+=1;
        }
        return sum/n;
    }
    public static long twoPowerX(){
        int t = 0;
        long sum = 0;
        int n = 0;
        for(int x = 1 ; x < 100;x = x*10){
            long startTime = nanoTime();
            for(int y = 1 ; y < Math.pow(2,x);y++){
                t += 1;
            }
            long endTime = nanoTime();
            sum += (endTime - startTime);
            n+=1;
        }
        return sum/n;
    }
    public static long sevenXLogX(){
        int t = 0;
        long sum = 0;
        int n = 0;
        for(int x = 1 ; x < 100000000;x = x*10){
            long startTime = nanoTime();
            for(int y = 1 ; y < 7*x*Math.log(x);y++){
                t += 1;
            }
            long endTime = nanoTime();
            sum += (endTime - startTime);
            n+=1;
        }
        return sum/n;
    }
    public static long fiveLogX(){
        int t = 0;
        long sum = 0;
        int n = 0;
        for(int x = 1 ; x < 1000000000;x = x*10){
            long startTime = nanoTime();
            for(int y = 1 ; y < 5*Math.log(x);y++){
                t += 1;
            }
            long endTime = nanoTime();
            sum += (endTime - startTime);
            n+=1;
        }
        return sum/n;
    }

}
