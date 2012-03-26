import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.golden.gamedev.*;
import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

public class ShooterGame extends GameObject
{
	private static Pokemon myShip;
	private SpriteGroup BULLET_GROUP;
	private Background background;
	private SystemTimer myTimer;
	private SpriteGroup ENEMY_GROUP;
	private CollisionManager collisionType;
	private CollisionManager powerType;
	private CollisionManager playerType;
	private GameFont f;
	private long startTime;
	private SpriteGroup POWERUP_GROUP;
	private SpriteGroup SQUIRTLE_GROUP;
	private PlayField field;
	private SpriteGroup ENEMY_BULLETS;
	private EnemyGenerator en;
	private CollisionManager charType;
	private static String statusMessage;
	private static String type = "Shooter";
	
	public ShooterGame(GameEngine parent)
	{
		super(parent);
	}
	
	public static String getType()
	{
		return type;
	}
	
	public static String getStatus()
	{
		return statusMessage;
	}
	
	public static int getScore()
	{
		return myShip.getScore();
	}


	public void initResources() 
	{
		background = new ColorBackground(Color.BLACK, 1280, 800);
		myTimer = new SystemTimer();
		startTime = myTimer.getTime();
		
		initBullets();
		initShip();
		initEnemies();
		initFont();
		initPowerUps();
		initCollisions();
		initPlayField();
	}
	
	public void initPlayField()
	{
		field = new PlayField();
		field.setBackground(background);
		field.addGroup(SQUIRTLE_GROUP);
		field.addGroup(ENEMY_GROUP);
		field.addGroup(BULLET_GROUP);
		field.addGroup(ENEMY_BULLETS);
		field.addGroup(POWERUP_GROUP);
		field.addCollisionGroup(SQUIRTLE_GROUP, POWERUP_GROUP, powerType);
		field.addCollisionGroup(BULLET_GROUP, ENEMY_GROUP, collisionType);
		field.addCollisionGroup(SQUIRTLE_GROUP, ENEMY_BULLETS, playerType);
		field.addCollisionGroup(SQUIRTLE_GROUP, ENEMY_GROUP, charType);
	}
	
	public void initPowerUps()
	{
		POWERUP_GROUP = new SpriteGroup("PowerUp Group");
	}
	
	public void initShip()
	{
		SQUIRTLE_GROUP = new SpriteGroup("Squirtle Group");
		myShip = new Squirtle(getImage("resources/Squirtle.png"), BULLET_GROUP);
		myShip.setLocation(getWidth() / 2 - myShip.getWidth() / 2, 
                             getHeight() / 2 - myShip.getHeight() / 2);

		SQUIRTLE_GROUP.add(myShip);
	}
	
	public void initBullets()
	{
		BULLET_GROUP = new SpriteGroup("Bullet Group");
		ENEMY_BULLETS = new SpriteGroup("Enemy Bullets");
	}
	
	public void initCollisions()
	{
		collisionType = new ShotToSpriteCollision();
		powerType = new PowerUpCollision();
		playerType = new SquirtleFireCollision();
		charType = new SquirtleCharCollision();
	}
	
	public void initEnemies()
	{
		ENEMY_GROUP = new SpriteGroup("Enemy Group");
		en = new EnemyGenerator();	    
	}
	
	public void initFont()
	{
		Font font = new Font( "Monospaced", Font.BOLD, 24 );
		f = fontManager.getFont(font);
	}

	public void render(Graphics2D pen) 
	{	
		field.render(pen);
		
		pen.setColor(Color.RED);
		f.drawString(pen, "Health: " + myShip.getHealth(), GameFont.LEFT, 40, 0, 20);
		
		pen.setColor(Color.BLUE);
		f.drawString(pen, "Score: " + myShip.getScore() , GameFont.LEFT, 370, 0, 20);
		
		pen.setColor(Color.YELLOW);
		f.drawString(pen, "Time: " + (myTimer.getTime()-startTime)/1000 , GameFont.RIGHT, 700, 0, 20);
	}
	
	public void processPowerUps()
	{
	    if(myShip.getPowerUps().size() > 0)
	    {
	    	POWERUP_GROUP.add(myShip.getPowerUps().remove(0));
	    }
	}
	
	public void createAndFire(long elapsedTime)
	{
		   en.createEnemies(getWidth(), myTimer.getTime(), ENEMY_GROUP, ENEMY_BULLETS);
		    
			 Sprite[] sprites = ENEMY_GROUP.getSprites();
			 
			 for (Sprite sp: sprites)
			 {
				 if (sp != null)
					 ((Enemy)sp).fire(elapsedTime);
			 }
	}

	 public void update(long elapsedTime)
	 {
		 	createAndFire(elapsedTime);
		 	
		 	processPowerUps();

	        field.update(elapsedTime);

	        processKeyPresses(elapsedTime);

	        manageSprites();
	        
	        if ((myTimer.getTime()-startTime)/1000 >= 30)
	        {
	        	statusMessage = "Congratulations, you repelled the Charmander invasion";
	        	parent.nextGameID = 2;
	        	finish();
	        }
	        
	        if (SQUIRTLE_GROUP.getActiveSprite() == null)
	        {
	        	statusMessage = "You were killed before time ran out";
	        	parent.nextGameID = 2;
	        	finish();
	        }
	    }
	 
	 public void manageSprites()
	 {
		    removeSprites(BULLET_GROUP);
		    removeSprites(ENEMY_GROUP);
		    removeSprites(ENEMY_BULLETS);
		    removeSprites(POWERUP_GROUP);
		    removeOwner(BULLET_GROUP);
		    removeOwner(ENEMY_BULLETS);
	 }
	 
	 public void processKeyPresses(long elapsedTime)
	 {
		 if (keyDown(KeyEvent.VK_DOWN)) 
	        {
	        	if(myShip.getY() + myShip.getHeight() < getHeight() )
	        		myShip.move(0, 0.5*elapsedTime);
	        }
	        if (keyDown(KeyEvent.VK_UP)) 
	        {
	        	if(myShip.getY() > 0 )
	        		myShip.move(0, -0.5*elapsedTime);
	        }
	        if (keyDown(KeyEvent.VK_RIGHT)) 
	        {
	        	if(myShip.getX() + myShip.getWidth() < getWidth())
	        		myShip.move(0.5*elapsedTime, 0);
	        }
	        if (keyDown(KeyEvent.VK_LEFT)) 
	        {
	        	if(myShip.getX() > 0)
	        		myShip.move(-0.5*elapsedTime, 0);
	        }
	        
	        if (keyDown(KeyEvent.VK_SPACE))
	        {
	        	long currentTime = myTimer.getTime();
	        	if (myShip.canShoot(currentTime))
	        	{
	        	Sprite bullet = new Bullet(getImage("resources/Bubble.png"), myShip);

	        	BULLET_GROUP.add(bullet);
	        	myShip.setLastShot(currentTime);
	        	}
	        }
	 }
	 
	 
		
		public void removeOwner(SpriteGroup bullets)
		{
			 Sprite[] sprites = bullets.getSprites();
			 
			 for (Sprite sp: sprites)
			 {
				 if (sp!= null && ((Bullet) sp).getOwner().isActive() == false)
					 sp.setActive(false);
			 }
		}
		
	 public void removeSprites(SpriteGroup s)
	 {
		 Sprite[] sprites = s.getSprites();
		 
		 for (Sprite sp: sprites)
		 {
			 if (sp != null && !inBounds(sp))
				 sp.setActive(false);
		 }
	 }
	 
	 
	 
	 public boolean inBounds(Sprite s)
	 {
		 return (s.getX() > 0 && s.getY() > 0 && s.getX() < getWidth() && s.getY() < getHeight());
	 }

}
