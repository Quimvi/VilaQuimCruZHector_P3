package prog2.model;

import prog2.vista.CentralUBException;

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
    public void setGeneradorVapor(GeneradorVapor generadorVapor) throws CentralUBException {
        if (generadorVapor == null) {
            throw new CentralUBException("El generador de vapor no pot ser null");
        }
        this.generadorVapor = generadorVapor;
    }

    public void setBombes(SistemaRefrigeracio refrigeracio) throws CentralUBException {
        if (refrigeracio == null) {
            throw new CentralUBException("El sistema de refrigeració no pot ser null");
        }
        this.refrigeracio = refrigeracio;
    }

    public void setReactor(Reactor reactor) throws CentralUBException {
        if (reactor == null) {
            throw new CentralUBException("El reactor no pot ser null");
        }
        this.reactor = reactor;
    }

    public void setTurbina(Turbina turbina) throws CentralUBException {
        if (turbina == null) {
            throw new CentralUBException("El turbina no pot ser null");
        }
        this.turbina = turbina;
    }

    // Getters i setters de dades econòmiques
    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    public void setDemandaPotencia(float demandaPotencia) throws CentralUBException {
        if (demandaPotencia < 0) {
            throw new CentralUBException("La demanda de potència no pot ser negativa");
        }
        this.demandaPotencia = demandaPotencia;
    }
    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    public void setPotenciaGenerada(float potenciaGenerada) throws CentralUBException {
        if (potenciaGenerada < 0) {
            throw new CentralUBException("La potència generada no pot ser negativa");
        }
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

    public void setPenalitzacioExcesProduccio(float penalitzacioExcesProduccio) throws CentralUBException {
        if (penalitzacioExcesProduccio < 0) {
            throw new CentralUBException("La penalització per excés de producció no pot ser negativa");
        }
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
