package prog2.adaptador;

import prog2.model.*;
import prog2.vista.CentralUBException;
import java.io.*;

public class Adaptador {
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti))) {
            oos.writeObject(_dades);
        } catch (IOException e) {
            throw new CentralUBException("Error al guardar les dades: " + e.getMessage());
        }
    }

    public void carregaDades(String camiOrigen) throws CentralUBException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            _dades = (Dades) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CentralUBException("Error al carregar les dades: " + e.getMessage());
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

    public String getEstatReactor() {
        return _dades.mostraReactor().toString();
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