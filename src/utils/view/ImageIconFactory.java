package utils.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ImageIconFactory {

	private static List<String> createdImagesNames = new ArrayList<>();
	private static List<ImageIcon> createdIcons = new ArrayList<>();

	public static ImageIcon create(String imagePath) {
		int imageIndex = createdImagesNames.indexOf(imagePath);
		if(imageIndex >= 0) {
			return createdIcons.get(imageIndex);
		} else {
	    	ImageIcon imageIcon = new ImageIcon(imagePath);
	        createdImagesNames.add(imagePath);
	        createdIcons.add(imageIcon);
	        return imageIcon;
		}
	}
	
}
