import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class ShotToSpriteCollision extends BasicCollisionGroup
{
	public void collided(Sprite s1, Sprite s2) 
	{
		((Pokemon) s2).attacked(((Bullet) s1).getDamage());
		
		if (((Pokemon) s2).isDead())
		{		
			Squirtle squirt = (Squirtle) (((Bullet) s1).getOwner());
			
			checkPowerUp(squirt, s2);
			
			s2.setActive(false);
			squirt.increaseScore(100);	
		}
		s1.setActive(false);
	}

	
	public void checkPowerUp(Squirtle squirt, Sprite s2)
	{
		Random r = new Random();
		
		int luck = r.nextInt(10);
		
		BufferedImage i;
		
		try {
			File f = new File("C:\\Users\\Robert\\workspace\\Project3Part1\\src\\resources\\FireRateUp.png");
			
			i = ImageIO.read(f);
			
			if (luck == 5)
			{
				Sprite up = new DoubleFireUp(i, squirt);
				up.setLocation(s2.getX(), s2.getY());
				up.setSpeed(-.15, 0);
				squirt.addPowerUp((PowerUp) up);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
