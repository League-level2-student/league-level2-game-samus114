import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Bullet extends gameObject {
	BufferedImage bullet;
	double tanAngleRad = 0.0;
	int bulletSpeed = 10;
	Random rand = new Random();
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
		if (x >= Gungailonline.WIDTH-95) {
			bulletSpeed = -10;
		} else if (y >= Gungailonline.HEIGHT) {
			int newX = rand.nextInt(250)-125;
			int newY = rand.nextInt(250)-125;
			changeDirectory(newX, newY);
		}
		x += Math.cos(tanAngleRad) * bulletSpeed;
        y += Math.sin(tanAngleRad) * bulletSpeed;
		super.update();
	}
public void changeDirectory(int x, int y) {
	tanAngleRad = Math.atan2(y - this.y, x - this.x);
}
}
