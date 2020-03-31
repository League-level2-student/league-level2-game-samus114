import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Enemy extends gameObject {
	BufferedImage enemy;

	
	Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		try {
			enemy = ImageIO.read(this.getClass().getResourceAsStream("enemy.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.drawImage(enemy, x, y, width, height, null);
	}
	public void update() {
		super.update();
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}