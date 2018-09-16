package Setup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ImageLoader {
    public BufferedImage loadedImage;
    /**
     * ImageLoader class constructor
     * @param path 
     */
    public ImageLoader(String path){
        loadedImage = loadImage(path);
    }
    
    public BufferedImage getLoadedImage(){
        return this.loadedImage;
    }
    
    public ImageLoader(BufferedImage image){
        this.loadedImage=image;
    }
    /**
     * loadedImage attribute setter
     * @param path
     * @return 
     */
    
    public int getImageWidth(BufferedImage img){
        return img.getWidth();
    }
    
    public int getImageHeight(BufferedImage img){
        return img.getHeight();
    }
    
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
                
        for(int x=0;x<16;x++){  //Dura n
            int imageRGB;
            for(int y=0;y<16;y++){  //Dura n
               //Brings out RGB int value 
               imageRGB =loadedImage.getRGB(x,y);
               
               //Decomposition of the int into its RGB components
                int a = (imageRGB &0xff000000) >> 24;
                int r = (imageRGB & 0xff0000) >> 16;
                int g = (imageRGB & 0xff00) >> 8;
                int b = imageRGB & 0xff;
               ArrayList<Integer>rgbComponents=new ArrayList<Integer>(); 
               //Adds those components int the rgbComponents ArrayList    
               
               rgbComponents.add(r);
               rgbComponents.add(g);
               rgbComponents.add(b);
               //Adds the previous array into the final array
               //System.out.println("Pixel <"+x+","+y+">"+rgbComponents.toString()+" "+(new ColorUtils().getColorNameFromRgb(r, g, b)));
               colors.add(rgbComponents);
               
               

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
    /**
     * Retorna la imagen cargada pero en escala de grises
     * @return BufferedImage 
     */
    public BufferedImage ImageToGrayscale(BufferedImage imagen){
        BufferedImage grayImg = new BufferedImage(imagen.getWidth(),imagen.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int x=0;x<this.loadedImage.getWidth();x++){
            for(int y=0;y<this.loadedImage.getHeight();y++){
                ArrayList<Integer> argbComponents=this.getColorFromPixel(x, y);
                
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
    /**
     * Funcion auxiliar que retorna un arreglo con los colores RGB de la image en escala de grises
     * @param colors
     * @return 
     */
    public ArrayList<ArrayList<Integer>> grayscaleRGB(){
        BufferedImage grayscaleImg= this.ImageToGrayscale(this.loadedImage);
        ArrayList<ArrayList<Integer>> colors = new ArrayList<ArrayList<Integer>>();
                
        for(int x=0;x<16;x++){  //Dura n
            int imageRGB;
            for(int y=0;y<16;y++){  //Dura n
               //Brings out RGB int value 
               imageRGB =grayscaleImg.getRGB(x,y);
               
               //Decomposition of the int into its RGB components
                int a = (imageRGB &0xff000000) >> 24;
                int r = (imageRGB & 0xff0000) >> 16;
                int g = (imageRGB & 0xff00) >> 8;
                int b = imageRGB & 0xff;
               ArrayList<Integer>rgbComponents=new ArrayList<Integer>(); 
               //Adds those components int the rgbComponents ArrayList    
               
               rgbComponents.add(r);
               rgbComponents.add(g);
               rgbComponents.add(b);
               //Adds the previous array into the final array
               //System.out.println("Pixel <"+x+","+y+">"+rgbComponents.toString()+" "+(new ColorUtils().getColorNameFromRgb(r, g, b)));
               colors.add(rgbComponents);
            }
        }
        return colors;
    }

}
