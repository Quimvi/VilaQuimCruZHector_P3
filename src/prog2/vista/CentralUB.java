/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.Dades;
import prog2.model.VariableNormal;

import java.util.Scanner;

/**
 *
 * @author Daniel Ortiz
 */
public class CentralUB {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    private Menu menu, subMenuBC, subMenuR, subMenuSR;
    private Adaptador adaptador;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;
    public Object[] llistaOpcions = new String[] {"Gestió barres de control", "Gestió reactor", "Gestió sistema refrigeració", "Mostrar estat central",
    "Mostrar bitàcola", "Mostrar incidències", "Obtenir demanda satisfeta amb configuració actual", "Finalitzar dia", "Guardar dades", "Carregar dades", "Sortir"};

    public Object[] llistaSubOpBC = new String[] {"Obtenir inserció barres", "Establir inserció barres", "Sortir"};
    public Object[] llistaSubOpR = new String[] {"Activar reactor", "Desactivar reactor", "Mostrar estat", "Sortir"};
    public Object[] llistaSubOpSR = new String[] {"Activar totes les bombes", "Desactivar totes les bombes", "Activar bomba", "Desactivar bomba", "Mostrar estat", "Sortir"};
    
    /* Constructor*/
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        menu = new Menu("Menú Central UB", llistaOpcions);
        subMenuBC = new Menu("Submenú barres de control", llistaSubOpBC);
        subMenuR = new Menu("Submenú reactor", llistaSubOpR);
        subMenuSR = new Menu("Submenú sistema refrigeració", llistaSubOpSR);
    }
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        String opcioM, camiDesti, camiOrigen;
        float insercioBarres = 0;
        do {
            menu.mostrarMenu();
            Scanner sc = new Scanner(System.in);
            opcioM = (String) menu.getOpcio(sc);

            switch (opcioM) {
                case "Gestió barres de control":
                    String subOpBC;
                    do {
                        subMenuBC.mostrarMenu();
                        subOpBC = (String) subMenuBC.getOpcio(sc);
                        switch (subOpBC) {
                            case "Obtenir inserció barres":
                                System.out.println(adaptador.getInsercioBarres() + "%");
                                break;

                            case "Establir inserció barres":
                                System.out.println("Estableix l'inserció de les barres: ");
                                insercioBarres = sc.nextFloat();
                                adaptador.setInsercioBarres(insercioBarres);
                                break;
                        }
                    } while (!subOpBC.equals("Sortir"));
                    break;

                case "Gestió reactor":
                    String subOpR;
                    do {
                        subMenuR.mostrarMenu();
                        subOpR = (String) subMenuR.getOpcio(sc);
                        switch (subOpR) {
                            case "Activar reactor":
                                adaptador.activaReactor();
                                System.out.println("Reactor activat");
                                break;

                            case "Desactivar reactor":
                                adaptador.desactivaReactor();
                                System.out.println("Reactor desactivat");
                                break;

                            case "Mostrar estat":
                                System.out.println(adaptador.getEstatReactor());
                                break;
                        }
                    } while (!subOpR.equals("Sortir"));
                    break;

                case "Gestió sistema refrigeració":
                    String subOpSR;
                    do {
                        subMenuSR.mostrarMenu();
                        subOpSR = (String) subMenuSR.getOpcio(sc);
                        switch (subOpSR) {
                            case "Activar totes les bombes":
                                break;

                            case "Desactivar totes les bombes":
                                break;

                            case "Activar bomba":
                                break;

                            case "Desactivar bomba":
                                break;

                            case "Mostrar estat":
                                System.out.println(adaptador.getEstatSistemaRefrigeracio());
                                break;
                        }
                    } while (!subOpSR.equals("Sortir"));
                    break;

                case "Mostrar estat central":
                    System.out.println(adaptador.getEstatActual());
                    break;

                case "Mostrar bitàcola":
                    System.out.println(adaptador.getBitacolaCompleta());
                    break;

                case "Mostrar incidències":
                    System.out.println(adaptador.getIncidencies());
                    break;

                case "Obtenir demanda satisfeta amb configuració actual":
                    System.out.println("La demanda satisfeta amb la configuració actual és de " + 100*(adaptador.getPotenciaGenerada()/demandaPotencia) + "%");
                    break;

                case "Finalitzar dia":
                    finalitzaDia();
                    break;

                case "Guardar dades":
                    System.out.println("Camí destí: ");
                    camiDesti = sc.next();
                    adaptador.guardaDades(camiDesti);
                    System.out.println("Dades guardades!");
                    break;

                case "Carregar dades":
                    System.out.println("Camí d'origen: ");
                    camiOrigen = sc.next();
                    adaptador.carregaDades(camiOrigen);
                    System.out.println("Dades carregades!");
                    break;

                default:
                    break;
            }
        } while (!opcioM.equals("Sortir"));
    }
    
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }
    
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        System.out.println(adaptador.finalitzaDia(demandaPotencia));
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }
}
