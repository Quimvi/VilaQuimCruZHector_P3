package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;
import java.util.Iterator;

public class SistemaRefrigeracio implements InComponent {

    private ArrayList<BombaRefrigerant> llistaBomba;
    private boolean activat = false;

    public SistemaRefrigeracio(){
        llistaBomba = new ArrayList<BombaRefrigerant>();
        activat = getActivat();
    }

    public void afegirBomba(BombaRefrigerant b){
        llistaBomba.add(b);
    }

    public ArrayList<BombaRefrigerant> getLlistaBomba(){
        return llistaBomba;
    }


    public void activa() throws CentralUBException {
        Iterator<BombaRefrigerant> itr = llistaBomba.iterator();
        while (itr.hasNext()) {
            BombaRefrigerant bombaRefrigerant = itr.next();
            if (!bombaRefrigerant.getForaDeServei())
                bombaRefrigerant.activa();

            //no se si es necessita una exceció
        }
    }

    public void desactiva() throws CentralUBException{
        Iterator<BombaRefrigerant> itr = llistaBomba.iterator();
        while(itr.hasNext()){
            BombaRefrigerant bombaRefrigerant = itr.next();
            if (!bombaRefrigerant.getForaDeServei())
                bombaRefrigerant.desactiva();

            //no se si es necessita una exceció
        }
    }

    public boolean getActivat() {
        Iterator<BombaRefrigerant> itr = llistaBomba.iterator();
        while (itr.hasNext()) {
            BombaRefrigerant bombaRefrigerant = itr.next();
            if (bombaRefrigerant.getActivat())
                return true;
        }
            return false;
    }

    public void revisa (PaginaIncidencies p){
        Iterator<BombaRefrigerant> itr = llistaBomba.iterator();
        while (itr.hasNext()) {
            BombaRefrigerant bombaRefrigerant = itr.next();
            bombaRefrigerant.revisa(p);
        }

    }

    public float getCostOperatiu(){
        float cost = 0;
        Iterator<BombaRefrigerant> itr = llistaBomba.iterator();
        while (itr.hasNext()) {
            BombaRefrigerant bombaRefrigerant = itr.next();
            if (bombaRefrigerant.getActivat()) {
                cost += bombaRefrigerant.getCostOperatiu();
            }
        }
        return cost;
    }


    public float calculaOutput(float input){
        float input2 = 0;

        Iterator<BombaRefrigerant> itr = llistaBomba.iterator();
        while (itr.hasNext()) {
            BombaRefrigerant bombaRefrigerant = itr.next();
            if (bombaRefrigerant.getActivat()) {
                input2 += bombaRefrigerant.getCapacitat();
            }
        }
        if (input <= input2){
            return input;
        }else{
            return input2;
        }
    }

}


