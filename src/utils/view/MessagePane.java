package utils.view;

import javax.swing.JOptionPane;

public class MessagePane {

	public static void show(String string) {
		JOptionPane.showMessageDialog(null, string, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

}
