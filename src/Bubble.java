import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bubble extends Bullet
{
	private static BufferedImage i;
	
	static{	
	try {
		File f = new File("C:\\Users\\Robert\\workspace\\Project3Part1\\src\\resources\\Bubble.png");
		
		i = ImageIO.read(f);

	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	public Bubble(BufferedImage i, Pokemon s) 
	{
		super(i, s);
	}
	
	public Bubble(Pokemon s)
	{
		this(i, s);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
