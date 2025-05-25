package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.VariableNormal;
import prog2.provaGUI.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * Classe principal de l'aplicació per a la Central UB, que estén JFrame per crear la finestra principal.
 */
public class AppCentralUB extends JFrame {

    // --- Constants de la simulació ---
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;

    // --- Atributs de la lògica i dades ---
    private VariableNormal variableNormal;
    private float demanda;

    // --- Components de la interfície gràfica (Swing) ---
    private JPanel panell;
    private JButton btnGestioComponentsCentral;
    private JButton btnVisualitzarIC;
    private JButton btnFiDia;
    private JButton btnGCDades;
    protected JTextField demandaPotencia;
    private JTextField nDia;
    private JTextField guanysAcumulats;
    private final Adaptador adaptador = new Adaptador();

    /**
     * Constructor de la classe AppCentralUB.
     * **Inicialitza la interfície d'usuari i la lògica de l'aplicació.**
     * Configura la finestra, genera la demanda inicial i assigna els *ActionListeners*
     * als botons per gestionar les interaccions de l'usuari.
     */
    public AppCentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demanda = generaDemandaPotencia();

        // Configuració bàsica de la finestra
        panell.setBackground(Color.GREEN);
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);

        // Mostra la informació inicial a la UI
        nDia.setText("Dia: " + adaptador.getDia());
        demandaPotencia.setText("Demanda de potència: " + demanda);
        guanysAcumulats.setText("Guanys acumulats: " + adaptador.getGuanysAcumulats());

        /**
         * **ActionListener per a 'btnGestioComponentsCentral':**
         * Obre la finestra de gestió de components (`FrmGestioComponentsCentral`).
         */
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral frmGCC = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador, adaptador.getSistemaRefrigeracio());
                frmGCC.setVisible(true);
            }
        });

        /**
         * **ActionListener per a 'btnVisualitzarIC':**
         * Obre la finestra de visualització d'informació (`FrmVisualitzarInformacio`).
         */
        btnVisualitzarIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVisualitzarInformacio frmVIC = new FrmVisualitzarInformacio(AppCentralUB.this, adaptador);
                frmVIC.setVisible(true);
            }
        });

        /**
         * **ActionListener per a 'btnFiDia':**
         * Crida al **mètode `finalitzaDia()`** per processar la fi del dia de simulació.
         */
        btnFiDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalitzaDia();
            }
        });

        /**
         * **ActionListener per a 'btnGCDades':**
         * Prepara i obre la finestra de guardar/carregar dades (`GuardarCarregar`),
         * i després actualitza la UI amb la informació recent de l'adaptador.
         */
        btnGCDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaptador.setDemenda(demanda);
                GuardarCarregar grCr = new GuardarCarregar(AppCentralUB.this, adaptador);
                grCr.setVisible(true);
                nDia.setText("Dia: " + adaptador.getDia());
                demandaPotencia.setText("Demanda de potència: " + adaptador.getDemenda());
                guanysAcumulats.setText("Guanys acumulats: " + adaptador.getGuanysAcumulats());
            }
        });
    }

    /**
     * **Mètode privat `generaDemandaPotencia()`**
     * **Calcula un valor de demanda de potència** basat en una distribució normal,
     * assegurant que el valor estigui dins dels límits definits (`DEMANDA_MIN`, `DEMANDA_MAX`).
     * @return El valor de la demanda de potència per al dia.
     */
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else if (valor < DEMANDA_MIN)
            return DEMANDA_MIN;
        else
            return valor;
    }

    /**
     * **Mètode privat `finalitzaDia()`**
     * **Processa el final del dia de la simulació.**
     * Utilitza l'adaptador per actualitzar l'estat intern (guanys, etc.),
     * mostra un missatge amb la informació del dia,
     * i prepara la UI per al dia següent generant una nova demanda i actualitzant els camps.
     */
    private void finalitzaDia() {
        JOptionPane.showMessageDialog(AppCentralUB.this,adaptador.finalitzaDia(demanda).toString() );

        demanda = generaDemandaPotencia(); // Genera nova demanda
        nDia.setText("Dia: " + adaptador.getDia());
        demandaPotencia.setText("Demanda de potència: " + demanda);
        guanysAcumulats.setText("Guanys acumulats: " + adaptador.getGuanysAcumulats());
    }

    /**
     * **Mètode `main()`**
     * **Punt d'entrada de l'aplicació.**
     * Garanteix que la UI es creï i es mostri correctament dins del fil d'esdeveniments de Swing (EDT).
     * @param args Arguments de la línia de comandes.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB appCUB = new AppCentralUB();
            appCUB.setVisible(true);
        });
    }
}
