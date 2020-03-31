import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener {
	Sniper sniper;
	Timer fireRate;
	int height;
	int height2;
	int height3;
	int height4;
	int height5;
	boolean isOver = false;
	ObjectManager(Sniper sniper) {
		Random rand = new Random();
		height = rand.nextInt(300) + 300;
		height2 = rand.nextInt(300) + 300;
		height3 = rand.nextInt(300) + 300;
		height4 = rand.nextInt(300) + 300;
		height5 = rand.nextInt(300) + 300;
		this.sniper = sniper;
		fireRate = new Timer(10000, this);
		fireRate.start();
		backg = new ArrayList<Buildings>();
		enemy = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		enemyProjectiles = new ArrayList<Bullet>();
		backg.add(new Buildings(500, 829 - height, 150, height, false));
		enemy.add(new Enemy(500, 829 - height - 75, 80, 80));
		backg.add(new Buildings(700, 829 - height2, 150, height2, false));
		enemy.add(new Enemy(700, 829 - height2 - 75, 80, 80));
		backg.add(new Buildings(900, 829 - height3, 150, height3, false));
		enemy.add(new Enemy(900, 829 - height3 - 75, 80, 80));
		backg.add(new Buildings(1100, 829 - height4, 150, height4, false));
		enemy.add(new Enemy(1100, 829 - height4 - 75, 80, 80));
		backg.add(new Buildings(1300, 829 - height5, 150, height5, false));
		enemy.add(new Enemy(1300, 829 - height5 - 75, 80, 80));
		backg.add(new Buildings(10, 100, 400, 700, true));
	}

	ArrayList<Buildings> backg;
	ArrayList<Enemy> enemy;
	ArrayList<Bullet> bullets;
	ArrayList<Bullet> enemyProjectiles;
	void draw(Graphics g) {
		for (int i = 0; i < backg.size(); i++) {
			backg.get(i).draw(g);
		}
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).draw(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
		for (int i = 0; i < enemyProjectiles.size(); i++) {
			enemyProjectiles.get(i).draw(g);
		}
	}
	void update() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
		for (int i = 0; i < enemyProjectiles.size(); i++) {
			enemyProjectiles.get(i).update();
		}
		checkCollision();
		purgeObjects();
		if (!sniper.isActive || enemy.isEmpty()) {
			isOver = true;
		}
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

		void purgeObjects() {
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).isActive == false) {
				  enemy.remove(i);
			}
		}
		for (int j = 0; j < bullets.size(); j++) {
			if (bullets.get(j).isActive == false) {
				bullets.remove(j);
			}
		}
		for (int j = 0; j < backg.size(); j++) {
		}
	}
	void checkCollision() {
		for (int i = 0; i < enemyProjectiles.size(); i++) {
			if (enemyProjectiles.get(i).collisionBox.intersects(sniper.collisionBox)) {
				sniper.isActive = false;
				enemyProjectiles.get(i).isActive = false;
			}
		}
		for (int j = 0; j < bullets.size(); j++) {
			for (int i = 0; i < enemy.size(); i++) {
				if (bullets.get(j).collisionBox.intersects(enemy.get(i).collisionBox)) {
					enemy.get(i).isActive = false;
						bullets.get(j).isActive = false;
					}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < enemy.size(); i++) {
		}
		enemyProjectiles.add(new Bullet(500, 829 - height - 75, 400));
		enemyProjectiles.add(new Bullet(700, 829 - height2 - 75, 425));
		enemyProjectiles.add(new Bullet(900, 829 - height3 - 75, 500));
		enemyProjectiles.add(new Bullet(1100, 829 - height4 - 75, 550));
		enemyProjectiles.add(new Bullet(1300, 829 - height5 - 75, 600));
		
	}
}