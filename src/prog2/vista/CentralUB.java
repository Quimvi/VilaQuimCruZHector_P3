/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;
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
    private Menu<OpcioMenuPrincipal> menu;
    private Menu<OpcioSubMenuBC> subMenuBC;
    private Menu<OpcioSubMenuR> subMenuR;
    private Menu<OpcioSubMenuSR> subMenuSR;
    private Adaptador adaptador;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;
    
    /** Constructor**/
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        adaptador = new Adaptador();
        menu = new Menu<>("Menú Central UB", OpcioMenuPrincipal.values());
        subMenuBC = new Menu<>("Submenú barres de control", OpcioSubMenuBC.values());
        subMenuR = new Menu<>("Submenú reactor", OpcioSubMenuR.values());
        subMenuSR = new Menu<>("Submenú sistema refrigeració", OpcioSubMenuSR.values());
    }

    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        int bombaActivar, bombaDesactivar;
        String camiDesti, camiOrigen;
        float insercioBarres = 0;

        OpcioMenuPrincipal opcioM;
        do {
            menu.mostrarMenu();
            Scanner sc = new Scanner(System.in);
            opcioM = menu.getOpcio(sc);

            switch (opcioM) {
                case GESTIO_BARRES_CONTROL:
                    OpcioSubMenuBC subOpBC;
                    do {
                        subMenuBC.mostrarMenu();
                        subOpBC = subMenuBC.getOpcio(sc);
                        switch (subOpBC) {
                            case OBTENIR_INSERCIO:
                                System.out.println(adaptador.getInsercioBarres() + "%");
                                break;

                            case ESTABLIR_INSERCIO:
                                try {
                                    System.out.println("Estableix l'inserció de les barres: ");
                                    insercioBarres = sc.nextFloat();
                                    adaptador.setInsercioBarres(insercioBarres);
                                } catch (CentralUBException e) {
                                    System.out.println("Error en les barres de control: " + e.getMessage());
                                }

                                break;

                            case SORTIR:
                                break;
                        }
                    } while (subOpBC != OpcioSubMenuBC.SORTIR);
                    break;

                case GESTIO_REACTOR:
                    OpcioSubMenuR subOpR;
                    do {
                        subMenuR.mostrarMenu();
                        subOpR = subMenuR.getOpcio(sc);
                        adaptador.getEstatReactor();
                        switch (subOpR) {
                            case ACTIVAR_REACTOR:
                                adaptador.activaReactor();
                                System.out.println("Reactor activat");
                                break;

                            case DESACTIVAR_REACTOR:
                                adaptador.desactivaReactor();
                                System.out.println("Reactor desactivat");
                                break;

                            case MOSTRAR_ESTAT:
                                System.out.println(adaptador.getEstatReactor());
                                break;

                            case SORTIR:
                                break;
                        }
                    } while (subOpR != OpcioSubMenuR.SORTIR);
                    break;

                case GESTIO_SISTEMA_REFRIGERACIO:
                    OpcioSubMenuSR subOpSR;
                    do {
                        subMenuSR.mostrarMenu();
                        subOpSR = subMenuSR.getOpcio(sc);
                        switch (subOpSR) {
                            case ACTIVAR_TOTES_BOMBES:
                                adaptador.activaBomba(0);
                                adaptador.activaBomba(1);
                                adaptador.activaBomba(2);
                                adaptador.activaBomba(3);
                                System.out.println("Totes les bombes han estat activades");
                                break;

                            case DESACTIVAR_TOTES_BOMBES:
                                adaptador.desactivaBomba(0);
                                adaptador.desactivaBomba(1);
                                adaptador.desactivaBomba(2);
                                adaptador.desactivaBomba(3);
                                System.out.println("Totes les bombes han estat desactivades");
                                break;

                            case ACTIVAR_BOMBA:
                                try {
                                    System.out.println("Quina bomba vols activar? ");
                                    bombaActivar = sc.nextInt();
                                    adaptador.activaBomba(bombaActivar);
                                    System.out.println("Bomba " + bombaActivar + " activada");
                                } catch (CentralUBException e) {
                                    System.out.println("Error en les bombes de activar: " + e.getMessage());
                                }
                                break;

                            case DESACTIVAR_BOMBA:
                                try {
                                    System.out.println("Quina bomba vols desactivar? ");
                                    bombaDesactivar = sc.nextInt();
                                    adaptador.desactivaBomba(bombaDesactivar);
                                    System.out.println("Bomba " + bombaDesactivar + " desactivada");
                                } catch (CentralUBException e) {
                                    System.out.println("Error en les bombes de desactivar: " + e.getMessage());
                                }
                                break;

                            case MOSTRAR_ESTAT:
                                System.out.println(adaptador.getEstatSistemaRefrigeracio());
                                break;

                            case SORTIR:
                                break;
                        }
                    } while (subOpSR != OpcioSubMenuSR.SORTIR);
                    break;

                case MOSTRAR_ESTAT_CENTRAL:
                    System.out.println(adaptador.getEstatActual());
                    break;

                case MOSTRAR_BITACOLA:
                    System.out.println(adaptador.getBitacolaCompleta());
                    break;

                case MOSTRAR_INCIDENCIES:
                    System.out.println(adaptador.getIncidencies());
                    break;

                case OBTENIR_DEMANDA_SATISFETA:
                    System.out.println("La demanda satisfeta amb la configuració actual és de " +
                            100 * (adaptador.getPotenciaGenerada() / demandaPotencia) + "%");
                    break;

                case FINALITZAR_DIA:
                    finalitzaDia();
                    break;

                case GUARDAR_DADES:
                    try {
                        System.out.println("Camí destí: ");
                        camiDesti = sc.next();
                        adaptador.guardaDades(camiDesti);
                        System.out.println("Dades guardades!");
                    } catch (CentralUBException e) {
                        System.out.print("Error en guardar les dades: " + e.getMessage());
                    }
                    break;

                case CARREGAR_DADES:
                    try {
                        System.out.println("Camí d'origen: ");
                        camiOrigen = sc.next();
                        adaptador.carregaDades(camiOrigen);
                        System.out.println("Dades carregades!");
                    } catch (CentralUBException e) {
                        System.out.print("Error en carregar les dades: " + e.getMessage());
                    }
                    break;

                case SORTIR:
                    break;
            }
        } while (opcioM != OpcioMenuPrincipal.SORTIR);
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
