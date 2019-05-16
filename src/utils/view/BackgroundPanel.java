package utils.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel {
	
	private Image image;
	
	public BackgroundPanel(String imagePath) {
		image = createImage(imagePath);
	}
	
	public BackgroundPanel(String imagePath, int imageWidth, int imageHeight) {
		image = createImage(imagePath, imageWidth, imageHeight);
	}

	private Image createImage(String imagePath) {
		return ImageIconFactory.create(imagePath).getImage();
	}

	private Image createImage(String imagePath, int imageWidth, int imageHeight) {
		return ResizedImageIconFactory.create(imagePath, imageWidth, imageHeight).getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(getImage(), getX(), getY(), null);
	}
	
	protected Image getImage() {
		return image;
	}
	
}
