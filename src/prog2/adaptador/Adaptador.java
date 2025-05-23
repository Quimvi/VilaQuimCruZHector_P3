package prog2.adaptador;

import prog2.model.*;
import prog2.vista.CentralUBException;
import java.io.*;

public class Adaptador implements Serializable {
    private Dades _dades;

    // Constructor
    public Adaptador() {
        this._dades = new Dades();
    }

    public SistemaRefrigeracio getSistemaRefrigeracio() {
        return _dades.getSistemaRefrigeracio();
    }

    /* ********
     * MÈTODES DE PERSISTÈNCIA *
     ********* */
    public void guardaDades(String camiDesti) throws CentralUBException {
        File fitxer = new File(camiDesti);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(fitxer);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(_dades);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void carregaDades(String camiOrigen) throws CentralUBException {
        try {
            FileInputStream fin = new FileInputStream(camiOrigen);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Dades centralUBCarregada = (Dades) ois.readObject();
            ois.close();
            fin.close();
            this._dades = centralUBCarregada;
        } catch (FileNotFoundException e) {
            throw new CentralUBException("No s'ha trobat l'arxiu: "+e.getMessage());
        } catch (IOException e) {
            throw new CentralUBException("Error al carregar l'arxiu: "+e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new CentralUBException("No s'ha pogut fer càsting de les dades centralUB: "+e.getMessage());
        }

    }

    /* ********
     * MÈTODES DE GESTIÓ *
     ********* */

    // Reactor
    public void activaReactor() throws CentralUBException {
            _dades.activaReactor();
    }

    public void desactivaReactor() {
        _dades.desactivaReactor();
    }

    public Reactor getEstatReactor() {
        return _dades.mostraReactor();
    }


    // Sistema de Refrigeració
    public void activaBomba(int idBomba) throws CentralUBException {
            _dades.activaBomba(idBomba);
    }

    public void desactivaBomba(int idBomba) throws CentralUBException {
        _dades.desactivaBomba(idBomba);
    }

    public String getEstatSistemaRefrigeracio() {
        return _dades.mostraSistemaRefrigeracio().toString();
    }

    // Bitàcola i Informes

    public String getBitacolaCompleta() {
        return _dades.mostraBitacola().toString();
    }

    public String getIncidencies() {
        StringBuilder sb = new StringBuilder();
        for (PaginaIncidencies pagina : _dades.mostraIncidencies()) {
            sb.append(pagina.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    // Gestió del Dia
    public Bitacola finalitzaDia(float demandaPotencia) throws CentralUBException {
            return _dades.finalitzaDia(demandaPotencia);
    }
    public int getDia(){
        return _dades.getDia();
    }


    public String getEstatActual() {
        return _dades.mostraEstat().toString();
    }

    // Mètodes addicionals
    public void setDemenda(float demandaPotencia){
        _dades.setDemandaPotencia(demandaPotencia);
    }

    public float getDemenda(){
       return _dades.getDemandaPotencia();
    }

    public float getPotenciaGenerada() {
        return _dades.calculaPotencia();
    }

    public float getGuanysAcumulats() {
        return _dades.getGuanysAcumulats();
    }

    public void setInsercioBarres(float grau) throws CentralUBException {
            _dades.setInsercioBarres(grau);
    }

    public float getInsercioBarres() {
        return _dades.getInsercioBarres();
    }
}