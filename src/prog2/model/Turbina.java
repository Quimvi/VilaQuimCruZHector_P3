package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;
import java.util.Iterator;

public class Turbina implements InComponent{
    private boolean activat;
    private float costOperatiu;

    public Turbina(){
        desactiva();
    }

    public void activa() throws CentralUBException {
        this.activat = true;
        //no se si es necessita una exceció
    }

    public void desactiva() throws CentralUBException{
        this.activat = false;
        //no se si es necessita una exceció
    }

    public boolean getActivat() {
        return this.activat;
    }

    public void revisa (PaginaIncidencies p){
        if (!getActivat()){
            p.afegeixIncidencia("Turbina fora de servei");
        }else{
            p.afegeixIncidencia("Turbina actiu");
        }

    }

    public float getCostOperatiu(){
        if (!getActivat())
            return 0;
        else
            return 20;
    }

    public void setCostOperatiu(float costOperatiu) {
        this.costOperatiu = costOperatiu;
    }

    public float calculaOutput(float input){
        if (!getActivat())
            return 0;

        else if (input < 100)
            return 0;

        else
            return input * 2;
    }
}