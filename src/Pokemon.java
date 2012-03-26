import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


public abstract class Pokemon extends Sprite
{
	private int health;
	private int score;
	private double fireRate;
	private ArrayList<PowerUp> powerUps;
	private long lastShot;
	private SpriteGroup bullets;
	
	public Pokemon(BufferedImage i, SpriteGroup bullets)
	{
		super(i);
		this.bullets = bullets;
		powerUps = new ArrayList<PowerUp>();
		lastShot = new SystemTimer().getTime();
	}
	
	public SpriteGroup getBullets()
	{
		return bullets;
	}
	
	public boolean canShoot(long currentTime)
	{
		return (currentTime - lastShot >= fireRate);
	}
	
	public void setLastShot(long currentTime)
	{
		lastShot = currentTime;
	}
	
	public void addPowerUp(PowerUp p)
	{
		powerUps.add(p);
	}
	
	public ArrayList<PowerUp> getPowerUps()
	{
		return powerUps;
	}
	
	public double getFireRate()
	{
		return fireRate;
	}
	
	public void setFireRate(double rate)
	{
		fireRate = rate;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void increaseScore(int bonus)
	{
		score += bonus;
	}
	
	public void attacked(int damage)
	{
		health -= damage;
	}
	
	public boolean isDead()
	{
		return health <= 0;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int i)
	{
		health = i;
	}
}
