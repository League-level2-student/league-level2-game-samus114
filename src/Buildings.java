import java.awt.Color;
import java.awt.Graphics;

public class Buildings extends gameObject {
	boolean hasroof;
	Buildings(int x, int y, int width, int height, boolean hasroof) {
		super(x, y, width, height);
		this.hasroof = hasroof;
		
	}
	void draw(Graphics g){
		g.drawRect(x, y, width, height);
		g.setColor(new Color (0, 0, 0));
		g.fillRect(x, y, width, height);
		if(hasroof) {
			int[] xs = {x, x+width, x+width-10};
			int[] ys = {y, y, y-20};
		g.fillPolygon(xs, ys, 3);
		}
	}
}