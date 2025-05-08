/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Daniel Ortiz
 */
public class Dades implements InDades{
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;
    private final VariableUniforme variableUniforme;
    private final SistemaRefrigeracio sistemaRefrigeracio;
    private final Turbina turbina;
    private final GeneradorVapor generadorVapor;
    private final Reactor reactor;
    private float insercioBarres;
    private float guanysAcumulats;
    private Bitacola bitacola;
    private int dia;


    // Afegir atributs:

    public Dades(){
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.reactor.desactiva();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);

        this.sistemaRefrigeracio.desactiva();
    }
    
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        PaginaEconomica paginaEconomia = new PaginaEconomica(dia,demandaPotencia,calculaPotencia(),PENALITZACIO_EXCES_POTENCIA,guanysAcumulats,reactor,sistemaRefrigeracio,generadorVapor,turbina);
        this.guanysAcumulats = paginaEconomia.getGuanysAcumulats();
        return paginaEconomia;
    }

    public float getInsercioBarres(){
        return insercioBarres;
    }

    public void setInsercioBarres(float insercioBarres) throws CentralUBException{
        if(insercioBarres > 100){
            throw new CentralUBException("El grau d'inserció de barres ha de ser de 0-100");
        }else {
            this.insercioBarres = insercioBarres;
        }
    }

    public void activaReactor() throws CentralUBException{
        reactor.activa();
    }

    public void desactivaReactor(){
        reactor.desactiva();
    }

    public Reactor mostraReactor(){
        return this.reactor;
    }

    public void activaBomba(int id) throws CentralUBException{
        if (id > 3 || id < 0){
            throw new CentralUBException("El Id ha de ser de 0-3");
        }else {
            Iterator<BombaRefrigerant> itr = sistemaRefrigeracio.getLlistaBomba().iterator();
            while (itr.hasNext()) {
                BombaRefrigerant bombaRefrigerant = itr.next();
                if (bombaRefrigerant.getId() == id) {
                    bombaRefrigerant.activa();
                }
            }
        }
    }

    public void desactivaBomba(int id){
        if (id > 3 || id < 0){
            throw new CentralUBException("El Id ha de ser de 0-3");
        }else {
            Iterator<BombaRefrigerant> itr = sistemaRefrigeracio.getLlistaBomba().iterator();
            while (itr.hasNext()) {
                BombaRefrigerant bombaRefrigerant = itr.next();
                if (bombaRefrigerant.getId() == id) {
                    bombaRefrigerant.desactiva();
                }
            }
        }
    }

    public SistemaRefrigeracio mostraSistemaRefrigeracio(){
        return sistemaRefrigeracio;
    }

    public float calculaPotencia(){
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres))));
    }

    public float getGuanysAcumulats(){
        return guanysAcumulats;
    }

    public PaginaEstat mostraEstat(){
        float temperaturaReactor = reactor.getTemperaturaReactor();
        float tempSistemaDeRefrigeracio = sistemaRefrigeracio.calculaOutput(reactor.getTemperaturaReactor());
        float generadorVapor = this.generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres)));
        float potenciaGenerada = calculaPotencia();

        PaginaEstat paginaEstat = new PaginaEstat(dia, getInsercioBarres(), temperaturaReactor, tempSistemaDeRefrigeracio,generadorVapor,potenciaGenerada );
        return paginaEstat;
    }

    public Bitacola mostraBitacola(){
        return bitacola;
    }

    public List<PaginaIncidencies> mostraIncidencies(){
        return mostraBitacola().getIncidencies();
    }

    /**
     * Aquest mètode ha de establir la nova temperatura del reactor.
     */
    private void refrigeraReactor() {
          reactor.setTemperaturaReactor(reactor.getTemperaturaReactor() - sistemaRefrigeracio.calculaOutput(insercioBarres));
    }

    /**
     * Aquest mètode ha de revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
          // Completar
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
        turbina.revisa(paginaIncidencies);
    }

    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);
        
        // Genera pàgina d'estat amb la configuració escollida (la nova pàgina
        // d'estat inclou la nova configuració escollida pel operador abans de
        // refrigerar el reactor)
        PaginaEstat paginaEstat = mostraEstat();

        // Actualitza estat de la central...

        // Refrigera el reactor
        refrigeraReactor();

        // Revisa els components de la central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);
        
        // Incrementa dia
        dia += 1;
        
        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        
        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }
}