package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.BombaRefrigerant;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FrmGestioComponentsCentral extends JDialog {
    private JPanel panell;
    private JTextField txtIntroduirInsercioBarresControl;
    private JSlider sldBarresControl;
    private JButton btnIntroduirInsercioBarresControl;
    private JButton btnActivatDesactivatButton;
    private JButton btnCancelarModificacions;
    private JButton btnAplicarModificacions;
    private JTextArea txtArea;
    private JCheckBox bomba1CheckBox;
    private JCheckBox bomba3CheckBox;
    private JCheckBox bomba2CheckBox;
    private JCheckBox bomba4CheckBox;
    private JList listBombes;
    private JButton btnMostrarBombes;
    private ArrayList<BombaRefrigerant> llistaBomba;

    public FrmGestioComponentsCentral(JFrame parent, Adaptador adaptador, SistemaRefrigeracio sistemaRefrigeracio) {
        super(parent);
        setTitle("Gestió Components Central");
        setContentPane(panell);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        llistaBomba = sistemaRefrigeracio.getLlistaBomba();
        listBombes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        txtIntroduirInsercioBarresControl.setText(String.valueOf(adaptador.getInsercioBarres()));
        btnActivatDesactivatButton.setText("Activar");
        if(adaptador.getEstatReactor().getActivat()){
            btnActivatDesactivatButton.setText("Desactivar");
        }else{
            btnActivatDesactivatButton.setText("Activar");
        }
        if (llistaBomba.get(0).getActivat()){
            bomba1CheckBox.setSelected(true);
        }else{
            bomba1CheckBox.setSelected(false);
        }
        if (llistaBomba.get(1).getActivat()){
            bomba2CheckBox.setSelected(true);
        }else{
            bomba2CheckBox.setSelected(false);
        }
        if (llistaBomba.get(2).getActivat()){
            bomba3CheckBox.setSelected(true);
        }else{
            bomba3CheckBox.setSelected(false);
        }
        if (llistaBomba.get(3).getActivat()){
            bomba4CheckBox.setSelected(true);
        }else{
            bomba4CheckBox.setSelected(false);
        }

        btnMostrarBombes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List seleccionats = listBombes.getSelectedValuesList();
                if (seleccionats.contains("Bomba 1"))
                    txtArea.append("Bomba 1: " + (llistaBomba.get(0).getForaDeServei() ? "fora de servei" : "en servei") + " i " + (llistaBomba.get(0).getActivat() ? "activada\n" : "desactivada\n"));
                if (seleccionats.contains("Bomba 2"))
                    txtArea.append("Bomba 2: " + (llistaBomba.get(1).getForaDeServei() ? "fora de servei" : "en servei") + " i " + (llistaBomba.get(1).getActivat() ? "activada\n" : "desactivada\n"));
                if (seleccionats.contains("Bomba 3"))
                    txtArea.append("Bomba 3: " + (llistaBomba.get(2).getForaDeServei() ? "fora de servei" : "en servei") + " i " + (llistaBomba.get(2).getActivat() ? "activada\n" : "desactivada\n"));
                if (seleccionats.contains("Bomba 4"))
                    txtArea.append("Bomba 4: " + (llistaBomba.get(3).getForaDeServei() ? "fora de servei" : "en servei") + " i " + (llistaBomba.get(3).getActivat() ? "activada\n" : "desactivada\n"));
            }
        });

        sldBarresControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txtIntroduirInsercioBarresControl.setText(sldBarresControl.getValue()+"");
            }
        });

        btnIntroduirInsercioBarresControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sldBarresControl.setValue(Integer.parseInt(txtIntroduirInsercioBarresControl.getText()));   // El valor s'ha modificar o deixar igual però s'ha de tocar perquè sinó salta error
            }
        });


        btnActivatDesactivatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (btnActivatDesactivatButton.getText().equals("Activar")) {
                    if(adaptador.getEstatReactor().getTemperaturaReactor() > 1000){
                        JOptionPane.showMessageDialog(panell, "El reactor no es pot activar. Ha superat la temperatura màxima");
                    }else{
                        btnActivatDesactivatButton.setText("Desactivar");
                    }

                }
                else if (btnActivatDesactivatButton.getText().equals("Desactivar")) {

                    btnActivatDesactivatButton.setText("Activar");
                }
            }
        });
        btnAplicarModificacions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaptador.setInsercioBarres(Float.parseFloat(txtIntroduirInsercioBarresControl.getText()));

                if (btnActivatDesactivatButton.getText().equals("Activar")) {
                    adaptador.desactivaReactor();
                }
                else if (btnActivatDesactivatButton.getText().equals("Desactivar")) {
                    adaptador.activaReactor();
                }

                if(bomba1CheckBox.isSelected()) {
                    if (llistaBomba.get(0).getForaDeServei()) {
                        JOptionPane.showMessageDialog(panell, "La bomba 1 està fora de servei");
                    } else {
                        llistaBomba.get(0).activa();
                    }
                }else{
                    llistaBomba.get(0).desactiva();
                }
                if(bomba2CheckBox.isSelected()) {
                    if (llistaBomba.get(1).getForaDeServei()) {
                        JOptionPane.showMessageDialog(panell, "La bomba 2 està fora de servei");
                    } else {
                        llistaBomba.get(1).activa();
                    }
                }else{
                    llistaBomba.get(1).desactiva();
                }

                if(bomba3CheckBox.isSelected()) {
                    if (llistaBomba.get(2).getForaDeServei()) {
                        JOptionPane.showMessageDialog(panell, "La bomba 3 està fora de servei");
                    } else {
                        llistaBomba.get(2).activa();
                    }
                }else{
                    llistaBomba.get(2).desactiva();
                }
                if(bomba4CheckBox.isSelected()) {
                    if (llistaBomba.get(3).getForaDeServei()) {
                        JOptionPane.showMessageDialog(panell, "La bomba 4 està fora de servei");
                    } else {
                        llistaBomba.get(3).activa();
                    }
                }else{
                    llistaBomba.get(3).desactiva();
                }
                dispose();
            }
        });
        btnCancelarModificacions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
