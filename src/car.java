import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class car extends gameObject {
	BufferedImage weapon;
	BufferedImage car;
	BufferedImage sniperSword;
	boolean drawSword = false;

	car(int x, int y, int width, int height) {
		super(x, y, width, height);
		try {
			weapon = ImageIO.read(this.getClass().getResourceAsStream("sniper.png"));
			car = ImageIO.read(this.getClass().getResourceAsStream("Untitled.png"));
			sniperSword = ImageIO.read(this.getClass().getResourceAsStream("sniperSword.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		collisionBox.setBounds(339, 200, 150, 150);
	}

	public void up() {
		y -= speed;
	}

	public void down() {
		y += speed;
	}

	public void left() {
		x -= speed;
	}

	public void right() {
		x += speed;
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		g.drawImage(car, 339, 200, 150, 150, null);
		if (drawSword) {
			g.drawImage(sniperSword, 339, 18, 80, 80, null);
		} else {
			g.drawImage(weapon, x, y, width, height, null);
		}
	}

	public void update() {
	}

	public void isHit() {
		Sniper snipe = new Sniper(1, 1, 1, 1);
		if (drawSword) {
			System.out.println("HIT " + snipe.swordLife + " hits left");
			snipe.swordLife--;
			if (snipe.swordLife == 0) {
				isActive = false;
			}
		} else {
			isActive = false;
		}
	}
}
