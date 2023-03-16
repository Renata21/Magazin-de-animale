package magazin_animale.vanzare;

import magazin_animale.database.DBDataAnimale;
import magazin_animale.database.DBDataVanzare;

import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;

public class VanzareView extends JFrame {
    JMenuBar menuBar = new JMenuBar();
    JMenu animale = new JMenu("Animale");
    JMenu vanzare = new JMenu("Vanzari");
    JTable tableVanzare = new JTable();
    JButton cumpara = new JButton();
    public VanzareView(){
        JPanel panelPrincipal = new JPanel();

        panelPrincipal.setLayout(new BorderLayout());

        menuBar.add(animale);
        menuBar.add(vanzare);

        DBDataVanzare.loadDataVanzare(tableVanzare);
        JTableHeader tableHeader = tableVanzare.getTableHeader();
        tableHeader.setBackground(Color.BLACK);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Serif", Font.BOLD, 15));
        tableVanzare.removeColumn(tableVanzare.getColumnModel().getColumn(0));
        tableVanzare.setEnabled(false);

        JPanel butoane = new JPanel();
        butoane.setLayout(new GridLayout(1, 3));

        cumpara.setText("Cumparare");

        cumpara.setBackground(Color.BLACK);
        cumpara.setForeground(Color.WHITE);

        butoane.add(cumpara);

        JScrollPane scrollPane = new JScrollPane();
        panelPrincipal.add(tableVanzare, BorderLayout.CENTER);
        scrollPane.setViewportView(tableVanzare);

        panelPrincipal.add(butoane, BorderLayout.SOUTH);
        panelPrincipal.add(scrollPane);

        setJMenuBar(menuBar);
        pack();
        setSize(1000, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setLocationRelativeTo(null);
        setName("Vanzare");
    }
    void addBuyAnimalListener(ActionListener a) {
        cumpara.addActionListener(a);
    }
    void addAnimaleMenuListener(MenuListener a){
        animale.addMenuListener(a);
    }
}
