/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vangoghproject;
/**
 *
 * @author josep
 */
public class Control {
    public Control(int tamanoPoblacionP,double probabilidadCruceP,double porcentajeGenesP,String imagePathP,int distanceMode,int cantGensP){
        
        VanGoghProject instance = new VanGoghProject(tamanoPoblacionP,probabilidadCruceP,porcentajeGenesP,imagePathP,distanceMode,cantGensP);
        if(instance.getGoalImg()==null){
            System.out.print("No se encuentra la imagen.");
        }else{
            instance.process();
           /* Thread revisarCorreos = new Thread(new Runnable() {

			@Override
			public void run() {
				
				while(true) {
                                    instance.process();
                                    break;
				}
			}
		});*/
            
            
        }
    }
}
