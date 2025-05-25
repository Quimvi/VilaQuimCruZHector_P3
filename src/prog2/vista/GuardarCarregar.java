package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Classe GuardarCarregar, un diàleg per a les operacions de guardar i carregar dades de la central.
 * Estén JDialog, indicant que és una finestra secundària modal.
 */
public class GuardarCarregar extends JDialog {
    private JPanel panell;
    private JButton btnCarregar;
    private JButton btnGuardar;

    /**
     * Constructor de la classe GuardarCarregar.
     * **Configura el diàleg i els listeners per als botons de guardar i carregar.**
     * Permet a l'usuari seleccionar un fitxer per desar l'estat actual de la simulació
     * o carregar un estat previ.
     *
     * @param parent La finestra pare (JFrame) que va obrir aquest diàleg.
     * @param adaptador L'objecte Adaptador, que gestiona la lògica de guardar/carregar les dades.
     */
    public GuardarCarregar(JFrame parent, Adaptador adaptador) {
        super(parent);
        setTitle("Guardar i carregar");
        setContentPane(panell);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        btnCarregar.setText("Carregar");
        btnGuardar.setText("Guardar");

        /**
         * **ActionListener per a 'btnGuardar':**
         * Quan es prem, **obre un `JFileChooser`** que permet a l'usuari triar una ubicació
         * i nom de fitxer per **desar l'estat actual de la simulació**.
         * L'adaptador és l'encarregat de realitzar l'operació de guardat.
         */
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser seleccionadorArxiu = new JFileChooser();
                File fitxer;
                int resultat = seleccionadorArxiu.showSaveDialog(null);
                // Assegurem que l'usuari hagi seleccionat un fitxer (no ha cancel·lat)
                if (resultat == JFileChooser.APPROVE_OPTION) {
                    // Obtenim el fitxer seleccionat
                    fitxer = seleccionadorArxiu.getSelectedFile();
                    // Invoquem el mètode de l'adaptador per guardar les dades a la ruta del fitxer
                    adaptador.guardaDades(fitxer.toString());
                }
            }
        });

        /**
         * **ActionListener per a 'btnCarregar':**
         * Quan es prem, **obre un `JFileChooser`** que permet a l'usuari seleccionar
         * un fitxer existent per **carregar un estat previ de la simulació**.
         * L'adaptador és l'encarregat de realitzar l'operació de càrrega.
         */
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser seleccionadorArxiu = new JFileChooser();
                File fitxer;
                int resultat = seleccionadorArxiu.showOpenDialog(null);
                // Assegurem que l'usuari hagi seleccionat un fitxer (no ha cancel·lat)
                if (resultat == JFileChooser.APPROVE_OPTION) {
                    // Obtenim el fitxer seleccionat
                    fitxer = seleccionadorArxiu.getSelectedFile();
                    // Invoquem el mètode de l'adaptador per carregar les dades des de la ruta del fitxer
                    adaptador.carregaDades(fitxer.toString());
                }
            }
        });
    }
}