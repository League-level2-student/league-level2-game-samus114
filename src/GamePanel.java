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
	car car = new car(70, 200, 100, 100);
	Sniper sniper;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int INFO = 3;
	final int GAME2 = 4;
	int currentState = MENU;
	ObjectManager object;
	Timer frameDraw;
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font subFont = new Font("Arial", Font.PLAIN, 26);
	BufferedImage bg1;
	BufferedImage backg2;
	int laserX = 1600;
	int laserY = 40;

	GamePanel() {
		this.sniper = new Sniper(350, 40, 80, 40);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		object = new ObjectManager(sniper);
		try {
			bg1 = ImageIO.read(this.getClass().getResourceAsStream("background.png"));
			backg2 = ImageIO.read(this.getClass().getResourceAsStream("backg2.png"));
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
			if (object.isEnemyOver) {
				currentState = GAME2;
			} else if (!object.sniper.isActive) {
				currentState = END;
			}
		} else if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == INFO) {
			drawInfoState(g);
		} else if (currentState == GAME2) {
			draw2GameState(g);
		} else if (currentState == END) {
			drawEndState(g);
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
		 if (!car.isActive) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT);
			g.setColor(Color.YELLOW);
			g.setFont(titleFont);
			g.drawString("GAME OVER", 615, 100);
			g.setFont(subFont);
			g.drawString("HAHAHA! YOU HAVE FALLEN FOR HASHANI'S DOOM TRAP!", 400, 200);
			g.drawString("Press ENTER to restart", 625, 400);
		} else if (sniper.isActive) {
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
			g.drawString("You have DIED, I THE IMMORTAL HISHANI HAVE KILLED YOU WITH MY HENCHMEN!", 275, 200);

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
		g.drawString(
				"The point of the game is to shoot all the enemys before they get you. You have 10 seconds till they fire",
				205, 150);
		g.drawString("Press Q key to deflect bullets, shoot with trackpad or mouse.", 440, 250);
		g.drawString("You can only block 5 bullets, you have 10 bullets left.", 470, 350);
		g.drawString("The bullets also have the ability to bounce off the walls", 460, 450);
		g.drawString("Hit space to return to menu screen", 580, 550);
	}

	void draw2GameState(Graphics g) {
		g.drawImage(backg2, 0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT, null);
		g.setColor(Color.RED);
		g.setFont(titleFont);
		car.draw(g);
		g.drawString("YOU HAVE KILLED MY HENCHMEN!", 350, 50);
		g.drawString("NOW YOU MUST PERISH BY MY WEAPON COME FACE ME.", 100, 100);
		int backg2c = backg2.getRGB(car.x, car.y);
		System.out.println(car.x + " , " + car.y);
		if (backg2c != -1) {
			currentState = END;
			car.isActive = false;
		}
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
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			sniper.drawSword = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			if (currentState == MENU) {
				repaint();
				currentState = INFO;
			} else if (currentState == INFO) {
				repaint();
				currentState = MENU;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_F) {
			currentState = GAME2;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && currentState == GAME2) {
			car.up();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && currentState == GAME2) {
			car.down();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && currentState == GAME2) {
			car.left();
			System.out.println("left");
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentState == GAME2) {
			car.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			if (!sniper.drawSword) {
				if (sniper.bulletsLeft > 0) {
					object.addBullet(new Bullet(car.x + 100, car.y + 10, 0, true, 20, 20, 10));
					sniper.bulletsLeft--;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			sniper.drawSword = false;
		}
	}
}