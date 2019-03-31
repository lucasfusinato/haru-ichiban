package view.factory;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ImageIconFactory {

	private static ImageIconFactory instance;
	private List<String> createdImagesNames;
	private List<ImageIcon> createdIcons;
	
	public List<String> getCreatedImagesNames() {
		return createdImagesNames;
	}

	public static synchronized ImageIconFactory getInstance() {
		if(instance == null) {
			instance = new ImageIconFactory();
		}
		return instance;
	}
	
	private ImageIconFactory() {
		init();
	}
	
	private void init() {
		this.createdImagesNames = new ArrayList<>();
		this.createdIcons = new ArrayList<>();
	}

	public ImageIcon create(String imagePath) {
		int imageIndex = createdImagesNames.indexOf(imagePath);
		if(imageIndex >= 0) {
			return createdIcons.get(imageIndex);
		} else {
	    	ImageIcon imageIcon = createImageIcon(imagePath);
	        createdImagesNames.add(imagePath);
	        createdIcons.add(imageIcon);
	        return imageIcon;
		}
	}

	private ImageIcon createImageIcon(String imagePath) {
        return new ImageIcon(imagePath);
	}
	
	
}
