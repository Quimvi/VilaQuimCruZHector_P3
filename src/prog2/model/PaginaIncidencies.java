package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;

public class PaginaIncidencies extends PaginaBitacola{
    private ArrayList<String> llistaIncidencies;

    public PaginaIncidencies(int dia){
        super(dia);
        llistaIncidencies = new ArrayList<String>();
    }

    public void afegeixIncidencia(String descIncidencia){
        llistaIncidencies.add(descIncidencia);
    }

    @Override
    public String toString(){
        StringBuffer concatenador = new StringBuffer();
        concatenador.append("# Pàgina Incidències \n").append("- Dia: ").append(this.getDia()).append("\n");
        Iterator<String> itr = llistaIncidencies.iterator();
        while(itr.hasNext()){
            concatenador.append("- Descripció Incidència: ");
            concatenador.append(itr).append("\n");
            itr.next();
        }
        return concatenador.toString();
    }
}