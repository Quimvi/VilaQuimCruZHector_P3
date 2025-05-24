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

public class AppCentralUB extends JFrame {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    private VariableNormal variableNormal;
    private float demanda;

    private JPanel panell;
    private JButton btnGestioComponentsCentral;
    private JButton btnVisualitzarIC;
    private JButton btnFiDia;
    private JButton btnGCDades;
    protected JTextField demandaPotencia;
    private JTextField nDia;
    private JTextField guanysAcumulats;
    private final Adaptador adaptador = new Adaptador();

    public AppCentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demanda = generaDemandaPotencia();
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
        nDia.setText("Dia: " + adaptador.getDia());
        demandaPotencia.setText("Demanda de potència: " + demanda);
        guanysAcumulats.setText("Guanys acumulats: " + adaptador.getGuanysAcumulats());
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral frmGCC = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador, adaptador.getSistemaRefrigeracio());
                frmGCC.setVisible(true);
            }
        });
        btnVisualitzarIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVisualitzarInformacio frmVIC = new FrmVisualitzarInformacio(AppCentralUB.this, adaptador);
                frmVIC.setVisible(true);
            }
        });
        btnFiDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalitzaDia();
            }
        });
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

        JOptionPane.showMessageDialog(AppCentralUB.this,adaptador.finalitzaDia(demanda).toString() );

        demanda = generaDemandaPotencia();
        nDia.setText("Dia: " + adaptador.getDia());
        demandaPotencia.setText("Demanda de potència: " + demanda);
        guanysAcumulats.setText("Guanys acumulats: " + adaptador.getGuanysAcumulats());
        // Generar i mostrar nova demanda de potencia

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB appCUB = new AppCentralUB();
            appCUB.setVisible(true);

        });
    }
}
