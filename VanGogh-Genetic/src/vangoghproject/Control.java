/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vangoghproject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vangoghproject.*;
/**
 *
 * @author josep
 */
public class Control {
    public Control(int tamanoPoblacionP,double probabilidadCruceP,double porcentajeGenesP,String imagePathP,int distanceMode,mainFrame window){
        
        VanGoghProject instance = new VanGoghProject(tamanoPoblacionP,probabilidadCruceP,porcentajeGenesP,imagePathP,distanceMode);
        if(instance.getGoalImg()==null){
             JOptionPane.showMessageDialog(null, "Oops! An error occurred");
        }else{
            instance.process(window);
        }
    }
}
