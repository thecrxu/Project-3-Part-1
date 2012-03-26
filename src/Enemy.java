import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.SpriteGroup;


public class Enemy extends Pokemon
{
	private Random r;
	private BufferedImage bullets;
	
	
	public Enemy(BufferedImage i, BufferedImage s, SpriteGroup bullets) 
	{
		super(i, bullets);
		setHealth(100);
		setFireRate(800);
		this.bullets = s;
		r = new Random();
	}
	
	public void fire(long elapsedTime)
	{
		long currentTime = new SystemTimer().getTime();
		
		if (this.canShoot(currentTime))
		{
			FireBall fire = new FireBall(bullets, this);
			
			double xSpeed = r.nextInt(100)/100.0;
			double ySpeed = (r.nextInt(100)/100.0) - (r.nextInt(100)/100.0);
			
			fire.setSpeed(-xSpeed/2.0, ySpeed/10.0);

			fire.setLocation(this.getX() - this.getWidth()/4, this.getY());
			this.getBullets().add(fire);
			this.setLastShot(currentTime);
		}
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
