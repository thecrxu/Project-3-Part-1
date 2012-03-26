import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.object.SpriteGroup;


public class EnemyGenerator 
{
	private long delayTime;
	private Random r;
	private long lastTime;
	
	public EnemyGenerator()
	{
		delayTime = 2000;
		r = new Random();
	}
	
	public void createEnemies(int width, long currentTime, SpriteGroup enemies, SpriteGroup bullets, BufferedImage i, BufferedImage s)
	{
		if (currentTime - lastTime > delayTime)
		{
			int row = r.nextInt(9)+1;
			Enemy en = new Enemy(i, s, bullets);
			en.setLocation(width-100, 800/row+200);
			
			double xSpeed = r.nextInt(100)/100.0;
			double ySpeed = (r.nextInt(100)/100.0) - (r.nextInt(100)/100.0);
			
			en.setSpeed(-xSpeed/2.0, ySpeed/10.0);

			enemies.add(en);
			lastTime = currentTime;
			delayTime -= 60;
		}
	}
}
