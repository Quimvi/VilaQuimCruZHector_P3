package prog2.model;

public class PaginaEstat extends PaginaBitacola{
    private float insercioBarres;
    private float outputReactor;
    private float outputSistemaDeRefrigeracio;
    private float outputGeneradorDeVapor;
    private float outputTurbina;

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

    @Override
    public String toString(){
        return "# Pàgina Estat "+"\n- Dia:" + this.getDia() + "\n- Inserció Barres:" + this.getInsercioBarres() + "%"
                +"\n- Output Reactor:" + this.getOutputReactor() + "Graus" + "\n- Output Sistema de Refrigeració:" + this.getOutputSistemaDeRefrigeracio() + "Graus"
                +"\n- Output Generador de Vapor:" + this.getOutputGeneradorDeVapor() + " Graus"
                + "\n- Output Turbina:" + this.getOutputTurbina() + " Unitats de Potència";

    }
}
