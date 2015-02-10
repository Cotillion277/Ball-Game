import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Item {
	
	
	private int x, y, dx, radius;
	private BallGame sp;
	private boolean createNew = false;
	
	public Item(int x) {
		// TODO Auto-generated constructor stub
		this.x = x;
		Random r= new Random();
		y = r.nextInt(400) + radius;
		radius = 10;
		dx = -2;
	}
	 public int getX() {
		return x;
	}
	 
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	
	public void update(BallGame sp, Ball b){
		x += dx;
		this.sp = sp;
		checkForCollision(b);
		
		if (x < 0 - radius){ //platform goes off left hand side of screen and is returned to right hand side
			createNew = true;
		}
	}
	
	private void checkForCollision(Ball ball) {
		int ballX = ball.getX();
		int ballY = ball.getY();
		int ballR = ball.getRadius();
		
		int a = x - ballX;
		int b = y - ballY;
		int collide = radius + ballR;
		double c = Math.sqrt((double) a*a + (double) b*b);
		
		if (c < collide){
			performAction(ball);
			createNew = true;
			
		}
	}

	public void performAction(Ball ball) {
		// TODO Auto-generated method stub
		
	}

	public void paint(Graphics g){
		//g.setColor(Color.green);
		g.fillOval(x-radius, y-radius, radius * 2, radius * 2);
	}
	public boolean isCreateNew() {
		return createNew;
	}
	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}

}
