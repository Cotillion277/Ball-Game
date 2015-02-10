import java.awt.Color;
import java.awt.Graphics;


public class GravDown extends Item{

	public GravDown(int x) {
		super(x); //tells the Item class what the x is
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		super.paint(g);
	}
	
	@Override
	public void performAction(Ball ball) {
		if (ball.getGravity() > 3)
			ball.setGravity(ball.getGravity() - 3);
			if (ball.getGravity() < 3){
				ball.setGravity(3);
		}
	}
}
