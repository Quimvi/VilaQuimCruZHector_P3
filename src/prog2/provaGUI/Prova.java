package prog2.provaGUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Prova extends JFrame{
    private JPanel panell;
    private JTextField txtMissatge;
    private JButton btnHola;
    private JButton btnAdeu;
    private JCheckBox chkBotonsActius;
    private JButton btnMostrar;
    private JList lstLlista1;
    private JButton btnEliminar;
    ArrayList<A> llista;

    public Prova(){
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
        chkBotonsActius.setSelected(true);
        txtMissatge.setEnabled(true);
        llista = null;
        afegirDadesTest();
        btnEliminar.setEnabled(false);
        btnHola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMissatge.setText(btnHola.getText());
            }
        });
        btnAdeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMissatge.setText(btnAdeu.getText());
            }
        });
        chkBotonsActius.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                btnHola.setEnabled(chkBotonsActius.isSelected());
                btnAdeu.setEnabled(chkBotonsActius.isSelected());
            }
        });
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                omplirLlista();
            }
        });
        lstLlista1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    btnEliminar.setEnabled(!lstLlista1.isSelectionEmpty());
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Object item : lstLlista1.getSelectedValuesList()) {
                    A elementSeleccionat = (A) item;
                    llista.remove(elementSeleccionat);
                }
                omplirLlista();
            }
        });
    }

    private void afegirDadesTest(){
        llista = new ArrayList<A>();
        llista.add(new A(1,2));
        llista.add(new A(3,4));
        llista.add(new A(5,6));
    }

    void omplirLlista(){
        DefaultListModel model = new DefaultListModel();
        model.clear();
        for(A item: llista){
            model.addElement(item);
        }
        lstLlista1.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Prova prova = new Prova();
            prova.setVisible(true);
        });
    }

    class A {
        int x;
        int y;
        public A(int x, int y){
            this.x=x;
            this.y=y;
        }
        @Override
        public String toString(){
            return "x= " + x +"\ny= " + y;
        }
    }
}
