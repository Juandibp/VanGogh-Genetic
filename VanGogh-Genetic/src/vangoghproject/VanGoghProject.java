package vangoghproject;

import Setup.*;
import java.io.File;
import java.util.ArrayList;

public class VanGoghProject {
    public static int genCounter=0;
    public static double similarityIndex=0;
    public static int MAX_NUM_IMGS=100;
    public static String fengDir="C:\\Users\\josep\\Desktop\\res\\";
    public static String juandiDir=null; //Cambielo plz
    
    public static String resDirectory=fengDir;
    
    public static void main(String[] args){
      
        ImageGenerator gen0 = new ImageGenerator();
        for(int i=1;i<=MAX_NUM_IMGS;i++){//Genera las primeras 1000 imagenes
            gen0.generateImages(i,genCounter);
        }
        genCounter=genCounter++;
        test();
        /*while(similarityIndex<=90.0){
            test();
        }*/
    }
    
    public static void test(){
        int value=0;
        ImageLoader goalImg = new ImageLoader(resDirectory+"face.bmp");
        for(int i =1;i<=MAX_NUM_IMGS;i++){
            ImageLoader chosenImg = new ImageLoader(resDirectory+"Image"+i+"Gen0.bmp");
            System.out.println("Testing goal image with "+"Image"+i);
            System.out.println("Euclidean difference is: "+String.valueOf(getEuclideanIndex(goalImg.grayscaleRGB(),chosenImg.grayscaleRGB())));
            if (getEuclideanIndex(goalImg.getColors(),chosenImg.getColors())!=0){
                value+=1;
            }else{
                continue;
            }
        }
        System.out.println("Hay "+value+" imagen(es) que tienen indice diferente a cero.");
    }
    
    /**
     * Coge dos vectores y calcula su diferencia Euclideana
     * @param imageA
     * @param imageB
     * @return 
     */
    public static double getEuclideanIndex(ArrayList<ArrayList<Integer>> imageA,ArrayList<ArrayList<Integer>> imageB){
        ArrayList<Double> partialResults=new ArrayList<Double>();
        double diffX,diffY,diffZ;
        int hits=0;
        for(int p=0;p<32;p++){
            diffX=Math.pow(imageA.get(p).get(0)-imageB.get(p).get(0),2);//(x_2)-(x_1)^2
            diffY=Math.pow(imageA.get(p).get(1)-imageB.get(p).get(1),2);//(y_2)-(y_1)^2
            diffZ=Math.pow(imageA.get(p).get(2)-imageB.get(p).get(2),2);//(z_2)-(z_1)^2
            
            partialResults.add(Math.pow(diffX+diffY+diffZ,0.5));
            if(Math.pow(diffX+diffY+diffZ,0.5)==0){
                hits+=1;
            }else{
                continue;
            }
        }
        //System.out.println("There were "+hits+" hits.");
        return hits;
    }
}
