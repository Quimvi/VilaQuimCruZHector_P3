package prog2.model;

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
    public void setInsercioBarres(float insercioBarres) {
        this.insercioBarres = insercioBarres;
    }

    public float getInsercioBarres() {
        return insercioBarres;
    }

    public void setOutputReactor(float outputReactor) {
        this.outputReactor = outputReactor;
    }

    public float getOutputReactor() {
        return outputReactor;
    }

    public void setOutputSistemaDeRefrigeracio(float outputSistemaDeRefrigeracio) {
        this.outputSistemaDeRefrigeracio = outputSistemaDeRefrigeracio;
    }

    public float getOutputSistemaDeRefrigeracio() {
        return outputSistemaDeRefrigeracio;
    }

    public void setOutputGeneradorDeVapor(float outputGeneradorDeVapor) {
        this.outputGeneradorDeVapor = outputGeneradorDeVapor;
    }

    public float getOutputGeneradorDeVapor() {
        return outputGeneradorDeVapor;
    }

    public void setOutputTurbina(float outputTurbina) {
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
        return "# Pàgina Estat "
                + "\n- Dia: " + this.getDia()
                + "\n- Inserció Barres: " + this.getInsercioBarres() + "%"
                + "\n- Output Reactor: " + this.getOutputReactor() + " Graus"
                + "\n- Output Sistema de Refrigeració: " + this.getOutputSistemaDeRefrigeracio() + " Graus"
                + "\n- Output Generador de Vapor: " + this.getOutputGeneradorDeVapor() + " Graus"
                + "\n- Output Turbina: " + this.getOutputTurbina() + " Unitats de Potència";
    }
}
