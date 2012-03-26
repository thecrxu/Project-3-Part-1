import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class PowerUpCollision extends BasicCollisionGroup
{

	public void collided(Sprite squirt, Sprite up) 
	{	
		((Squirtle)squirt).setFireRate(((Squirtle)squirt).getFireRate()/2.0);
		up.setActive(false);
	}
}
