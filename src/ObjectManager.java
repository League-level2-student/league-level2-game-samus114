import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Sniper sniper;
	ObjectManager(Sniper sniper) {
		Random rand = new Random();
		int height = rand.nextInt(300)+300;
		int height2 = rand.nextInt(300)+300;
		int height3 = rand.nextInt(300)+300;
		int height4 = rand.nextInt(300)+300;
		int height5 = rand.nextInt(300)+300;
		this.sniper = sniper;
		backg = new ArrayList <Buildings> ();
		backg.add(new Buildings(700, 829 - height, 150, height, false));
		backg.add(new Buildings(500, 829 - height2, 150, height2, false));
		backg.add(new Buildings(900, 829 - height3, 150, height3, false));
		backg.add(new Buildings(1100, 829 - height4, 150, height4, false));
		backg.add(new Buildings(1300, 829 - height5, 150, height5, false));
		backg.add(new Buildings(10, 100, 400, 700, true));
	}
	ArrayList<Buildings> backg;
	void draw(Graphics g) {
		for (int i = 0; i < backg.size(); i++) {
			backg.get(i).draw(g);
		}
	}
}