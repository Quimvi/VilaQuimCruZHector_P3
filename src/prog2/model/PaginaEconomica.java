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
                           float beneficis, float penalitzacioExcesProduccio,
                           float costOperatiu, float guanysAcumulats){
        super(dia);
        setDemandaPotencia(demandaPotencia);
        setPotenciaGenerada(potenciaGenerada);
        setBeneficis(beneficis);
        setPenalitzacioExesProduccio(penalitzacioExcesProduccio);
        setCostOperatiu(costOperatiu);
        setGuanysAcumulats(guanysAcumulats);
    }

    public void setDemandaPotencia(float demandaPotencia) {
        this.demandaPotencia = demandaPotencia;
    }

    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    public void setPotenciaGenerada(float potenciaGenerada) {
        this.potenciaGenerada = potenciaGenerada;
    }

    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    public float getPercentatgePotencia() {
        return percentatgePotencia = 100*(getPotenciaGenerada()/getDemandaPotencia());
    }

    public void setBeneficis(float beneficis) {
        this.beneficis = beneficis;
    }

    public float getBeneficis() {
        return beneficis;
    }

    public void setPenalitzacioExesProduccio(float penalitzacioExesProduccio) {
        this.penalitzacioExesProduccio = penalitzacioExesProduccio;
    }

    public float getPenalitzacioExesProduccio() {
        return penalitzacioExesProduccio;
    }

    public void setCostOperatiu(float costOperatiu) {
        this.costOperatiu = costOperatiu;
    }

    public float getCostOperatiu() {
        return costOperatiu;
    }

    public void setGuanysAcumulats(float guanysAcumulats) {
        this.guanysAcumulats = guanysAcumulats;
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

