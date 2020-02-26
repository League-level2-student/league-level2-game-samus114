import javax.swing.JFrame;

public class Gungailonline {
	JFrame frame;
	GamePanel panel = new GamePanel();
	public static final int WIDTH = 800;
	public static final int HEIGHT = 1600;
	public static void main(String[] args) {
	Gungailonline Ggo = new Gungailonline();
	Ggo.setup();
}
	public Gungailonline (){
		frame = new JFrame();
	}
	void setup() {
		frame.add(panel);
	}
}