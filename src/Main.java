import controller.MainController;
import model.MainModel;
import view.MainView;

public class Main {

	public static void main(String[] args) {
		MainModel mainModel = new MainModel();
		MainView mainView = new MainView(mainModel);
		new MainController(mainModel, mainView);
	}
}
