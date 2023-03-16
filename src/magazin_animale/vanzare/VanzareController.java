package magazin_animale.vanzare;

import magazin_animale.custom_dialog.AddEditDialogView;
import magazin_animale.custom_dialog.BuyDialogView;
import magazin_animale.mg_animale.MagazinController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static magazin_animale.database.DBDataAnimale.*;
import static magazin_animale.database.DBDataVanzare.addVanzare;
import static magazin_animale.database.DBDataVanzare.loadDataVanzare;

public class VanzareController {
    private static VanzareView vanzareView;

    public VanzareController(VanzareView vanzareView) {
        this.vanzareView = vanzareView;
        vanzareView.addAnimaleMenuListener(new MagazinController.AnimaleMenuListener());
        vanzareView.addBuyAnimalListener(new BuyListener());
    }

    static class BuyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String[] input = BuyDialogView.showBuyInputDialog(vanzareView, "Modifica animal");

            if (input != null) {
                if (isAnimal(input[0])) {
                    addVanzare(input[0], input[1], input[2], input[3]);
                    loadDataVanzare(vanzareView.tableVanzare);

                    reduceAnimalsNumber(getIDByName(input[0]));
                }
                else {
                    JOptionPane.showMessageDialog(vanzareView, "Acest animal nu este disponibil");
                }
            }
        }
    }
}
