import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class Bullet extends Sprite 
{
	private int damage;
	private Pokemon owner;
	
	public Bullet(BufferedImage i, Pokemon s)
	{
		super(i);
		damage = 50;
		owner = s;
    	this.setLocation(s.getX()+s.getWidth()/2, s.getY());
    	this.setSpeed(.8, 0);
	}
	
	public Bullet()
	{
	}
	
	public Pokemon getOwner()
	{
		return owner;
	}
	
	public int getDamage()
	{
		return damage;
	}
}
