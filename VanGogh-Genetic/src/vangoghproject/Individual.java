/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vangoghproject;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author juand
 */
public class Individual extends VanGoghProject {
    int max;
    BufferedImage p;
    double health;
    BufferedImage Target;

    public Individual(BufferedImage Target) {
        this.Target = Target;
        this.max = Target.getWidth()*Target.getHeight();
        this.p = generateImage();
        this.health=0;
    }

    public BufferedImage getTarget() {
        return Target;
    }
    
    public BufferedImage getGenImage() {
        return p;
    }

    public BufferedImage generateImage(){
        Random randint = new Random(System.currentTimeMillis());
        int r,g,b;
        BufferedImage buffedImage=new BufferedImage(Target.getWidth(),Target.getHeight(),BufferedImage.TYPE_INT_RGB);
        //System.out.println("Image: "+buffedImage.toString());
        for(int x=0;x<Target.getWidth();x++){
            for(int y=0;y<Target.getHeight();y++){

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
        return buffedImage;
        /*File newImage= new File(VanGoghProject.resDirectory+"Image"+imageNumber+"Gen"+imageGeneration+".bmp");
        try {
            ImageIO.write(buffedImage, "BMP", newImage);
            //System.out.println("Image generated succesfully @"+newImage.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Oops! An error occurred");
        }/*/
    }
    public void mutate(){
        Random randint = new Random(System.currentTimeMillis());
        for(int i=0;i!=25;i++){
            int x= randint.nextInt(Target.getWidth());
            int y = randint.nextInt(Target.getHeight());
            int rgb = Target.getRGB(x,y);
            this.p.setRGB(x, y, rgb);
        }
    }
    
    public double calculateHealth(){
        this.health = euclidianDistance(Target,p);
        return health;
    }
}
