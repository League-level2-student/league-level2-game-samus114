import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	Sniper sniper;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int INFO = 3;
	int currentState = MENU;
	ObjectManager object;
	Timer frameDraw;
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font subFont = new Font("Arial", Font.PLAIN, 26);
	BufferedImage bg1;
	int laserX = 1600;
	int laserY = 40;

	GamePanel() {
		this.sniper = new Sniper(350, 40, 80, 40);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		object = new ObjectManager(sniper);
		try {
			bg1 = ImageIO.read(this.getClass().getResourceAsStream("background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void startGameState() {
		this.sniper = new Sniper(350, 40, 80, 40);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		object = new ObjectManager(sniper);
	}

	@Override
	public void paintComponent(Graphics g) {
		
		if (currentState == GAME) {
			g.drawImage(bg1, 0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT, null);
			g.setFont(subFont);
			object.draw(g);
			sniper.draw(g);
			g.setColor(Color.RED);
			Point laser = MouseInfo.getPointerInfo().getLocation();
			g.setColor(Color.ORANGE);
			g.drawImage(sniper.sniperSword, 1375, 10, 100, 100, null);
			g.drawString("" + sniper.swordLife + " blocks left", 1230, 50);
			g.setColor(Color.CYAN);
g.drawString("" + sniper.bulletsLeft + " bullets left", 1220, 120);
			g.drawImage(sniper.weapon, 1400, 80, 74, 37, null);
			if (!sniper.drawSword) {
				g.setColor(Color.RED);
				g.drawLine(430, 60, getMiddle(430, laser.x - 5), getMiddle(60, laser.y - 25));
			}
			object.update();
			if (object.isOver) {
				currentState = END;
			}
		} else if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == INFO) {
			drawInfoState(g);
		}else {
			drawEndState(g);
			frameDraw.stop();
		}

	}

	int getMiddle(int x1, int x2) {
		return (x1 + ((x2 - x1) / 2));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX() - 15;
		int y = e.getY() - 35;
		int diffX = x - 430;
		int diffY = y - 60;
		if (!sniper.drawSword) {
			if (sniper.bulletsLeft > 0) {
				object.addBullet(new Bullet(430, 60, Math.atan2(diffY, diffX), false, 40, 40, 300));
				sniper.bulletsLeft--;
			}
		}
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Gun Gail Online", 600, 100);
		g.setFont(subFont);
		g.drawString("Press ENTER to start", 640, 300);
		g.drawString("Press SPACE for instructions", 590, 400);
	}

	void drawEndState(Graphics g) {
		if (sniper.isActive) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT);
			g.setColor(Color.YELLOW);
			g.setFont(titleFont);
			g.drawString("GOOD JOB!!!", 615, 100);
			g.setFont(subFont);
			g.drawString("You have won", 675, 200);
			g.drawString("Everyone wants to hire you as a hitman", 580, 350);
			g.drawString("Press ENTER to restart", 625, 400);
		} else {
			g.setColor(Color.RED);
			g.fillRect(0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT);
			g.setColor(Color.YELLOW);
			g.setFont(titleFont);
			g.drawString("GAME OVER", 615, 100);
			g.setFont(subFont);
			g.drawString("You have DIED", 675, 200);
			if (object.score == 0) {
				g.drawString("Why do you attempt to be a hitman?", 550, 350);
			} else if (object.score == 1) {
				g.drawString("How'd you hit him?", 660, 350);
			} else if (object.score == 2) {
				g.drawString("Could improve", 660, 350);
			} else if (object.score == 3) {
				g.drawString("Where'd you learn how to be a hitman?", 540, 350);
			} else if (object.score == 4) {
				g.drawString("SO CLOSE!!!", 670, 350);
			}
			g.drawString("Press ENTER to restart", 625, 400);
		}
		
		repaint();
	}
void drawInfoState(Graphics g) {
	g.setColor(Color.BLUE);
	g.fillRect(0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT);
	g.setColor(Color.YELLOW);
	g.setFont(titleFont);
	g.drawString("MENU", 690, 100);
	g.setFont(subFont);
	g.drawString("The point of the game is to shoot all the enemys before they get you. You have 10 seconds till they fire", 205, 150);
	g.drawString("Press s key to deflect bullets, shoot with trackpad or mouse.", 440, 250);
	g.drawString("You can only block 5 bullets, you have 10 bullets left.", 470, 350);
	g.drawString("The bullets also have the ability to bounce off the walls", 460, 450);
	g.drawString("Hit space to return to menu screen", 580, 550);
}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			if (currentState == MENU) {
				startGameState();
				currentState = GAME;
			} else if (currentState == END) {
				repaint();
				currentState = MENU;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			sniper.drawSword = true;
	}
if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	if (currentState == MENU) {
		repaint();
		currentState = INFO;
	}else if (currentState == INFO) {
		repaint();
		currentState = MENU;
	}
}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_S) {
			sniper.drawSword = false;
		}
	}
}