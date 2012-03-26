import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.SpriteGroup;


public class Enemy extends Pokemon
{
	private Random r;
	
	private static BufferedImage i;
	
	static{	
		try {
			File f = new File("C:\\Users\\Robert\\workspace\\Project3Part1\\src\\resources\\Charmander.png");
			
			i = ImageIO.read(f);

		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	public Enemy(SpriteGroup bullets)
	{
		this(i, bullets);
		r = new Random();
	}
	
	
	public Enemy(BufferedImage i, SpriteGroup bullets) 
	{
		super(i, bullets);
		setHealth(100);
		setFireRate(800);
	}
	
	public void fire(long elapsedTime)
	{
		long currentTime = new SystemTimer().getTime();
		
		if (this.canShoot(currentTime))
		{
			FireBall fire = new FireBall(this);
			
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
