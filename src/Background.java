import java.awt.Graphics;

public class Background extends gameObject {
	Background(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void draw(Graphics g){
		g.drawRect(x, y, width, height);
	}
}