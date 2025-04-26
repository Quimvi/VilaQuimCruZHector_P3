package prog2.model;

public class PaginaEconomica extends PaginaBitacola{
    private float demandaPotencia;
    private float potenciaGenerada;
    private float percentatgePotencia;
    private float beneficis;
    private float penalitzacioExesProduccio;
    private float costOperatiu;
    private float guanysAcumulats;

    public PaginaEconomica(int dia, float demandaPotencia,float potenciaGenerada,
                           float beneficis, float penalitzacioExesProduccio,
                           float costOperatiu, float guanysAcumulats){
        super(dia);
        this.demandaPotencia = demandaPotencia; this.potenciaGenerada = potenciaGenerada; this.percentatgePotencia = 100*(getPotenciaGenerada()/getDemandaPotencia());
        this.beneficis = beneficis; this.penalitzacioExesProduccio = penalitzacioExesProduccio;
        this.costOperatiu = costOperatiu; this.guanysAcumulats = guanysAcumulats;
    }

    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    public float getPercentatgePotencia() {
        return percentatgePotencia;
    }

    public float getBeneficis() {
        return beneficis;
    }

    public float getPenalitzacioExesProduccio() {
        return penalitzacioExesProduccio;
    }

    public float getCostOperatiu() {
        return costOperatiu;
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }
    @Override
    public String toString(){
        return "# Pàgina Econòmica "+"\n- Dia:" + this.getDia() + "\n- Demanda de Potència:" + this.getDemandaPotencia()
                +"\n-  Potència Generada:" + this.getPotenciaGenerada() +"\n- Demanda de Potència Satisfeta::" + this.getPercentatgePotencia() + "%"
                +"\n- Beneficis:" + this.getBeneficis() + " Unitats Econòmiques"
                + "\n- Penalització Excés Producció:" + this.getPenalitzacioExesProduccio() + " Unitats Econòmiques"
                + "\n- Cost Operatiu:" + this.getCostOperatiu() + " Unitats Econòmiques"
                + "\n- Guanys acumulats:" + this.getGuanysAcumulats() + " Unitats Econòmiques";
    }
}

