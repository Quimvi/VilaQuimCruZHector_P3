package prog2.provaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooser extends JFrame {
    private JButton btnSelecciona;
    private JPanel panell;
    private JTextField txtNomFitxer;

    public FileChooser() {
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
        btnSelecciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fitxer;
                //Creació del selector de fitxer
                JFileChooser seleccio = new JFileChooser();
                //Mostrem la finestra de dialeg
                //Resultat emmagazema una constant que indica si s’ha
                //seleccionat o no un fitxer
                int resultat = seleccio.showOpenDialog(FileChooser.this);
                //Assegurem que hi hagi un fitxer seleccionat
                if (resultat == JFileChooser.APPROVE_OPTION) {
                    //Obtenim el fitxer
                    fitxer=seleccio.getSelectedFile();
                    //Posem la ruta del fitxer al quadre de text
                    txtNomFitxer.setText(fitxer.toString());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileChooser prova = new FileChooser();
            prova.setVisible(true);
        });
    }
}
