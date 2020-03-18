import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	Sniper sniper;
	ObjectManager(Sniper sniper) {
		this.sniper = sniper;
		backg = new ArrayList <Background> ();
		backg.add(new Background(100, 100, 100, 100));
	}
	ArrayList<Background> backg;
	void draw(Graphics g) {
		for (int i = 0; i < backg.size(); i++) {
			backg.get(i).draw(g);
		}
	}
}