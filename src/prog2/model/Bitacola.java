package prog2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bitacola implements InBitacola{

    private ArrayList<PaginaBitacola> paginesBitacola;

    public Bitacola(){
        paginesBitacola = new ArrayList<PaginaBitacola>();
    }

    public void afegeixPagina(PaginaBitacola p){
        paginesBitacola.add(p);
    }

    public List<PaginaIncidencies> getIncidencies(){
        List<PaginaIncidencies> llista = new ArrayList<>();
        Iterator<PaginaBitacola> itr = paginesBitacola.iterator();

        while(itr.hasNext()){
            PaginaBitacola pagina = itr.next();
            if (pagina instanceof PaginaIncidencies){
                PaginaIncidencies paginaIncidencies = (PaginaIncidencies) pagina;
                llista.add(paginaIncidencies);
            }

        }
        return llista;
    }
}
