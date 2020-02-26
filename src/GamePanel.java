import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
Sniper sniper = new Sniper();
final int MENU = 0;
final int GAME = 1;
final int END = 2;
int currentState = MENU;
ObjectManager object;
Font titleFont = new Font("Arial", Font.PLAIN, 48);
Font subFont = new Font("Arial", Font.PLAIN, 26);
}
GamePanel(){
	
}