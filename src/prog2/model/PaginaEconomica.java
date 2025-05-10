package prog2.model;

public class PaginaEconomica extends PaginaBitacola{
    private float demandaPotencia;
    private float potenciaGenerada;
    private float penalitzacioExcesProduccio;
    private float guanysAcumulats;
    private Reactor reactor;
    private SistemaRefrigeracio refrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;

    public PaginaEconomica(int dia, float demandaPotencia,float potenciaGenerada,float penalitzacioExcesProduccio ,float guanysAcumulats,Reactor reactor,SistemaRefrigeracio refrigeracio,GeneradorVapor generadorVapor,Turbina turbina){
        super(dia);
        setReactor(reactor);
        setTurbina(turbina);
        setBombes(refrigeracio);
        setGeneradorVapor(generadorVapor);

        setDemandaPotencia(demandaPotencia);
        setPotenciaGenerada(potenciaGenerada);
        setPenalitzacioExcesProduccio(penalitzacioExcesProduccio);

        setGuanysAcumulats(guanysAcumulats);
    }

    public void setGeneradorVapor(GeneradorVapor generadorVapor) {
        this.generadorVapor = generadorVapor;
    }

    public void setBombes(SistemaRefrigeracio refrigeracio) {
        this.refrigeracio = refrigeracio;
    }

    public void setReactor(Reactor reactor) {
        this.reactor = reactor;
    }

    public void setTurbina(Turbina turbina) {
        this.turbina = turbina;
    }

    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    public void setDemandaPotencia(float demandaPotencia) {
        this.demandaPotencia = demandaPotencia;
    }

    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    public void setPotenciaGenerada(float potenciaGenerada) {
        this.potenciaGenerada = potenciaGenerada;
    }

    public float getPercentatgePotencia() {
        return 100*(getPotenciaGenerada()/getDemandaPotencia());
    }

    public float getBeneficis(){
        if (demandaPotencia >= potenciaGenerada){
            return potenciaGenerada;
        }
        else{
            return getDemandaPotencia() - getPenalitzacioExcesProduccio();
        }
    }

    public float getPenalitzacioExcesProduccio() {
        if (getDemandaPotencia() >= getPotenciaGenerada())
            return 0;

        penalitzacioExcesProduccio = getPotenciaGenerada() - getDemandaPotencia();
        return penalitzacioExcesProduccio;
    }

    public void setPenalitzacioExcesProduccio(float penalitzacioExcesProduccio) {
        this.penalitzacioExcesProduccio = penalitzacioExcesProduccio;
    }

    public float getCostOperatiu() {
        return refrigeracio.getCostOperatiu() + reactor.getCostOperatiu() + turbina.getCostOperatiu() + generadorVapor.getCostOperatiu();
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    public void setGuanysAcumulats(float guanysAcumulats) {
        this.guanysAcumulats =  guanysAcumulats + getBeneficis() - getCostOperatiu();
    }

    @Override
    public String toString(){
        return "# Pàgina Econòmica "+"\n- Dia:" + this.getDia() + "\n- Demanda de Potència:" + this.getDemandaPotencia()
                +"\n-  Potència Generada:" + this.getPotenciaGenerada() +"\n- Demanda de Potència Satisfeta::" + this.getPercentatgePotencia() + "%"
                +"\n- Beneficis:" + this.getBeneficis() + " Unitats Econòmiques"
                + "\n- Penalització Excés Producció:" + this.getPenalitzacioExcesProduccio() + " Unitats Econòmiques"
                + "\n- Cost Operatiu:" + this.getCostOperatiu() + " Unitats Econòmiques"
                + "\n- Guanys acumulats:" + this.getGuanysAcumulats() + " Unitats Econòmiques";
    }
}

