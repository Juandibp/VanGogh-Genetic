/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vangoghproject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author juand
 */
public class Generation extends VanGoghProject{
    int SimilarityIndex;
    BufferedImage Target;
    int GenerationSize;
    ArrayList<Individual> Individuals;

    public Generation(int GenerationSize,BufferedImage Goal) {
        this.Target = Goal;
        this.GenerationSize = GenerationSize;
        this.Individuals= new ArrayList<Individual>();
        createGeneration();
    }
    
    public void createGeneration(){
        for(int i=0; i<GenerationSize;i++){
            Individuals.add(new Individual(Target));
            Individuals.get(i).calculateHealth();
        }
    }
    
    public Individual getHealthiest(){
        Individual healthiest;
        healthiest=Individuals.get(0);
        
        for(int i=1;i<Individuals.size();i++){
            if (Individuals.get(i).health < healthiest.health){
                healthiest=Individuals.get(i);
            }
        }
        return healthiest;
    }
    

    
    
    public void getNextGeneration(){
        Individual best1;
        Individual best2;
        int bestIndex=getHealthiestIndex();
        best1=Individuals.get(bestIndex);
        //Individual[] sub = Arrays.copyOfRange(Individuals,bestIndex,bestIndex+1);
        bestIndex=getHealthiestIndex();
        best2=Individuals.get(bestIndex);
        //Individuals = new ArrayList<Individual>();
        
        for(int i=0;i<GenerationSize;i++){
            Individuals.set(i,breed(best1,best2));
            Individuals.get(i).mutate();
            Individuals.get(i).calculateHealth();
        }
    }

    int getHealthiestIndex(){
        int healthiest;
        healthiest=0;
        
        for(int i=1;i<Individuals.size();i++){
            if(Individuals.get(i).health<Individuals.get(healthiest).health){
                healthiest=i;
            }
        }
        return healthiest;
    }
    
    public Individual breed(Individual splat1, Individual splat2){
        Random rand= new Random();
        float r= rand.nextInt(101);
        Individual offspring;
        offspring=new Individual(Target);

        if(r>50){
             // one for one
            for(int x=0; x<splat1.p.getWidth();x+=2){
               for(int y=0;y<splat1.p.getHeight();y+=2){
                   offspring.p.setRGB(x, y,splat1.p.getRGB(x,y));
                   offspring.p.setRGB(x+1, y+1,splat2.p.getRGB(x+1,y+1));
               }
            }
            return offspring;
        }else{
            //5050
            for(int x=0;x<splat1.p.getWidth();x++){
                for(int y=0;y<splat1.p.getHeight();y++){
                    if(x >= (splat1.p.getWidth()/2)){
                        offspring.p.setRGB(x, y, splat2.p.getRGB(x,y));
                    }else{
                     offspring.p.setRGB(x, y, splat1.p.getRGB(x,y));
                    }
                }
            }
            return offspring;
        }
    }
}