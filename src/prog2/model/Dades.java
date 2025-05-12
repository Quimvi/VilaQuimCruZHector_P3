// Paquet on es troba la classe
package prog2.model;

// Importació de classes relacionades amb la vista i el model
import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import java.util.Iterator;
import java.util.List;

/**
 * Classe que encapsula totes les dades i components de la central
 * i gestiona el seu estat, operacions i l'evolució diària.
 * @author Daniel Ortiz
 */
public class Dades implements InDades {

    // Constants per configurar el sistema
    public final static long VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;

    // Atributs principals de la central
    private final VariableUniforme variableUniforme;
    private final SistemaRefrigeracio sistemaRefrigeracio;
    private final Turbina turbina;
    private final GeneradorVapor generadorVapor;
    private final Reactor reactor;
    private float insercioBarres;
    private float guanysAcumulats;
    private Bitacola bitacola;
    private int dia;

    // Constructor: inicialitza els components de la central
    public Dades() {
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

        // Creació i afegit de les 4 bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);

        sistemaRefrigeracio.afegirBomba(b0);
        sistemaRefrigeracio.afegirBomba(b1);
        sistemaRefrigeracio.afegirBomba(b2);
        sistemaRefrigeracio.afegirBomba(b3);

        sistemaRefrigeracio.desactiva();
    }

    // Genera i actualitza una pàgina econòmica amb l'estat actual
    private PaginaEconomica actualitzaEconomia(float demandaPotencia) {
        PaginaEconomica paginaEconomia = new PaginaEconomica(
                dia,
                demandaPotencia,
                calculaPotencia(),
                PENALITZACIO_EXCES_POTENCIA,
                guanysAcumulats,
                reactor,
                sistemaRefrigeracio,
                generadorVapor,
                turbina
        );
        this.guanysAcumulats = paginaEconomia.getGuanysAcumulats();
        return paginaEconomia;
    }

    // Get i set del grau d'inserció de barres de control
    public float getInsercioBarres() {
        return insercioBarres;
    }

    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres > 100) {
            throw new CentralUBException("El grau d'inserció de barres ha de ser de 0-100");
        } else {
            this.insercioBarres = insercioBarres;
        }
    }

    // Activació/desactivació del reactor
    public void activaReactor() {
        reactor.activa();
    }

    public void desactivaReactor() {
        reactor.desactiva();
    }

    public Reactor mostraReactor() {
        reactor.calculaOutput(insercioBarres);
        return this.reactor;
    }

    // Activació/desactivació d'una bomba refrigerant per ID
    public void activaBomba(int id) throws CentralUBException {
        if (id > 3 || id < 0) {
            throw new CentralUBException("El Id ha de ser de 0-3");
        } else {
            for (BombaRefrigerant bomba : sistemaRefrigeracio.getLlistaBomba()) {
                if (bomba.getId() == id) {
                    bomba.activa();
                }
            }
        }
    }

    public void desactivaBomba(int id) {
        if (id > 3 || id < 0) {
            throw new CentralUBException("El Id ha de ser de 0-3");
        } else {
            for (BombaRefrigerant bomba : sistemaRefrigeracio.getLlistaBomba()) {
                if (bomba.getId() == id) {
                    bomba.desactiva();
                }
            }
        }
    }

    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    // Calcul de la potència generada segons la cadena: reactor → refrigeració → vapor → turbina
    public float calculaPotencia() {
        return turbina.calculaOutput(
                generadorVapor.calculaOutput(
                        sistemaRefrigeracio.calculaOutput(
                                reactor.calculaOutput(insercioBarres)
                        )
                )
        );
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    // Mostra una pàgina amb l’estat actual de la central
    public PaginaEstat mostraEstat() {
        float temperaturaReactor = reactor.getTemperaturaReactor();
        float tempSistemaRefrigeracio = sistemaRefrigeracio.calculaOutput(temperaturaReactor);
        float generadorVaporOutput = generadorVapor.calculaOutput(tempSistemaRefrigeracio);
        float potenciaGenerada = calculaPotencia();

        return new PaginaEstat(
                dia,
                getInsercioBarres(),
                temperaturaReactor,
                tempSistemaRefrigeracio,
                generadorVaporOutput,
                potenciaGenerada
        );
    }

    public Bitacola mostraBitacola() {
        return bitacola;
    }

    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }

    // Refreda el reactor segons la capacitat del sistema de refrigeració
    private void refrigeraReactor() {
        reactor.setTemperaturaReactor(
                reactor.getTemperaturaReactor() - sistemaRefrigeracio.calculaOutput(insercioBarres)
        );
    }

    // Revisa tots els components i registra les incidències trobades
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
        turbina.revisa(paginaIncidencies);
    }

    /**
     * Finalitza el dia: registra economia, estat i incidències, refreda el reactor,
     * revisa els components i incrementa el comptador de dia.
     * @param demandaPotencia la demanda de potència diària
     * @return una bitàcola amb les tres pàgines del dia
     */
    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualització de la situació econòmica
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);

        // Captura de l’estat actual abans de refredar
        PaginaEstat paginaEstat = mostraEstat();

        // Refredament del reactor
        refrigeraReactor();

        // Revisió de l'estat dels components
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);

        // Increment del dia
        dia++;

        // Afegir pàgines a la bitàcola global
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);

        // Retorna la bitàcola específica del dia (nova instància)
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);

        return bitacolaDia;
    }
}
