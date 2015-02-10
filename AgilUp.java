import java.awt.Color;
import java.awt.Graphics;


public class AgilUp extends Item{

	public AgilUp(int x) {
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
		if (ball.getAgility() < 8){ //could be get max speed
			ball.setAgility(ball.getAgility() + 1);
		}
		

	}
}