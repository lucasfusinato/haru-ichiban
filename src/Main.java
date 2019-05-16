
import core.view.MainFrame;

public class Main implements Runnable {

	public static void main(String[] args) {
		Main main = new Main();
		Thread thread = new Thread(main);
		thread.start();
	}

	@Override
	public void run() {
		MainFrame.getInstance().setVisible(true);
	}
	
}
