package magazin_animale.mg_animale;

import magazin_animale.vanzare.VanzareController;
import magazin_animale.vanzare.VanzareView;

public class MagazinMain {
    public static void main(String[] args) {
        MagazinModel model = new MagazinModel();
        MagazinView view = new MagazinView(model);
        VanzareView vanzareView = new VanzareView();
        VanzareController vanzareController = new VanzareController(vanzareView);
        MagazinController controller = new MagazinController(model, view, vanzareView);
        view.setVisible(true);
    }
}
