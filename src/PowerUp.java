import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;


public class PowerUp extends Sprite
{
	Pokemon owner;
	
	public PowerUp(BufferedImage i, Pokemon owner)
	{
		super(i);
		this.owner = owner;
	}
}
