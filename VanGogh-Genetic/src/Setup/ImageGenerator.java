package Setup;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


/*TODO: 
https://stackoverflow.com/questions/25714003/does-java-have-a-bitmap-class 
https://docs.oracle.com/javase/tutorial/2d/overview/coordinate.html 

*/
public class ImageGenerator {
    
    public static void main(String[] args){
        System.out.println("Program start.");
        /*for(int i = 1;i<=10;i++){    
            generateImages(i,1);
        }*/
        JOptionPane.showMessageDialog(null, "Images generated succesfully!");
        ImageLoader loadedImage = new ImageLoader("D:\\josep\\Documents\\NetBeansProjects\\VanGoghProject\\res\\testImage.bmp");
        if(loadedImage.imgIsNull()==true){
            JOptionPane.showMessageDialog(null, "Image doesn't exist!");
        }else{
          loadedImage.getColors();
           
        }
    }
    
    public static void generateImages(int imageNumber,int imageGeneration){
        Random randint = new Random(System.currentTimeMillis());
        int r,g,b;
        BufferedImage buffedImage=new BufferedImage(32,32,BufferedImage.TYPE_INT_RGB);
        System.out.println("Image: "+buffedImage.toString());
        for(int x=0;x<32;x++){
            for(int y=0;y<32;y++){
                
                r=randint.nextInt(255);
                g=randint.nextInt(255);
                b=randint.nextInt(255);
             
            
                Color colour = new Color(r,g,b);
                int rgb=colour.getRGB();
                //System.out.println(String.valueOf(r)+","+String.valueOf(g)+","+String.valueOf(b)+"("+colour.toString()+")"+"="+colour.getRGB());
                //System.out.println("Color @"+String.valueOf(x)+","+String.valueOf(y)+" is "+String.valueOf(rgb));
                buffedImage.setRGB(x, y, rgb);              
            }
        }
        File newImage= new File("D:\\josep\\Documents\\NetBeansProjects\\VanGoghProject\\res\\Image"+imageNumber+"Gen"+imageGeneration+".bmp");
        try {
            ImageIO.write(buffedImage, "BMP", newImage);
            System.out.println("Image generated succesfully @"+newImage.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Oops! An error occurred");
            
        }
    }
}
