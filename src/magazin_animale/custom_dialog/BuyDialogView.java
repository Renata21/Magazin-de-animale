package magazin_animale.custom_dialog;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static magazin_animale.database.DBDataAnimale.getAnimals;

public class BuyDialogView {
    static JComboBox<String> animale = new JComboBox<>();
    static JTextField numeClient = new JTextField(10);
    static JTextField telClient = new JTextField(10);

    public static String[] showBuyInputDialog(Component parentComponent, String option) {
        JPanel principalPanel = new JPanel(new GridLayout(3, 1, 20, 0));

        JPanel animalePanel = new JPanel(new FlowLayout());
        animalePanel.add(new JLabel("Animal: "));

        ResultSet rs = getAnimals();
        try {
            while (rs.next()) {
                animale.addItem(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        animalePanel.add(animale);
        JPanel numePanel = new JPanel(new FlowLayout());
        numePanel.add(new JLabel("Nume client: "));
        numePanel.add(numeClient);

        JPanel telPanel = new JPanel((new FlowLayout()));
        telPanel.add(new JLabel("Telefon client: "));
        telPanel.add(telClient);

        principalPanel.add(animalePanel);
        principalPanel.add(numePanel);
        principalPanel.add(telPanel);

        String[] input = new String[4];

        int result = JOptionPane.showConfirmDialog(parentComponent, principalPanel, option, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (numeClient.getText().length() == 0) {
                JOptionPane.showMessageDialog(parentComponent, "Numele nu poate fi null", "Eroare de validare", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            if (!validateTelClient(telClient.getText())) {
                JOptionPane.showMessageDialog(parentComponent, "Nu ati introdus un numar valid.", "Eroare de validare", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            input[0] = String.valueOf(animale.getSelectedItem());
            input[1] = String.valueOf(java.time.LocalDate.now());
            input[2] = numeClient.getText();
            input[3] = telClient.getText();
        }
        else return null;

        return input;
    }

    public static boolean validateTelClient(String tel) {
        Pattern pattern = Pattern.compile("[+]\\d{11}|\\d{9}");
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    }
}
