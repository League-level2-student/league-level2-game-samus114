import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends gameObject {
	BufferedImage bullet;
	double tanAngleRad = 0.0;
	int bulletSpeed = 10;
	Bullet(int x, int y, double tanAngleRad) {
		super(x, y, 20, 20);
		try {
			bullet = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tanAngleRad = tanAngleRad;
	}
	public void draw(Graphics g) {
		super.draw(g);
		g.drawImage(bullet, x, y, width, height, null);
	}
	void update() {
        x += Math.cos(tanAngleRad) * bulletSpeed;
        y += Math.sin(tanAngleRad) * bulletSpeed;
		super.update();
	}
}
