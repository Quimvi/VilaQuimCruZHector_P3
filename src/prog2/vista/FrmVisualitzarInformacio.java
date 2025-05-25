package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Classe FrmVisualitzarInformacio, un diàleg per visualitzar informació de la central.
 */
public class FrmVisualitzarInformacio extends JDialog{
    private JComboBox cmboxOpcionsVisualitzar;
    private JButton btnVisualitzar;
    private JPanel panell;
    private JTextArea textArea;
    private JScrollBar scrollBar;
    private JScrollPane scrollPane;

    /**
     * Constructor de la classe FrmVisualitzarInformacio.
     * **Configura el diàleg, el comportament del desplaçament del text i les accions del botó.**
     *
     * @param parent La finestra pare.
     * @param adaptador L'objecte Adaptador per accedir a les dades de la central.
     */
    public FrmVisualitzarInformacio(JFrame parent, Adaptador adaptador) {
        super(parent);
        setTitle("Visualitzar Informació Central");
        setContentPane(panell);
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setModal(true);

        // --- Gestió de la barra de desplaçament (scrollbar) ---
        // Vincula una JScrollBar personalitzada al JTextArea per controlar el seu desplaçament
        // i amaga la scrollbar per defecte del JScrollPane.
        scrollPane = (JScrollPane) textArea.getParent().getParent();
        scrollBar.setModel(scrollPane.getVerticalScrollBar().getModel());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                scrollPane.getVerticalScrollBar().setValue(e.getValue());
            }
        });

        /**
         * **ActionListener per a 'btnVisualitzar':**
         * **Mostra informació de la central segons l'opció seleccionada al ComboBox.**
         * Pot mostrar l'estat actual, el quadern de bitàcola complet o les incidències.
         */
        btnVisualitzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = cmboxOpcionsVisualitzar.getSelectedItem().toString();

                if (selectedOption.equals("Estat de la central")) {
                    JOptionPane.showMessageDialog(panell, adaptador.getEstatActual());
                } else if (selectedOption.equals("Quadern de bitàcola")){
                    textArea.setText(adaptador.getBitacolaCompleta());
                } else if (selectedOption.equals("Incidències")){
                    JOptionPane.showMessageDialog(panell, adaptador.getIncidencies());
                }
            }
        });
    }
}
