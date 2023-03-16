package magazin_animale.mg_animale;

import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;

import magazin_animale.database.*;

// vizualizare (toate componentele grafice)
public class MagazinView extends JFrame {
    JMenuBar menuBar = new JMenuBar();
    JMenu animale = new JMenu("Animale");
    JMenu vanzare = new JMenu("Vanzari");
    JTable tableAnimale = new JTable(){
        public boolean isCellEditable(int row, int column) {
            return false;
        };
    };
    JButton add = new JButton();
    JButton edit = new JButton();
    JButton delete = new JButton();
    MagazinModel model;

    public MagazinView(MagazinModel model) throws HeadlessException {
        this.model = model;

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        menuBar.add(animale);
        menuBar.add(vanzare);

        DBDataAnimale.loadDataAnimale(tableAnimale);
        JTableHeader tableHeader = tableAnimale.getTableHeader();
        tableHeader.setBackground(Color.BLACK);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Serif", Font.BOLD, 15));
        tableAnimale.removeColumn(tableAnimale.getColumnModel().getColumn(0));

        JPanel butoane = new JPanel();
        butoane.setLayout(new GridLayout(1, 3));

        add.setText("Adaugare");
        edit.setText("Editare");
        delete.setText("Stergere");

        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);

        edit.setBackground(Color.BLACK);
        edit.setForeground(Color.WHITE);

        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);

        butoane.add(add);
        butoane.add(edit);
        butoane.add(delete);

        JScrollPane scrollPane = new JScrollPane();
        panelPrincipal.add(tableAnimale, BorderLayout.CENTER);
        scrollPane.setViewportView(tableAnimale);

        panelPrincipal.add(butoane, BorderLayout.SOUTH);
        panelPrincipal.add(scrollPane);

        setJMenuBar(menuBar);
        pack();
        setSize(1000, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setLocationRelativeTo(null);
        setName("Animale");
    }

    void setAddAnimalListener(ActionListener a) {
        add.addActionListener(a);
    }

    void addUpdateAnimalListener(ActionListener a) {
        edit.addActionListener(a);
    }

    void addDeleteAnimalListener(ActionListener a) {
        delete.addActionListener(a);
    }

    void addVanzareMenuListener(MenuListener a){
        vanzare.addMenuListener(a);
    }
}
