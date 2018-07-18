import ingame.controller.MainController;
import ingame.model.MainModel;
import ingame.view.MainView;
import mainmenu.controller.MainMenuController;
import mainmenu.model.MainMenuModel;
import mainmenu.view.MainMenuView;

public class Main {

	public static void main(String[] args) {
		MainMenuModel model = new MainMenuModel();
		MainMenuView view = new MainMenuView(model);
		MainMenuController controller = new MainMenuController(model, view);
	}
}
