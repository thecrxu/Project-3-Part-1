import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.background.ColorBackground;


public class EndGameScreen extends GameObject
{
	private int score;
	private String status;
	private Background background;
	private GameFont f;
	private String type;
	
	public EndGameScreen(GameEngine parent, int score, String status, String type)
	{
		super(parent);
		this.score = score;
		this.status = status;
		this.type = type;
	}

	public void initResources() 
	{
		background = new ColorBackground(Color.BLACK, 1280, 800);
		Font font = new Font( "Monospaced", Font.BOLD, 24 );
		f = fontManager.getFont(font);
	}

	public void render(Graphics2D pen) 
	{
		background.render(pen);
		pen.setColor(Color.BLUE);
		if (type.equals("Shooter"))
		{
			f.drawString(pen, "Final Score: " + score, GameFont.CENTER, 600, 200, 20);
		}
		f.drawString(pen, status, GameFont.CENTER, 600, 300, 20);
		f.drawString(pen, "Press 1 to start a new game or escape to exit", GameFont.CENTER, 600, 400, 20);
	}

	public void update(long elapsedTime) 
	{
		   if (keyDown(KeyEvent.VK_1)) 
	        {
	        	parent.nextGameID = 0;
	 	       finish();
	        }
	       if (keyDown(KeyEvent.VK_ESCAPE)) 
	        {
	 	       finish();
	        }
	}

}
