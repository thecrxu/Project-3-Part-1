import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class SquirtleCharCollision extends BasicCollisionGroup
{

	
	public void collided(Sprite squirt, Sprite charm)
	{
		((Squirtle)squirt).setHealth(0);
		squirt.setActive(false);
		charm.setActive(false);	
	}

}
