import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Bullet extends gameObject {
	BufferedImage bullet;
	BufferedImage bulletLeft;
	BufferedImage bulletRight;
	double tanAngleRadX = 0.0;
	double tanAngleRadY = 0.0;
	int bulletSpeed = 10;
	boolean hasSW = false;
	Random rand = new Random();
	int rotationDegrees = 0;
	Bullet(int x, int y, double tanAngleRad, boolean hasSW, int width, int height, int bulletSpeed) {
		super(x, y, width, height);
		try {
			if (hasSW) {
				bullet = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
			} else {
				bulletRight = ImageIO.read(this.getClass().getResourceAsStream("bulletBOI.png"));
				bulletLeft = ImageIO.read(this.getClass().getResourceAsStream("bulletBOIL.png"));
				bullet = bulletRight;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tanAngleRadX = tanAngleRad;
		this.tanAngleRadY = tanAngleRad;
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.drawImage(bullet, x, y, width, height, null);
		
	}

	void update() {
		if (x >= Gungailonline.WIDTH - 95) {
			int newX = rand.nextInt(300) - 125;
			int newY = rand.nextInt(300) - 125;
			changeTrajectory(newX, newY);
			bullet = bulletLeft;
		} else if (y >= Gungailonline.HEIGHT - 15) {
			int newX = rand.nextInt(250) - 125;
			int newY = rand.nextInt(250) - 125;
			changeTrajectory(newX, newY);
		} else if (y <= 0) {
			tanAngleRadY = - tanAngleRadY;
			rotationDegrees = 45;
			bullet = rotateImage(bullet, rotationDegrees);
		}
		x += Math.cos(tanAngleRadX) * bulletSpeed;
		y += Math.sin(tanAngleRadY) * bulletSpeed;
		super.update();
	}

	public void changeTrajectory(int x, int y) {
		tanAngleRadX = Math.atan2(y - this.y, x - this.x);
		tanAngleRadY = Math.atan2(this.y - y, x - this.x);
	}
	public BufferedImage rotateImage( BufferedImage image, int rotationDeg ) {
        double rotationRad = Math.toRadians(rotationDeg);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRad, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(image, null);
    }
}