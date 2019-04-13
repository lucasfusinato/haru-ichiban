package core.view;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class MainImageBackgroundPanel extends JPanel {

    private ImageIcon bgImage;

    public MainImageBackgroundPanel(ImageIcon bgImage) {
        this.bgImage = bgImage;
    }

    public void paintComponent(Graphics g) {
        Dimension d = getSize();
        g.drawImage(bgImage.getImage(), 0, 0, (int) d.getWidth(), (int) d.getHeight(), this);
    }
}
