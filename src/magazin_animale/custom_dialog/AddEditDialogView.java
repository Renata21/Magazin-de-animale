package magazin_animale.custom_dialog;

import javax.swing.*;
import java.awt.*;

public class AddEditDialogView {
    static JTextField specie = new JTextField(10);
    static JTextField rasa = new JTextField(10);
    static JSpinner pret = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 1));
    static JSpinner numar = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));


    public static String[] showAddEditInputDialog(Component parentComponent, String option, String[] initial_data) {
        JPanel principalPanel = new JPanel(new GridLayout(4, 1, 20, 0));

        JPanel speciePanel = new JPanel(new FlowLayout());
        speciePanel.add(new Label("Specie: "));
        speciePanel.add(specie);

        JPanel rasaPanel = new JPanel((new FlowLayout()));
        rasaPanel.add(new Label("Rasa: "));
        rasaPanel.add(rasa);

        JPanel pretPanel = new JPanel((new FlowLayout()));
        pretPanel.add(new Label("Pret: "));
        pretPanel.add(pret);

        JPanel numarPanel = new JPanel((new FlowLayout()));
        numarPanel.add(new Label("Numar: "));
        numarPanel.add(numar);

        if (initial_data.length == 4) {
            specie.setText(initial_data[0]);
            rasa.setText(initial_data[1]);
            pret.setValue(Integer.valueOf(initial_data[2]));
            numar.setValue(Integer.valueOf(initial_data[3]));
        }

        principalPanel.add(speciePanel);
        principalPanel.add(rasaPanel);
        principalPanel.add(pretPanel);
        principalPanel.add(numarPanel);

        String[] input = new String[5];

        int result = JOptionPane.showConfirmDialog(parentComponent, principalPanel, option, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            input[0] = specie.getText();
            input[1] = rasa.getText();
            input[2] = pret.getValue().toString();
            input[3] = specie.getText() + "_" + rasa.getText();
            input[4] = numar.getValue().toString();
        } else input = null;

        return input;
    }
}
