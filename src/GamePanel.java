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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	Sniper sniper;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
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
		currentState = GAME;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState != END) {
			g.drawImage(bg1, 0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT, null);
			object.draw(g);
			sniper.draw(g);
			g.setColor(Color.RED);
			Point laser = MouseInfo.getPointerInfo().getLocation();
			g.drawLine(430, 60, getMiddle(430, laser.x - 5), getMiddle(60, laser.y - 25));
			object.update();
			if (object.isOver) {
				currentState = END;
			}
		} else {
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
		object.addBullet(new Bullet(430, 60, Math.atan2(diffY, diffX)));
	}

	void drawEndState(Graphics g) {
		if (sniper.isActive) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT);
			g.setColor(Color.YELLOW);
			g.setFont(titleFont);
			g.drawString("GOOD JOB!!!", 100, 100);
			g.setFont(subFont);
			g.drawString("You have won", 110, 200);
			g.drawString("Press ENTER to restart", 90, 400);
		} else {
			g.setColor(Color.RED);
			g.fillRect(0, 0, Gungailonline.WIDTH, Gungailonline.HEIGHT);
			g.setColor(Color.YELLOW);
			g.setFont(titleFont);
			g.drawString("GAME OVER", 100, 100);
			g.setFont(subFont);
			g.drawString("You have DIED", 110, 200);
			g.drawString("Press ENTER to restart", 90, 400);
		}
		repaint();
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
			if (currentState == END) {
				startGameState();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}