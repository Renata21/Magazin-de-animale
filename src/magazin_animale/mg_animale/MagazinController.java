package magazin_animale.mg_animale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import magazin_animale.custom_dialog.*;
import magazin_animale.vanzare.VanzareView;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import static magazin_animale.database.DBDataAnimale.*;

// pentru listeners
public class MagazinController {
    private MagazinModel model;
    private static MagazinView view;
    private static VanzareView vanzareView;

    public MagazinController(MagazinModel model, MagazinView view, VanzareView vanzareView) {
        this.model = model;
        this.view = view;
        this.vanzareView = vanzareView;

        view.setAddAnimalListener(new AddListener());
        view.addUpdateAnimalListener(new UpdateListener());
        view.addDeleteAnimalListener(new DeleteListener());
        view.addVanzareMenuListener(new VanzareMenuListener());

    }

    static class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = AddEditDialogView.showAddEditInputDialog(view, "Adauga animal", new String[]{});
            if (input != null) {
                addAnimal(input[0], input[1], Double.valueOf(input[2]), input[3], Integer.valueOf(input[4]));
                loadDataAnimale(view.tableAnimale);
            }
        }
    }

    static class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.tableAnimale.getSelectedRow();

            if (selectedRow != -1) {
                Integer id = Integer.valueOf((String) view.tableAnimale.getModel().getValueAt(view.tableAnimale.getSelectedRow(), 0));
                String[] data = {
                        (String) view.tableAnimale.getModel().getValueAt(view.tableAnimale.getSelectedRow(), 2),
                        (String) view.tableAnimale.getModel().getValueAt(view.tableAnimale.getSelectedRow(), 3),
                        (String) view.tableAnimale.getModel().getValueAt(view.tableAnimale.getSelectedRow(), 4),
                        (String) view.tableAnimale.getModel().getValueAt(view.tableAnimale.getSelectedRow(), 5)
                };

                String[] input = AddEditDialogView.showAddEditInputDialog(view, "Modifica animal", data);

                if (input != null) {
                    updateAnimal(input[0], input[1], Double.valueOf(input[2]), input[3], Integer.valueOf(input[4]), id);
                    loadDataAnimale(view.tableAnimale);
                }
            }
        }
    }

    static class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.tableAnimale.getSelectedRow();

            if (selectedRow != -1) {
                Integer id = Integer.valueOf((String) view.tableAnimale.getModel().getValueAt(view.tableAnimale.getSelectedRow(), 0));

                int input = JOptionPane.showConfirmDialog(view, "Sigur doresti sa stergi acest animal?", "Confirmare stergere",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                if (input == 0) {
                    deleteAnimal(id);
                    loadDataAnimale(view.tableAnimale);
                }
            }
        }
    }

    public static class AnimaleMenuListener implements MenuListener{
        @Override
        public void menuSelected(MenuEvent e) {
            view.setVisible(!view.isVisible());
            loadDataAnimale(view.tableAnimale);
            vanzareView.setVisible(!vanzareView.isVisible());
        }

        @Override
        public void menuDeselected(MenuEvent e) {

        }

        @Override
        public void menuCanceled(MenuEvent e) {

        }
    }

    public static class VanzareMenuListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            view.setVisible(!view.isVisible());
            vanzareView.setVisible(!vanzareView.isVisible());
        }

        @Override
        public void menuDeselected(MenuEvent e) {

        }

        @Override
        public void menuCanceled(MenuEvent e) {

        }
    }
}
