package prog2.provaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtNom;
    private JComboBox tipus;

    public EditarDialog(JFrame parent, DialogDemo.Arxiu arxiu) {
        super(parent);
        setContentPane(contentPane);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arxiu.setNom(txtNom.getText());
                arxiu.setTipus((String) tipus.getSelectedItem());
                dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
