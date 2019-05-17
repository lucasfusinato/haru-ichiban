package core.view;

import utils.view.ErrorPane;

public class MainUtils {

	public static String getImagePath() {
		return "images";
	}
	
	public static String createImagePath(String image) {
		return createImagePath(image, "png");
	}

	public static String createImagePath(String image, String extensao) {
		return getImagePath()+"/"+image+"."+extensao;
	}

	public static void catchException(Exception e) {
		ErrorPane.show(e.getMessage());
	}

}
