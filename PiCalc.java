public class PiCalc {
    public  static void main(String[] args){

        int its = Integer.parseInt(args [1]);
        double x, y;
        double rad = Double.parseDouble(args [0]);
        double xy;                          /*sqrt(sqr(x2 - x1) + sqr(y2 - y1))   (x1,y1)=(0,0)*/
        double count = 0;
        double pi;


        for(int i = 0; i < its; i++){
            x = (double)(Math.random()*rad);
            y = (double)(Math.random()*rad);
            xy = Math.sqrt((x * x) + (y * y));
            System.out.println("(" + x + "," + y + ")  " + xy);
            if(xy <= rad){
                count++;
            }

        }
        pi = ((count/its)*4);
        System.out.println(count + "\nPI = " + pi + "\n(" + Math.PI + ")");



    }
}
