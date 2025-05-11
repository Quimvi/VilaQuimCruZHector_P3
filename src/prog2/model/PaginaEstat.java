package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que representa una pàgina d'estat de la bitàcola.
 * Emmagatzema valors tècnics dels components per a un dia determinat.
 */
public class PaginaEstat extends PaginaBitacola {
    private float insercioBarres; // Percentatge d’inserció de les barres de control al reactor
    private float outputReactor; // Output tèrmic del reactor en graus
    private float outputSistemaDeRefrigeracio; // Output del sistema de refrigeració en graus
    private float outputGeneradorDeVapor; // Output del generador de vapor en graus
    private float outputTurbina; // Output de la turbina en unitats de potència

    /**
     * Constructor que inicialitza tots els valors tècnics per a un dia concret.
     */
    public PaginaEstat(int dia, float insercioBarres, float outputReactor,
                       float outputSistemaDeRefrigeracio, float outputGeneradorDeVapor,
                       float outputTurbina) {
        super(dia);
        setInsercioBarres(insercioBarres);
        setOutputReactor(outputReactor);
        setOutputSistemaDeRefrigeracio(outputSistemaDeRefrigeracio);
        setOutputGeneradorDeVapor(outputGeneradorDeVapor);
        setOutputTurbina(outputTurbina);
    }

    // Getters i setters per cada atribut tècnic
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres < 0 || insercioBarres > 100) {
            throw new CentralUBException("La inserció de barres ha d'estar entre 0 i 100%");
        }
        this.insercioBarres = insercioBarres;
    }

    public float getInsercioBarres() {
        return insercioBarres;
    }

    public void setOutputReactor(float outputReactor) throws CentralUBException {
        if (outputReactor < 0) {
            throw new CentralUBException("L'output del reactor no pot ser negatiu");
        }
        this.outputReactor = outputReactor;
    }

    public float getOutputReactor() {
        return outputReactor;
    }

    public void setOutputSistemaDeRefrigeracio(float outputSistemaDeRefrigeracio) throws CentralUBException {
        if (outputSistemaDeRefrigeracio < 0) {
            throw new CentralUBException("L'output del sistema de refrigeració no pot ser negatiu");
        }
        this.outputSistemaDeRefrigeracio = outputSistemaDeRefrigeracio;
    }

    public float getOutputSistemaDeRefrigeracio() {
        return outputSistemaDeRefrigeracio;
    }

    public void setOutputGeneradorDeVapor(float outputGeneradorDeVapor) throws CentralUBException {
        if (outputGeneradorDeVapor < 0) {
            throw new CentralUBException("L'output del generador no pot ser negatiu");
        }
        this.outputGeneradorDeVapor = outputGeneradorDeVapor;
    }

    public float getOutputGeneradorDeVapor() {
        return outputGeneradorDeVapor;
    }

    public void setOutputTurbina(float outputTurbina) throws CentralUBException {
        if (outputTurbina < 0) {
            throw new CentralUBException("L'output de la turbina no pot ser negatiu");
        }
        this.outputTurbina = outputTurbina;
    }

    public float getOutputTurbina() {
        return outputTurbina;
    }

    /**
     * Retorna una representació en format text de l’estat tècnic dels components.
     */
    @Override
    public String toString() {
        return "\n# Pàgina Estat "
                + "\n- Dia: " + this.getDia()
                + "\n- Inserció Barres: " + this.getInsercioBarres() + "%"
                + "\n- Output Reactor: " + this.getOutputReactor() + " Graus"
                + "\n- Output Sistema de Refrigeració: " + this.getOutputSistemaDeRefrigeracio() + " Graus"
                + "\n- Output Generador de Vapor: " + this.getOutputGeneradorDeVapor() + " Graus"
                + "\n- Output Turbina: " + this.getOutputTurbina() + " Unitats de Potència" + "\n";
    }
}
