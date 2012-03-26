import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class FireBall extends Bullet
{
private static BufferedImage i;
	
	static{	
	try {
		File f = new File("C:\\Users\\Robert\\workspace\\Project3Part1\\src\\resources\\FireBall.png");
		
		i = ImageIO.read(f);

	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	public FireBall(BufferedImage i, Pokemon s) 
	{
		super(i, s);
	}
	
	public FireBall(Pokemon s)
	{
		this(i, s);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
