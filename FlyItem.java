import java.awt.Color;
import java.awt.Graphics;


public class FlyItem extends Item{
	
	public FlyItem(int x, BallGame ballGame) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
		super.paint(g);
	}
	
	@Override
	public void performAction(Ball ball) {
		
		ball.enableFlyMode();

	}
}
