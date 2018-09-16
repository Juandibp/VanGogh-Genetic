/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vangoghproject;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author juand
 */
public class Individual extends VanGoghProject {
    int max;
    Color[] p;
    double health;
    BufferedImage Target;

    public Individual(BufferedImage Target) {
        this.Target = Target;
        this. max = Target.getWidth()*Target.getHeight();
        this.p = new Color [max];
        this.health=0;
    }

    public BufferedImage getTarget() {
        return Target;
    }

    public void generateImage(){
        for(int i=1; i<p.length;i++){
            Random rand = new Random();
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            Color c = new Color(r,g,b);
            p[i]=c;
        }
    }
    
    public void mutate(){
        Random rand= new Random();
        for(int i=0;i<(int)rand.nextInt(11);i++){
            int r = (int)rand.nextInt(p.length+1);
            p[r]= new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
        }
    }
    
        public double calculateHealth(){
        this.health = euclidianDistance(Target,p);
        return health;
    }
    
    
    
    
    
    

}
