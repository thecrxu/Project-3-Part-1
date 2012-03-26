import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class SquirtleFireCollision extends BasicCollisionGroup
{

	public void collided(Sprite squirt, Sprite fire)
	{
		((Pokemon) squirt).attacked(((Bullet) fire).getDamage());
		
		if (((Pokemon) squirt).isDead())
		{		
			squirt.setActive(false);
		}
		fire.setActive(false);
		
	}
}
