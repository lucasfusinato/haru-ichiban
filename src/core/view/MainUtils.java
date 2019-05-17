package game.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import utils.view.ErrorPane;

public class ViewUtils {

	public static String getImagePath() {
		return "images";
	}
	
	public static String createImagePath(String image) {
		return createImagePath(image, "png");
	}

	public static String createImagePath(String image, String extensao) {
		return getImagePath()+"/"+image+"."+extensao;
	}

	public static void loadSources() {
		createFlowers();
		createComponents();
		createCache();
	}
	
	private static void createFlowers() {
//		String[] flowers = { "darkened-red-flower", "darkened-yellow-flower" };
//		String flower;
//		String number;
//		for(int i = 0; i < flowers.length; i++) {
//			flower = flowers[i];
//			for(int j = 1; j <= 36; j++) {
//				number = "number-" + i;
//				if(!exists(number)) {
//					createNumber(number, j);
//				}
//				if(!exists(flower, number)) {
//					combine(flower, number);
//				}
//			}
//		}
	}

	private static boolean exists(String number) {
		return new File(createImagePath(number)).exists();
	}

	private static void createNumber(String number, int j) {
		String a = j + "";
	}

	private static void createComponents() {
		/*
		for(String source : getSources()) {
			for(String target : getTargets()) {
				if(!exists(source, target)) {
					combine(source, target);
				} else {
					System.out.println("Componentes já criados: " + source + " | " + target);
				}
			}
		}
		*/
	}

	private static void createCache() {
		/*
		File path = new File(getImagePath());
		File[] files = path.listFiles();
		for(int i = 0; i < files.length; i++) {
			ResizedImageIconFactory.create(files[i].getPath(), 90, 90);
		}
		*/
	}

	private static List<String> getSources() {
		List<String> sources = new ArrayList<>();
		sources.add("darkened-nenufar");
		sources.add("lighted-nenufar");
		return sources;
	}
	
	private static List<String> getTargets() {
		List<String> targets = new ArrayList<>();
		targets.add("yellow-flower");
		targets.add("red-flower");
		targets.add("yellow-frog");
		targets.add("red-frog");
		return targets;
	}
	
	private static boolean exists(String source, String target) {
		return new File(createImagePath(source + "-" + target)).exists();
	}
	
	private static void combine(String source, String target) {
		BufferedImage image;
		BufferedImage overlay;
		try {
			image = ImageIO.read(new File(createImagePath(source)));
			overlay = ImageIO.read(new File(createImagePath(target)));

			// create the new image, canvas size is the max. of both image sizes
			int w = Math.max(image.getWidth(), overlay.getWidth());
			int h = Math.max(image.getHeight(), overlay.getHeight());
			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(overlay, 0, 0, null);

			// Save as new image
			ImageIO.write(combined, "PNG", new File(createImagePath(source + "-" + target)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void catchException(Exception e) {
		ErrorPane.show(e.getMessage());
	}

}
