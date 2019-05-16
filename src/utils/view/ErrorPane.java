package utils.view;

import javax.swing.JOptionPane;

public class ErrorPane {

	public static void show(String message) {
		JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
	}

}
