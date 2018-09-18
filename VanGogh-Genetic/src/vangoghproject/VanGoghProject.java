package vangoghproject;

import Setup.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Setup.ImageLoader;

public class VanGoghProject {
    public static int genCounter=0;
    public static ArrayList<ArrayList<Integer>> targetColors;
    public static double similarityIndex=0;
    public static int MAX_NUM_IMGS=15;
    public static int GenerationSize=15;
    public static String fengDir="D:\\josep\\Documents\\GitRepos\\VanGogh-Genetic\\data\\";
    public static String juandiDir="C:\\Users\\juand\\Documents\\GitHub\\VanGogh-Genetic\\data\\";
    public static String resDirectory=juandiDir;
    public static String DistanceType="BEJARANO-FENG";
    //public static String DistanceType="EUCLIDEAN";
    //public static String DistanceType="MANHATTAN";
    
    public static BufferedImage goalImg;
    
    public static void main(String[] args){
        try {
            System.out.println("Searching in: "+resDirectory);
            goalImg = ImageIO.read(new File(resDirectory+"totoro.jpg"));
            //goalImg = ImageIO.read(new File(resDirectory+"NickCage.bmp"));
          
        } catch (IOException e) {
            System.out.println("Imagen no existe");
            
        }
        Generation gen= new Generation(MAX_NUM_IMGS, goalImg);
        //gen.getStrongest();
         
        JFrame frame = new JFrame();
        ImageIcon image = new ImageIcon(goalImg);
        JLabel imageLabel = new JLabel(image);
        frame.add(imageLabel);
        frame.setLayout(null);
        imageLabel.setLocation(0, 0);
        imageLabel.setSize(500, 750);
        imageLabel.setVisible(true);
        frame.setVisible(true);
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon generationStandard = new ImageIcon(gen.getHealthiest().getGenImage());
        JLabel genLabel = new JLabel(generationStandard);
        frame.add(genLabel);
        frame.setLayout(null);
        genLabel.setLocation(0, 0);
        genLabel.setSize(1500, 750);
        genLabel.setVisible(true);
        Generation NextGen = new Generation (gen.getNextGeneration(),goalImg);
        startGenerations(frame,genLabel,NextGen);
   
        //gen.test();
        //gen.getHealthiest();
        
        /*ImageGenerator gen0 = new ImageGenerator();
        for(int i=1;i<=MAX_NUM_IMGS;i++){//Genera las primeras 1000 imagenes
            gen0.generateImages(i,genCounter,goalImg.getWidth(),goalImg.getHeight());
        }*/

        //genCounter=genCounter++;
       // test(goalImg);
        /*while(similarityIndex<=90.0){
            test();
        }*/
 
    }
 
    public static void startGenerations (JFrame frame,JLabel genLabel,Generation gen){
        long startTime = System.currentTimeMillis();
        Individual currentBest;
        int i=1;
        while (gen.SimilarityIndex<=10000){
            currentBest= gen.getHealthiest();
            currentBest.setName("Image"+String.valueOf((int)currentBest.calculateHealth(DistanceType)));
            ImageIcon generationStandard = new ImageIcon(currentBest.getGenImage());

            genLabel.setIcon(generationStandard);
            frame.add(genLabel);
            frame.setLayout(null);
            genLabel.setLocation(0, 0);
            genLabel.setSize(1500, 750);
            genLabel.setVisible(true);
            genLabel.updateUI();
            if(i%10==0){
                VanGoghProject.toFile(currentBest.getGenImage(),currentBest.getName());
                Generation NextGen = new Generation (gen.getNextGeneration(),goalImg);
                i+=1;
            }else{
                Generation NextGen = new Generation (gen.getNextGeneration(),goalImg);
                i+=1;
            }
        }
    }
public static void test(BufferedImage goalImg){
        int value=0;
 
        /* JFrame frame = new JFrame();
         ImageIcon image = new ImageIcon(goalImg);
         JLabel imageLabel = new JLabel(image);
         frame.add(imageLabel);
         frame.setLayout(null);
         imageLabel.setLocation(0, 0);
         imageLabel.setSize(1000, 750);
         imageLabel.setVisible(true);
         frame.setVisible(true);
         frame.setSize(1000, 750);
         frame.setDefaultCloseOperation(EXIT_ON_CLOSE);*/

        ImageLoader finalImg = new ImageLoader(goalImg);
        //Arraylist de pares donde se van a guardar pares de String para el nombre de la imagen y un double donde va a estar la distancia Euclideana
        ArrayList<Pair<String,Double>> ranking = new ArrayList<Pair<String,Double>>();

        for(int i =1;i<=MAX_NUM_IMGS;i++){
            ImageLoader chosenImg = new ImageLoader(resDirectory+"Image"+i+"Gen0.bmp");
            BufferedImage genImg = null;
            try {
                genImg = ImageIO.read(new File(resDirectory+"Image"+i+"Gen0.bmp"));

                Pair<String,Double> newEntry = new Pair<>("Image"+String.valueOf(i)+"Gen0.bmp",euclidianDistance(goalImg,genImg));
                ranking.add(newEntry);
                } catch (IOException e) {
            System.out.println("Imagen no existe");
                }
            //System.out.println("Testing goal image with "+"Image"+i);
        }
        verRanking(ranking);

    }

public static void verRanking(ArrayList<Pair<String,Double>> ranking){
        Collections.sort(ranking,new Comparator<Pair<String,Double>>(){
           @Override
           public int compare(Pair<String,Double > o1,Pair<String,Double> o2){
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue().equals(o2.getValue())) {
                    return 0;
                } else {
                    return 1;
                }
           }
        });
        System.out.println(ranking.toString().replace(',',Character.valueOf('\n')));
    }

public static ArrayList<Pair<String,Double>> sortRanking(ArrayList<Pair<String,Double>> ranking){
        Collections.sort(ranking,new Comparator<Pair<String,Double>>(){
           @Override
           public int compare(Pair<String,Double > o1,Pair<String,Double> o2){
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue().equals(o2.getValue())) {
                    return 0;
                } else {
                    return 1;
                }
           }
        });
        System.out.println(ranking.toString().replace(',',Character.valueOf('\n')));
        return ranking;
    }

public static double EuclideanDistanceCalculator(double[] ImageA, double[] ImageB)
    {
        double Sum = 0.0;
        for(int i=0;i<ImageA.length;i++) {
           Sum = Sum + Math.pow((ImageA[i]-ImageB[i]),2.0);
        }
        return Math.sqrt(Sum);
    }
    
    public static ArrayList<ArrayList<Integer>> getRGBComponents(BufferedImage Image){
        ArrayList<ArrayList<Integer>> colorsImage= new ArrayList();
        for (int i=0; i<Image.getWidth();i++){
            for (int j=0; j<Image.getHeight();j++){
                int pixel = Image.getRGB(i, j);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                ArrayList<Integer> rgb= new ArrayList();
                rgb.add(red);
                rgb.add(green);
                rgb.add(blue);
                
                //System.out.println(Image.getHeight());
                /*System.out.println(green);
                System.out.println(blue);
                System.out.println(" ");*/
                colorsImage.add(rgb);
            }
        }
        return colorsImage;
    }
    
    public static ArrayList<Integer> getColorFromPixel(int x,int y, BufferedImage Img){
        ArrayList<Integer>rgbComponents=new ArrayList<Integer>();
        int imageRGB =Img.getRGB(x,y);
        
        //Decomposition of the int into its RGB components
        int a = (imageRGB >> 24) & 0xff;
        int r = (imageRGB >> 16) & 0xff;
        int g = (imageRGB >> 8) & 0xff;
        int b = imageRGB & 0xff;
               
        //Adds those components int the rgbComponents ArrayList           
        rgbComponents.add(a);
        rgbComponents.add(r);
        rgbComponents.add(g);
        rgbComponents.add(b);
        
        return rgbComponents;
    }
    
    public static BufferedImage ImageToGrayscale(BufferedImage imagen){
        BufferedImage grayImg = new BufferedImage(imagen.getWidth(),imagen.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int x=0;x<imagen.getWidth();x++){
            for(int y=0;y<imagen.getHeight();y++){
                ArrayList<Integer> argbComponents=getColorFromPixel(x, y,imagen);

                int a = argbComponents.get(0);
                int r = argbComponents.get(1);
                int g = argbComponents.get(2);
                int b = argbComponents.get(3);       
                
                int avg = (r+g+b)/3;
                
                int newPixel = (a<<24)|(avg<<16)|(avg<<8)|avg;
                
                grayImg.setRGB(x, y, newPixel);
            }
        }
        return grayImg;
    }
    
    public static ArrayList<Color> getColorsArray(BufferedImage buffedImg){ 

        ArrayList<Color> colors = new ArrayList<Color>();
        for(int x=0;x<buffedImg.getWidth();x++){  //Dura n
            for(int y=0;y<buffedImg.getHeight();y++){  //Dura n
               //Brings out RGB int value 
               Color pixelColor = new Color(buffedImg.getRGB(x,y));
               colors.add(pixelColor);
            }
        }
        //System.out.println(colors.toString());
        return colors;
    }
    
    public static double euclidianDistance(BufferedImage ImageA, BufferedImage ImageB)
    {
        ArrayList<ArrayList<Integer>> colorsA = getRGBComponents(ImageA);
        ArrayList<ArrayList<Integer>> colorsB = getRGBComponents(ImageB);
        double Sum=0;
        double Red=0;
        double Green=0;
        double Blue=0;
        for(int i=0;i<colorsA.size()-1;i++) {
            Red = Red + Math.pow((colorsA.get(i).get(0)-colorsB.get(i).get(0)),2.0);
            Green = Green + Math.pow((colorsA.get(i).get(1)-colorsB.get(i).get(1)),2.0);
            Blue = Blue + Math.pow((colorsA.get(i).get(2)-colorsB.get(i).get(2)),2.0);
        }
        Sum=Sum+Red+Green+Blue;
        return Math.sqrt(Sum);
    }
    
    
    public static double manhattanDistance(BufferedImage ImageA, BufferedImage ImageB){ //Uses GrayScale for Efficiency 
        ArrayList<ArrayList<Integer>> colorsA = getRGBComponents(ImageA);
        ArrayList<ArrayList<Integer>> colorsB = getRGBComponents(ImageB);
        double Sum=0;
        double red=0;
        double green=0;
        double blue=0;

        for(int i=0;i<colorsA.size()-1;i++) {
            red = red + Math.abs((colorsA.get(i).get(0)-colorsB.get(i).get(0)));
            green = green + Math.abs((colorsA.get(i).get(1)-colorsB.get(i).get(1)));
            blue = blue + Math.abs((colorsA.get(i).get(2)-colorsB.get(i).get(2)));
        }
        Sum=Sum+red+green+blue;
        return Sum;
        
    }
    
   public static double BejaranoFengDistance (BufferedImage ImageA, BufferedImage ImageB){ //Uses GrayScale for Efficiency 
        BufferedImage targetGray = ImageToGrayscale(ImageA);
        BufferedImage genGray = ImageToGrayscale(ImageB);
        ArrayList<ArrayList<Integer>> colorsA = getRGBComponents(targetGray);
        ArrayList<ArrayList<Integer>> colorsB = getRGBComponents(genGray);
        double Sum=0;
        double gray=0;

        for(int i=0;i<colorsA.size()-1;i++) {
            gray = gray + Math.abs((((colorsA.get(i).get(0)))-((colorsB.get(i).get(0)))));
        if (colorsA.get(i).get(0)!=0 && (colorsB.get(i).get(0))!=0){
            gray=gray + Math.abs(((Math.log(colorsA.get(i).get(0)))-(Math.log(colorsB.get(i).get(0)))));
        }
        else{
            if(Math.abs((((colorsA.get(i).get(0)))-((colorsB.get(i).get(0)))))==0){
                gray=0;
            }
        }
            gray=Math.log(gray);
        }
        Sum=Sum+gray;
        return Sum;
    }
   /* public static double euclidianDistance(BufferedImage ImageA, Color[] p)
    {
        ArrayList<ArrayList<Integer>> colorsA = getRGBComponents(ImageA);
        ArrayList<ArrayList<Integer>> colorsB = getRGBFromColor(p);
        double Sum=0;
        double Red=0;
        double Green=0;
        double Blue=0;
        for(int i=0;i<colorsA.size()-1;i++) {
            Red = Red + Math.pow((colorsA.get(i).get(0)-colorsB.get(i).get(0)),2.0);
            Green = Green + Math.pow((colorsA.get(i).get(1)-colorsB.get(i).get(1)),2.0);
            Blue = Blue + Math.pow((colorsA.get(i).get(2)-colorsB.get(i).get(2)),2.0);
        }
        Sum=Sum+Red+Green+Blue;
        return Math.sqrt(Sum);
    }*/
    
    public static ArrayList<ArrayList<Integer>> getRGBFromColor(Color[] p){
        ArrayList<ArrayList<Integer>> colorsImage= new ArrayList();
        for (int i=0; i<p.length;i++){
                int pixel = p[i].getRGB();
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                ArrayList<Integer> rgb= new ArrayList();
                rgb.add(red);
                rgb.add(green);
                rgb.add(blue);
                
                //System.out.println(Image.getHeight());
                /*System.out.println(green);
                System.out.println(blue);
                System.out.println(" ");*/
                colorsImage.add(rgb);
            }
        
        return colorsImage;
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
    
    public static void toFile(BufferedImage newImageP,String filename){
        File newImageFile= new File(VanGoghProject.resDirectory+filename+".bmp");
        try {
            ImageIO.write(newImageP, "BMP", newImageFile);
            //System.out.println("Image generated succesfully @"+newImage.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Oops! An error occurred"); 
        }
    }   
}
