import java.awt.Image;
import java.net.URL;


public class Pictures {
	
	static Image platform, ball;
	static StartingPoint sp;
	URL url;
	static int level = 1;
	
	public Pictures(StartingPoint sp) {
		// TODO Auto-generated constructor stub
		try {
			url = sp.getDocumentBase();
		} catch (Exception e){
			
		}
		
		platform = sp.getImage(url, "images/platform.png");
		this.sp = sp;
	}
	
	

}
