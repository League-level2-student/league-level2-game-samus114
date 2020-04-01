import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sniper extends gameObject {
	BufferedImage weapon;
	BufferedImage guy;
	Sniper(int x, int y, int width, int height) {
		super(x, y, width, height);
		try {
			weapon = ImageIO.read(this.getClass().getResourceAsStream("sniper.png"));
			guy = ImageIO.read(this.getClass().getResourceAsStream("sniperMan.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		collisionBox.setBounds(339, 25, 50, 50);
	}

	public void draw(Graphics g) {
		super.draw(g);
	g.drawImage(guy, 339, 4, 150, 150, null);
		g.drawImage(weapon, x, y, width, height, null);
	}
	public void update() {
	}
}