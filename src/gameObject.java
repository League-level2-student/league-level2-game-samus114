import java.awt.Rectangle;

public class gameObject {
	int x;
	int y;
	int width;
	int height;
	int speed = 0;
	boolean isActive = true;
	Rectangle collisionBox;

	gameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = 10;
		collisionBox = new Rectangle(x, y, width, height);
	}

	void update() {
		if (x >= Gungailonline.WIDTH || y >= Gungailonline.HEIGHT) {
			isActive = false;
		} else {
			collisionBox.setBounds(x, y, width, height);
		}
		}
}