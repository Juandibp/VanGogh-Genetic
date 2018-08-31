package Setup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ImageLoader {
    private BufferedImage loadedImage;
    /**
     * ImageLoader class constructor
     * @param path 
     */
    public ImageLoader(String path){
        loadedImage = loadImage(path);
    }
    /**
     * loadedImage attribute setter
     * @param path
     * @return 
     */
    public BufferedImage loadImage(String path){
        try {
            BufferedImage retrievedImage = ImageIO.read(new File(path));
            return retrievedImage;
        } catch (IOException ex) {
           return null;
        }
    }
    
    public boolean imgIsNull(){
        if(this.loadedImage==null){
            return true;
        }else{
            return false;
        }
    }
    //https://www.youtube.com/watch?v=aBhmeTAuFZI 
    
    /*
        En el vector se guardaran los RGB de las cordenadas (x,y)
        y posteriormente se comparan esos RGB con las de la imagen meta.
        Se usaran los indices de los ArrayLists para denotar las coordenadas (x,y)
        y asi, comparar ambas imagenes.
    */
    
    //Para las componentes RGB, https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values 
    
    /**
     * Method to retrieve an array with all the RGB components of each pixel of the
     * specified image.
     * @return 
     */
    public ArrayList<ArrayList<Integer>> getColors(){ 
        
        ArrayList<ArrayList<Integer>> colors = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer>rgbComponents=new ArrayList<Integer>(); 
        int imageRGB,r,g,b;
                
        for(int x=0;x<32;x++){  //Dura n
            
            for(int y=0;y<32;y++){  //Dura n
               //Brings out RGB int value 
               rgbComponents.clear();
               imageRGB =loadedImage.getRGB(x,y);
               
               //Decomposition of the int into its RGB components
               r = (imageRGB & 0xff0000) >> 16;
               g = (imageRGB & 0xff00) >> 8;
               b = imageRGB & 0xff;
               
               //Adds those components int the rgbComponents ArrayList           
               rgbComponents.add(r);
               rgbComponents.add(g);
               rgbComponents.add(b);
               System.out.println("Added "+rgbComponents.size()+" elements.");
               //Adds the previous array into the final array
               System.out.println("Adding "+rgbComponents.toString());
               colors.add(rgbComponents);
               
               //Clean rgbComponents, for the next pixel components

            }
        }
        //System.out.println(colors.toString());
        return colors;
    }
    /**
     * Retrieves the RGB components of a specified pixel.
     * @param x
     * @param y
     * @return 
     */
    public ArrayList<Integer> getColorFromPixel(int x,int y){
        ArrayList<Integer>rgbComponents=new ArrayList<Integer>();
        int imageRGB =loadedImage.getRGB(x,y);
        
        //Decomposition of the int into its RGB components
        int r = (imageRGB & 0xff0000) >> 16;
        int g = (imageRGB & 0xff00) >> 8;
        int b = imageRGB & 0xff;
               
        //Adds those components int the rgbComponents ArrayList           
        rgbComponents.add(r);
        rgbComponents.add(g);
        rgbComponents.add(b);
        
        return rgbComponents;
    }
}
