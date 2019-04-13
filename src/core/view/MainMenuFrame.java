package core.view;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import core.controller.MainControllerInterface;
import core.controller.MainMenuOption;
import core.view.listeners.MainMenuActionListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;

@SuppressWarnings("serial")
public class MainMenuFrame extends JInternalFrame {

    private MainControllerInterface mainController;
    private JPanel panelMain;
    private HashMap<MainMenuOption, JButton> menuOptions;

    public MainMenuFrame(MainControllerInterface mainController) {
        this.init(mainController);
    }

    private void init(MainControllerInterface mainController) {
        this.defineProperties(mainController);
        this.initComponents();
        this.addComponents();
        this.doLayout();
    }

    private void defineProperties(MainControllerInterface mainController) {
        this.mainController = mainController;
        this.menuOptions = new HashMap<>();
    }

    private void initComponents() {
        this.panelMain = new JPanel();
        for (MainMenuOption mainMenuOption : MainMenuOption.values()) {
            this.menuOptions.put(mainMenuOption, this.createButton(mainMenuOption));
        }
    }

    private void addComponents() {
        this.setContentPane(new MainImageBackgroundPanel(new ImageIcon("images/boardgame-haru-ichiban.jpg")));
        Box box = Box.createVerticalBox();
        GridBagConstraints gbc = this.getGridBagConstraints();
        for (MainMenuOption mainMenuOption : MainMenuOption.values()) {
            box.add(this.menuOptions.get(mainMenuOption));
        }
        this.setLayout(new GridBagLayout());
        this.getContentPane().add(box,gbc);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    private JButton createButton(MainMenuOption mainMenuOption) {
        JButton button = new JButton(mainMenuOption.getOption());
        button.addActionListener(new MainMenuActionListener(this.mainController, mainMenuOption));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 60);
        button.setFont(font);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                button.setFont(font.deriveFont(attributes));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setFont(font);
            }
        });
        
        return button;
    }
    
    private GridBagConstraints getGridBagConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        return gbc;
    }
}
