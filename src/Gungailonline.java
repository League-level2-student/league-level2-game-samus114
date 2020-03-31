import javax.swing.JFrame;

public class Gungailonline{
	JFrame frame;
	GamePanel panel = new GamePanel();
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 800;
	public static void main(String[] args) {
	Gungailonline Ggo = new Gungailonline();
	Ggo.setup();
}
	public Gungailonline (){
		frame = new JFrame();
	}
	void setup() {
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(panel);
		frame.addMouseMotionListener(panel);
		frame.addKeyListener(panel);
		//b.pack();
	}
}