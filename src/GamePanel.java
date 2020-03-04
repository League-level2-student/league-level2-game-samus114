import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	Sniper sniper = new Sniper();
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	ObjectManager object;
	Timer frameDraw;
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font subFont = new Font("Arial", Font.PLAIN, 26);
	BufferedImage bg1;

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		object = new ObjectManager(sniper);
		try {
			bg1 = ImageIO.read(this.getClass().getResourceAsStream("background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g) {
	g.drawImage(bg1, 0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT, null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	repaint();
	}
}