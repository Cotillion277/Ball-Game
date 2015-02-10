import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;


public class Platform {
	
	int dx;
	int x, y, width, height;
	Image plat;
	URL url;
	float frame = 0;
	
	/*
	public Platform() {
		// TODO Auto-generated constructor stub
		dx = -1;
		x = 300;
		y = 300;
		width = 120;
		height = 40;
	}*/
	
	public Platform(int x, int y){
		this.x = x;
		this.y = y;
		width = 120;
		height = 40;
		dx = -1;
		plat = Pictures.platform;
	}
	
	public void update(BallGame sp, Ball b){
		
		int platformSpriteChanger = (int)(frame + .1);
		if (platformSpriteChanger < 3){
			frame += .1;
		} else {
			frame = 0;
		}
		x += - (Pictures.level); //as level increases the speed of platforms goes up
		checkForCollision(b);
		
		if (x < 0 - width){ //this makes new platforms be in random spots so you dont get the first 7 in the line repeating
			Random r = new Random();
			y = sp.getHeight() - 40 - r.nextInt(400);
			x = sp.getWidth() + r.nextInt(300);
		}
	}
	
	private void checkForCollision(Ball b) {
		int ballX = b.getX();
		int ballY = b.getY();
		int radius = b.getRadius();
		
		if (ballY + radius > y && ballY + radius < y + height){
			if (ballX > x && ballX < x + width){ //changed second part of if from ballX + radius to current
				double newDY = b.getGameDy();
				b.setY(y-radius);
				b.setDy(newDY);
			}
		}
	}

	public void paint(Graphics g){
	
		g.drawImage(plat, x, y, x + width, y + height, 0, 40 *(int)frame, 120, 40 * (int)frame + 40, Pictures.sp);
	}

}
