
package vangoghproject;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Individual{

    int max;

    int height,width;

    BufferedImage p;
    double health;
    BufferedImage Target;
    public String name;
    
    public Individual(BufferedImage Target) {
        this.Target = Target;

        this.max = Target.getWidth()*Target.getHeight();

        this.height=Target.getHeight();
        this.width= Target.getWidth();

        this.p = generateImage();
        this.health=calculateHealth(VanGoghProject.DistanceType);
    }

    public BufferedImage getTarget() {
        return Target;
    }
    
public BufferedImage getGenImage() {
        return p;
    }

    public String getName(){
        return name;
    }
    
    public void setName(String nameP){
        this.name =  nameP;
    }
    
    public BufferedImage generateImage(){
        Random randint = new Random(System.currentTimeMillis());
        int r,g,b;
        BufferedImage buffedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for(int x=0;x<this.width;x++){
            for(int y=0;y<this.height;y++){
                
                r=randint.nextInt(255);
                g=randint.nextInt(255);
                b=randint.nextInt(255);
                Color colour = new Color(r,g,b);
                int rgb=colour.getRGB();
             

                buffedImage.setRGB(x, y, rgb);
            }
        }         
        
        return buffedImage;
        

        }
    public void mutate(){
        Random randint = new Random(System.currentTimeMillis());
        
        Double tries = ((max*VanGoghProject.porcentajeGenes)%25)+5;
        
        while(tries >= 0){
            int x= randint.nextInt(this.width);
            int y = randint.nextInt(this.height);
            int rgb = Target.getRGB(x,y);
            
            if (rgb == p.getRGB(x,y)){
                tries-=1;
            }else{
               this.p.setRGB(x, y, rgb); 
               tries-=1;
            }
        }
    }

    public double calculateHealth(String Distance){
        if (Distance.equals("EUCLIDEAN")){
            
            this.health = VanGoghProject.euclidianDistance(Target,p);
            //System.out.println("Current is:"+String.valueOf(this.health));
            return health;
        }
        if (Distance.equals("MANHATTAN")){
             
            this.health = VanGoghProject.manhattanDistance(Target,p);
            //System.out.println("Current is:"+String.valueOf(this.health));            
            return health;
        }
        if (Distance.equals("BEJARANO-FENG")){
            
            this.health = VanGoghProject.BejaranoFengDistance(Target,p);
            //System.out.println("Current is:"+String.valueOf(this.health));
            return health;
        }else{
            return 0;
        }
    }
}

