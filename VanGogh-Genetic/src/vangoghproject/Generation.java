/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vangoghproject;

import Setup.EuclideanDistanceCalculator;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import static vangoghproject.VanGoghProject.euclidianDistance;
import static vangoghproject.VanGoghProject.resDirectory;

/**
 *
 * @author juand
 */
public class Generation extends VanGoghProject{
    double SimilarityIndex;
    BufferedImage Target;
    int GenerationSize;
    ArrayList<Individual> Individuals;

    public Generation(int GenerationSizeP,BufferedImage Goal) {
        this.Target = Goal;
        this.GenerationSize = GenerationSizeP;
        this.Individuals= new ArrayList<Individual>();
        createGeneration();
    }
    
    public Generation(ArrayList<Individual> newGen, BufferedImage Goal) {
        this.Target = Goal;
        this.GenerationSize = newGen.size();
        this.Individuals= newGen;
        //createGeneration();
    }
    
    public void createGeneration(){
        for(int i=0; i<=GenerationSize;i++){
            Individual newEntry =new Individual(Target);
            newEntry.generateImage();
            newEntry.setName("Image"+String.valueOf(i));
            this.Individuals.add(newEntry);
            //VanGoghProject.toFile(newEntry.p,newEntry.getName());
        }
        Individuals.toString();
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
    public ArrayList<Pair<String,Double>> rank(){
        //Arraylist de pares donde se van a guardar pares de String para el nombre de la imagen y un double donde va a estar la distancia Euclideana
        ArrayList<Pair<String,Double>> ranking = new ArrayList<Pair<String,Double>>();
        BufferedImage genImg = null;
        for(int i=0;i<=this.GenerationSize;i++){
            try {
               genImg =ImageIO.read(new File(resDirectory+"Image"+String.valueOf(i)+".bmp"));
               Pair<String,Double> newEntry = new Pair<>("Image"+String.valueOf(i)+".bmp",euclidianDistance(goalImg,genImg));
               ranking.add(newEntry);
                } catch (IOException e) {
            System.out.println("Imagen no existe");
                }
        }
        return ranking;
    }
    
    
    public void test(){
        ArrayList<Pair<String,Double>> ranking = rank();
        VanGoghProject.verRanking(ranking);
    }
    
    
    public Individual getHealthiest(ArrayList<Pair<String,Double>> rankingP){
        ArrayList<Pair<String,Double>> ranking = VanGoghProject.sortRanking(rankingP);
        System.out.println(ranking.toString());
        Individual healthiest=null;
        //System.out.println(ranking.get(ranking.size()-1).getKey());
        healthiest = retrieveIndividual(ranking.get(ranking.size()-1).getKey());
        VanGoghProject.toFile(healthiest.getGenImage(),healthiest.getName());
        //System.out.println(Individuals.toString());
        //System.out.println("Current generation size: "+ String.valueOf(Individuals.size()));
        return healthiest;
    }
    
    public double getStrongest(){
        ArrayList<Pair<String,Double>> ranking =this.rank();
        System.out.println("Strongest Individual: "+getHealthiest(ranking).getName());
        this.SimilarityIndex = getHealthiest(ranking).health;
        return this.SimilarityIndex;
    }
    
    public Individual retrieveIndividual(String filename){
        if(Individuals.size()==1){
            return Individuals.get(0);
        }else{
            for(int i=0;i!=Individuals.size();i++){
                if((filename).equals(Individuals.get(i).getName()+".bmp")){

                    return Individuals.get(i); // This is the healthiest
                }else{
                    continue;
                }
            }
            return null;
        }
    }

    
    public ArrayList<Individual> getNextGeneration(){
        Individual best1;
        Individual best2;
        int bestIndex=getHealthiestIndex();
        best1=Individuals.get(bestIndex);
        //Individual[] sub = Arrays.copyOfRange(Individuals,bestIndex,bestIndex+1);
        best2=Individuals.get(bestIndex);
        //Individuals = new ArrayList<Individual>();
        this.Individuals.clear();
        Individuals.add(best1);
        Individuals.add(best2);
        for(int i=0;i<GenerationSize;i++){
            Individual newIndividual = breed(best1,best2);
            Individuals.add(newIndividual);
            Individuals.get(i).mutate();
            Individuals.get(i).calculateHealth(DistanceType);
        }
        return Individuals;
    }

    public int getHealthiestIndex(){
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

            for(int x=0; x<splat1.width;x+=2){
               for(int y=0;y<splat1.height;y+=2){
                   offspring.p.setRGB(x, y,splat1.p.getRGB(x,y));
                   offspring.p.setRGB(x+1, y+1,splat2.p.getRGB(x+1,y+1));
               }
            }
            return offspring;

        }else{
            //5050
            for(int x=0;x<splat1.width;x++){
                for(int y=0;y<splat1.height;y++){
                    if(x >= (splat1.width/2)){
                        offspring.p.setRGB(x, y, splat2.p.getRGB(x,y));
                    }else{
                     offspring.p.setRGB(x, y, splat1.p.getRGB(x,y));
                    }
                }
            }
        }
            return offspring;
        }
}
