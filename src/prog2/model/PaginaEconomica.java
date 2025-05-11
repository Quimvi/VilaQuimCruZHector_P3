package prog2.model;

/**
 * Classe que representa una pàgina econòmica de la bitàcola.
 * Inclou informació sobre la demanda i generació de potència,
 * penalitzacions, costos i guanys econòmics.
 */
public class PaginaEconomica extends PaginaBitacola {
    private float demandaPotencia;
    private float potenciaGenerada;
    private float penalitzacioExcesProduccio;
    private float guanysAcumulats;

    // Referències als components per calcular els costos operatius
    private Reactor reactor;
    private SistemaRefrigeracio refrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;

    /**
     * Constructor que inicialitza tots els valors econòmics de la pàgina i els components implicats.
     */
    public PaginaEconomica(int dia, float demandaPotencia, float potenciaGenerada,
                           float penalitzacioExcesProduccio, float guanysAcumulats,
                           Reactor reactor, SistemaRefrigeracio refrigeracio,
                           GeneradorVapor generadorVapor, Turbina turbina) {
        super(dia); // Assigna el dia de la pàgina

        // Assignació de valors i referències als components
        setReactor(reactor);
        setTurbina(turbina);
        setBombes(refrigeracio);
        setGeneradorVapor(generadorVapor);
        setDemandaPotencia(demandaPotencia);
        setPotenciaGenerada(potenciaGenerada);
        setPenalitzacioExcesProduccio(penalitzacioExcesProduccio);
        setGuanysAcumulats(guanysAcumulats);
    }

    // Setters dels components
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

    // Getters i setters de dades econòmiques
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

    /**
     * Percentatge de la demanda de potència que s’ha satisfet.
     */
    public float getPercentatgePotencia() {
        return 100 * (getPotenciaGenerada() / getDemandaPotencia());
    }

    /**
     * Calcula els beneficis obtinguts.
     * Si es compleix la demanda, els beneficis són iguals a la potència generada.
     * Si hi ha excés, s’hi resta la penalització.
     */
    public float getBeneficis() {
        if (demandaPotencia >= potenciaGenerada) {
            return potenciaGenerada;
        } else {
            return getDemandaPotencia() - getPenalitzacioExcesProduccio();
        }
    }

    /**
     * Calcula la penalització per excés de producció.
     */
    public float getPenalitzacioExcesProduccio() {
        if (getDemandaPotencia() >= getPotenciaGenerada())
            return 0;
        penalitzacioExcesProduccio = getPotenciaGenerada() - getDemandaPotencia();
        return penalitzacioExcesProduccio;
    }

    public void setPenalitzacioExcesProduccio(float penalitzacioExcesProduccio) {
        this.penalitzacioExcesProduccio = penalitzacioExcesProduccio;
    }

    /**
     * Calcula el cost total operatiu de tots els components.
     */
    public float getCostOperatiu() {
        return refrigeracio.getCostOperatiu()
                + reactor.getCostOperatiu()
                + turbina.getCostOperatiu()
                + generadorVapor.getCostOperatiu();
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    /**
     * Actualitza els guanys acumulats amb els beneficis nets (beneficis - costos).
     */
    public void setGuanysAcumulats(float guanysAcumulats) {
        this.guanysAcumulats = guanysAcumulats + getBeneficis() - getCostOperatiu();
    }

    /**
     * Mostra tota la informació econòmica d’un dia en format llegible.
     */
    @Override
    public String toString() {
        return "# Pàgina Econòmica "
                + "\n- Dia: " + this.getDia()
                + "\n- Demanda de Potència: " + this.getDemandaPotencia()
                + "\n- Potència Generada: " + this.getPotenciaGenerada()
                + "\n- Demanda de Potència Satisfeta: " + this.getPercentatgePotencia() + "%"
                + "\n- Beneficis: " + this.getBeneficis() + " Unitats Econòmiques"
                + "\n- Penalització Excés Producció: " + this.getPenalitzacioExcesProduccio() + " Unitats Econòmiques"
                + "\n- Cost Operatiu: " + this.getCostOperatiu() + " Unitats Econòmiques"
                + "\n- Guanys acumulats: " + this.getGuanysAcumulats() + " Unitats Econòmiques";
    }
}
