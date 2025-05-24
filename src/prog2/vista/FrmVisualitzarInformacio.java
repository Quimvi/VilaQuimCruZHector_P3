package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class FrmVisualitzarInformacio extends JDialog{
    private JComboBox cmboxOpcionsVisualitzar;
    private JButton btnVisualitzar;
    private JPanel panell;
    private JTextArea textArea;
    private JScrollBar scrollBar;
    private JScrollPane scrollPane;

    public FrmVisualitzarInformacio(JFrame parent, Adaptador adaptador) {
        super(parent);
        setTitle("Visualitzar Informació Central");
        setContentPane(panell);
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setModal(true);

        // --- VINCULAR LA SCROLLBAR AMB EL JScrollPane ---
        // 1. Obtenir el JScrollPane del JTextArea (assumint que el .form el conté)
        scrollPane = (JScrollPane) textArea.getParent().getParent(); // Si el JTextArea està dins d'un JScrollPane

        // 2. Vincular el model de la scrollbar personalitzada amb el del JScrollPane
        scrollBar.setModel(scrollPane.getVerticalScrollBar().getModel());

        // 3. Opcional: Amagar la scrollbar per defecte del JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // 4. Afegir listener per sincronitzar el desplaçament
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                // Moure el viewport del JScrollPane quan es mou la scrollbar
                scrollPane.getVerticalScrollBar().setValue(e.getValue());
            }
        });

        btnVisualitzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmboxOpcionsVisualitzar.getSelectedItem().toString().equals("Estat de la central")) {
                    JOptionPane.showMessageDialog(panell, adaptador.getEstatActual());
                }
                else if (cmboxOpcionsVisualitzar.getSelectedItem().toString().equals("Quadern de bitàcola")){
                    textArea.setText(adaptador.getBitacolaCompleta());
                }
                else if (cmboxOpcionsVisualitzar.getSelectedItem().toString().equals("Incidències")){
                    JOptionPane.showMessageDialog(panell, adaptador.getIncidencies());
                }
            }
        });
    }
}
