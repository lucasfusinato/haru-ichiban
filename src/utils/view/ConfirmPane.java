package utils.view;

import javax.swing.JOptionPane;

public class ConfirmPane {
	
	private static String[] buttons = { "Sim", "N�o" };
	private static final int CONFIRM_VALUE = 0;
	
	public static boolean confirm(String message) {
		return JOptionPane.showOptionDialog(null, message, "Confirma��o", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, -1) == CONFIRM_VALUE;
	}
	
}
