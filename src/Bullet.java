import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends gameObject {
	BufferedImage bullet;
	int x2 = -1;
	int y2 = -1;
	int bulletSpeed = 10;
	Bullet(int x, int y, int x2, int y2) {
		super(x, y, 20, 20);
		try {
			bullet = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x2 = x2;
		this.y2 = y2;
	}

	public void draw(Graphics g) {
		g.drawImage(bullet, x, y, width, height, null);
	}

	void update() {
		int diffX = x2 - x;
		int diffY = y2 - y;

		if (Math.abs(diffX) > bulletSpeed || Math.abs(diffY) > 1) {
			double angleRad = Math.atan2(diffY, diffX);

			x += Math.cos(angleRad) * bulletSpeed;
			y += Math.sin(angleRad) * bulletSpeed;
			System.out.println(x + " " + Math.cos(angleRad));
		}
	}
}