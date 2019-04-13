
import core.view.MainView;

public class Main implements Runnable {

	public static void main(String[] args) {
		Thread thread = new Thread(new Main());
		thread.start();
	}

	@Override
	public void run() {
		MainView view = new MainView();
		view.setVisible(true);
	}
	
}
