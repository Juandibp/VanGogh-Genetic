package Setup;
import Setup.ImageLoader;
public class EuclideanDistanceCalculator {
   public static double EuclideanDistanceCalculator(double[] ImageA, double[] ImageB)
    {
        double Sum = 0.0;
        for(int i=0;i<ImageA.length;i++) {
           Sum = Sum + Math.pow((ImageA[i]-ImageB[i]),2.0);
        }
        return Math.sqrt(Sum);
    }
    
}
