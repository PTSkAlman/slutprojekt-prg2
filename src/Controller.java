import Core.CoinFlip;
import UI.View;

public class Controller {
    public Controller() {
        View view = new View("Game");
        view.init();

        CoinFlip cf = new CoinFlip();
        cf.flip(1);
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
