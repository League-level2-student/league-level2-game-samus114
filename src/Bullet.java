import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends gameObject{
	BufferedImage bullet;
Bullet(int x, int y, int width, int height) {
	super(x, y, width, height);
	try {
		bullet = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}