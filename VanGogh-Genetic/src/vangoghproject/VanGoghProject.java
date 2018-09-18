package vangoghproject;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VanGoghProject {
    /*public static int genCounter=0;
    public static ArrayList<ArrayList<Integer>> targetColors;
    public static double similarityIndex=0;
    public static int MAX_NUM_IMGS=10;
    public static int GenerationSize=20;
    public static String fengDir="D:\\josep\\Documents\\GitRepos\\VanGogh-Genetic\\data\\";
    public static String juandiDir="C:\\Users\\juand\\Documents\\GitHub\\VanGogh-Genetic\\data\\";
    public static String resDirectory=fengDir;
    public static String DistanceType="BEJARANO-FENG";
    public static double similarityGoal;
    public static BufferedImage goalImg;*/
    
    public static int genCounter=0;
    public static ArrayList<ArrayList<Integer>> targetColors;
    public static double similarityIndex;
    public static int MAX_NUM_IMGS;
    public static int GenerationSize;
    public static String fengDir="D:\\josep\\Documents\\GitRepos\\VanGogh-Genetic\\data\\";
    public static String juandiDir="C:\\Users\\juand\\Documents\\GitHub\\VanGogh-Genetic\\data\\";
    public static String resDirectory=fengDir;
    public static String DistanceType;
    public static double similarityGoal;
    public static BufferedImage goalImg;
    public static double probabilidadCruce;
    public static double porcentajeGenes;
    public static int cantGenerations;
    public static int i=1;
    public VanGoghProject(int tamanoPoblacionP,double probabilidadCruceP,double porcentajeGenesP,String imagePathP,int distanceModeP,int cantGens){
        GenerationSize=tamanoPoblacionP;
        MAX_NUM_IMGS=tamanoPoblacionP;
        probabilidadCruce = probabilidadCruceP;
        porcentajeGenes = porcentajeGenesP;
        getDistanceType(distanceModeP);
        retrieveImage(imagePathP);
        cantGenerations = cantGens;
        System.out.println("All attributes set!");
        
    }
    
    public void process(){
        Generation gen= new Generation(MAX_NUM_IMGS, goalImg);
        mainFrame frame = new mainFrame();
        ImageIcon image = new ImageIcon(goalImg);
        JLabel imageLabel = new JLabel(image);
        frame.add(imageLabel);
        frame.setLayout(null);
        imageLabel.setLocation(0, 0);
        imageLabel.setSize(500, 750);
        imageLabel.setVisible(true);
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        ImageIcon generationStandard = new ImageIcon(gen.getHealthiest().getGenImage());
        System.out.println("Similarity goal: "+ String.valueOf(similarityGoal));
        JLabel genLabel = new JLabel(generationStandard);
        frame.setLayout(null);
        genLabel.setVisible(true);
        frame.getSimilarityIndex().setText(String.valueOf(gen.getHealthiest().health));
        Generation NextGen = new Generation (gen.getNextGeneration(),goalImg);
        Thread revisarCorreos = new Thread(new Runnable() {

			@Override
			public void run() {
				
				while(true) {
                                    frame.getHealthiestImgLabel().updateUI();
                                    frame.getSimilarityIndex().updateUI();
                                    break;
				}
			}
                        
		});
        startGenerations(frame,genLabel,NextGen);
    }
   
    public static BufferedImage getGoalImg() {
        return goalImg;
    }
    
    public static void getDistanceType(int modeInt){
        switch(modeInt){
            case 0:
                DistanceType ="EUCLIDEAN".toUpperCase();
                break;
            case 1:
                DistanceType ="MANHATTAN".toUpperCase();
                break;
            case 2:
                DistanceType ="BEJARANO-FENG".toUpperCase();
                break;
            default:
                DistanceType = "No definido.";
                break; 
        }
    }
    
    public static void retrieveImage(String filename){
        try {
            System.out.println("Searching in: "+filename);
            goalImg = ImageIO.read(new File(filename));
            setSimilarityGoal();
        } catch (IOException e) {
            System.out.println("Imagen no existe en :"+filename);
            JOptionPane.showMessageDialog(null, "Imagen no encontrada.");
        }
    }
    
   /* public static void main(String[] args){
        try {
            System.out.println("Searching in: "+resDirectory);
            goalImg = ImageIO.read(new File(resDirectory+"downhillduck.bmp"));
            //goalImg = ImageIO.read(new File(resDirectory+"NickCage.bmp"));
          
        } catch (IOException e) {
            System.out.println("Imagen no existe");
        }
        Generation gen= new Generation(MAX_NUM_IMGS, goalImg);
         
        mainFrame frame = new mainFrame();
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
    }*/
    
    public static void setSimilarityGoal(){
        //System.out.print("Mode: "+DistanceType);
        switch(DistanceType){
            case "MANHATTAN":
                System.out.println("EUCLIDEAN");
                similarityGoal = 1000000;
                break;
            case "EUCLIDEAN":
                System.out.println("MANHATTAN");
                similarityGoal= 100000; 
                break;
            case "BEJARANO-FENG":
                 System.out.println("BEJARANO-FENG");
                similarityGoal=5;
            default:
                similarityGoal = 10000;
                break;      
        }
    }
 
    public static void startGenerations (mainFrame frame,JLabel genLabel,Generation gen){
        Individual currentBest;
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        System.out.println("Mode:"+DistanceType);
        while (cantGenerations !=0){
            //System.out.println("SimilarityIndex ="+String.valueOf(similarityIndex)+" i = "+String.valueOf(i));
            System.out.println("Generation: "+ String.valueOf(cantGenerations));
            currentBest= gen.getHealthiest();
            int health = (int)currentBest.calculateHealth(DistanceType);
            currentBest.setName("Image"+String.valueOf(health));
            ImageIcon generationStandard = new ImageIcon(currentBest.getGenImage());

            frame.getHealthiestImgLabel().setIcon(generationStandard);
            
            frame.getSimilarityIndex().setText(String.valueOf(currentBest.health));
            similarityIndex =health;
            frame.getHealthiestImgLabel().updateUI();


            genLabel.setIcon(generationStandard);
            frame.add(genLabel);
            frame.setLayout(null);

            genLabel.setVisible(true);
            genLabel.updateUI();    
            if(i%10==0){
                VanGoghProject.toFile(currentBest.getGenImage(),currentBest.getName()+"-"+String.valueOf(i));
                Generation NextGen = new Generation (gen.getNextGeneration(),goalImg);
                i+=1;
                cantGenerations-=1;
            }else{
                Generation NextGen = new Generation (gen.getNextGeneration(),goalImg);
                i+=1;
                cantGenerations-=1;
            }
        }
       
        frame.setVisible(true);
        genLabel.updateUI();
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
        System.out.println(ranking.toString().replace(',', '\n'));
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
        System.out.println(ranking.toString().replace(',', '\n'));
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

        ArrayList<Color> colors = new ArrayList<>();
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
        }
        if (gray==0){
            gray=0;
        }
        else{
            gray=Math.log(gray);
        }
        Sum=Sum+gray;
        return Sum;
    }
    
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
