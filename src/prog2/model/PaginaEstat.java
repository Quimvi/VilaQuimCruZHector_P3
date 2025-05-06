package prog2.model;

public class PaginaEstat extends PaginaBitacola{
    private float insercioBarres;
    private float outputReactor;
    private float outputSistemaDeRefrigeracio;
    private float outputGeneradorDeVapor;
    private float outputTurvina;

    public PaginaEstat(int dia, float insercioBarres,float outputReactor,
                       float outputSistemaDeRefrigeracio,
                       float outputGeneradorDeVapor, float outputTurvina){
        super(dia);
        this.insercioBarres = insercioBarres; this.outputReactor = outputReactor;
        this.outputSistemaDeRefrigeracio = outputSistemaDeRefrigeracio;
        this.outputGeneradorDeVapor = outputGeneradorDeVapor; this.outputTurvina = outputTurvina;
    }

    public float getInsercioBarres() {
        return insercioBarres;
    }

    public float getOutputReactor() {
        return outputReactor;
    }

    public float getOutputSistemaDeRefrigeracio() {
        return outputSistemaDeRefrigeracio;
    }

    public float getOutputGeneradorDeVapor() {
        return outputGeneradorDeVapor;
    }

    public float getOutputTurvina() {
        return outputTurvina;
    }

    @Override
    public String toString(){
        return "# Pàgina Estat "+"\n- Dia:" + this.getDia() + "\n- Inserció Barres:" + this.getInsercioBarres() + "%"
                +"\n- Output Reactor:" + this.getOutputReactor() + "Graus" + "\n- Output Sistema de Refrigeració:" + this.getOutputSistemaDeRefrigeracio() + "Graus"
                +"\n- Output Generador de Vapor:" + this.getOutputGeneradorDeVapor() + " Graus"
                + "\n- Output Turbina:" + this.getOutputTurvina() + " Unitats de Poténcia";

    }
}
