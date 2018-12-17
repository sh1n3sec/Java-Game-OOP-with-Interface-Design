
package javafxapplication01;

import javafx.scene.control.Button;

public class botoes extends Button{
        
    public boolean rodar;
    
    
    botoes(){
        
        rodar = false;
        
        
    }

    public boolean isRodar() {
        return rodar;
    }

    public void setRodar(boolean rodar) {
        this.rodar = rodar;
    }
     

    
}
