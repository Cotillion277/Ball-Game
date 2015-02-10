import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;


public class BallGame extends Applet implements Runnable, KeyListener {
	

	private Image i;
	private Graphics doubleG; //for double buffering
	Ball b;
	Platform p[] = new Platform[7];
	Item item[] = new Item[3];
	private int score;
	double desertX = 0;
	double desertDx = 1; 
	URL url;
	Image desert;
	int levelCheck = 0;
	boolean gameOver = false;
	private boolean run = true;
	int flyingCounter = 0;
	
	@Override
	public void init() { //called only once, first time someone sees the applet. then start() is called
		setSize(800, 600);
		addKeyListener(this);
		try{
			url = getDocumentBase(); //go find the root folder where applet lives
		} catch (Exception e) {
			
		}
		
		desert = getImage(url, "images/desert.png");
		Pictures p = new Pictures(this);
	}
	
	@Override
	public void start() {
		resetGame();
	}
	
	public void resetGame() {
		b = new Ball(0, 0);
		
		score = 0;
		
		for (int i = 0; i<p.length; i++){
			Random r = new Random();
			p[i] = new Platform (i*120 , 300); //this makes the first 7 platforms in a line
		}
		
		for (int i = 0; i<item.length; i++){
			Random r = new Random();
			switch (r.nextInt(6)){
			case 0: 
				item[i] = new GravUp(getWidth() + 2000 * i);
				break;
			case 1: 
				item[i] = new GravDown(getWidth() + 2000 * i);
				break;	
			case 2: 
				item[i] = new AgilUp(getWidth() + 2000 * i);
				break;
			case 3: 
				item[i] = new AgilDown(getWidth() + 2000 * i);
				break;		
			
			case 4:
				item[i] = new ScorePlus(getWidth() + 2000 * i, this);
				break;	
			
			case 5:
				item[i] = new FlyItem(getWidth() + 2000 * i, this);
				break;
			}
			//^^ polymorhpism. stuff that extend item class
		}
		Thread thread = new Thread(this); //thread needs a run method so this tells it to use the run method from Runnable
		thread.start();
	}
	
	@Override
	public void run() {
		//thread info'
		while (run){
			
			if (b.isFlyMode()){
				flyingCounter++;			
			}
			
			if (flyingCounter == 500){
				b.setFlyMode(false);
				flyingCounter = 0;
			}
			
			gameOver = b.getGameOver();
			
			if (levelCheck > 1000){
				Pictures.level++;
				levelCheck = 0;
			}
			levelCheck++;
			
			if(desertX > getWidth() * -1){
				desertX -= desertDx;
			} else {
				desertX = 0;
			}
			
			
			Random r = new Random();
			
			if(!gameOver){
				score++;
			}
			
			
			for (int i = 0; i < item.length; i++){
				if (item[i].isCreateNew()){
					item[i] = null;
					switch (r.nextInt(6)){
					case 0: 
						item[i] = new GravUp(getWidth() + 2000 * i);
						break;
					case 1: 
						item[i] = new GravDown(getWidth() + 2000 * i);
						break;	
					case 2: 
						item[i] = new AgilUp(getWidth() + 2000 * i);
						break;
					case 3: 
						item[i] = new AgilDown(getWidth() + 2000 * i);
						break;		
					
					case 4:
						item[i] = new ScorePlus(getWidth() + 2000 * i, this);
						break;	
					
					case 5:
						item[i] = new FlyItem(getWidth() + 2000 * i, this);
						break;
					}
					item[i].setCreateNew(false);
				}
				
			}
		
			b.update(this);	
			for (int i = 0; i < p.length; i++){
				p[i].update(this, b);
			}
			for (int i = 0; i < item.length; i++){
				item[i].update(this, b);
			}
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void stop() {
	}
	
	@Override
	public void destroy() {
	}
	
	
	@Override
	public void update(Graphics g) {
	//double buffering! overriding the update method which is called by repaint in the run method
		if (i == null){
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height); //creating another graphic of same size and backg colour, producing a copy
		doubleG.setColor(getForeground()); 
		paint(doubleG);
		g.drawImage(i, 0, 0, this);
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		g.setColor(new Color(15, 77, 147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(desert, (int)desertX, 0, this);
		g.drawImage(desert, (int)desertX + getWidth(), 0, this);
		
		for (int i = 0; i < p.length; i++){
			p[i].paint(g);
		}
		for (int i = 0; i < item.length; i++){
			item[i].paint(g);
		}
		b.paint(g);
		
		String lvlUP = "LEVEL UP!";
		
		if(score % 1000 == 0){
			g.setColor(Color.red);
			Font levelUPfont = new Font("Impact", Font.BOLD, 32);
			g.setFont(levelUPfont);
			g.drawString(lvlUP, 400, 300);
			
		}
		
		String s = Integer.toString(score);
		Font font = new Font("Calibri", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(s, getWidth() - 150+2, 50+2);
		g.setColor(new Color(198,226,255));
		g.drawString(s, getWidth() - 150, 50);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;
		case KeyEvent.VK_F5:
			Item debugFly = new FlyItem(getWidth(), this); 
			System.out.println("test");
			break;	
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	

}
