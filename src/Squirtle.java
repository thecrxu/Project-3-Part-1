import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.SpriteGroup;


public class Squirtle extends Pokemon
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Squirtle(BufferedImage i, SpriteGroup bullets)
	{
		super(i, bullets);
		setHealth(200);
		setFireRate(500);
	}

}
