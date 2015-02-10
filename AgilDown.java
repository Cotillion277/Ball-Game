import java.awt.Color;
import java.awt.Graphics;


public class AgilDown extends Item{

	public AgilDown(int x) {
		super(x); //tells the Item class what the x is
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		super.paint(g);
	}
	
	@Override
	public void performAction(Ball ball) {
		if (ball.getAgility() >= 2){
			ball.setAgility(ball.getAgility() - 1);
		}
		

	}
}
