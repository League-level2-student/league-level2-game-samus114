import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class car extends gameObject {
	BufferedImage weapon;
	BufferedImage carR;
	BufferedImage carD;
	BufferedImage carL;
	BufferedImage carU;
	BufferedImage sniperSword;
	boolean drawSword = false;
	boolean left = false;
	boolean right = true;
	boolean up = false;
	boolean down = false;

	car(int x, int y, int width, int height) {
		super(x, y, width, height);
		try {
			weapon = ImageIO.read(this.getClass().getResourceAsStream("sniper.png"));
			carR = ImageIO.read(this.getClass().getResourceAsStream("carR.png"));
			carD = ImageIO.read(this.getClass().getResourceAsStream("carD.png"));
			carL = ImageIO.read(this.getClass().getResourceAsStream("carL.png"));
			carU = ImageIO.read(this.getClass().getResourceAsStream("carU.png"));
			sniperSword = ImageIO.read(this.getClass().getResourceAsStream("sniperSword.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		collisionBox.setBounds(339, 200, 150, 150);
	}

	public void up() {
		y -= 10;
		up = true;
		down = false;
		left = false;
		right = false;
	}

	public void down() {
		y += 10;
		down = true;
		left = false;
		right = false;
		up = false;
	}

	public void left() {
		x -= 10;
		left = true;
		right = false;
		up = false;
		down = false;
		System.out.println("left");
	}

	public void right() {
		x += 10;
		right = true;
		up = false;
		down = false;
		left = false;
	}

	public void draw(Graphics g) {
		super.draw(g);
		if (right) {
			g.drawImage(carR, x - (width/2), y - (height/2), width - 20, height - 20, null);
		}
		if (left) {
			g.drawImage(carL, x - (width/2), y - (height/2), width - 20, height - 20, null);
		}
		if (down) {
			g.drawImage(carD, x - (width/2), y - (height/2), width - 20, height - 20, null);
		}
		if (up) {
			g.drawImage(carU, x - (width/2), y - (height/2), width - 20, height - 20, null);
			
		}
		if (drawSword) {
			g.drawImage(sniperSword, 39, 1, 20, 20, null);
		}
	}

	public void update() {
		super.update();
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

	public void shoot() {

	}
}
