import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;


public class SquirtEngine extends GameEngine
{

	public GameObject getGame(int GameID) 
	{
		  switch (GameID)
		  {
          case 0: 
             return new IntroMenu(this);
          case 1: 
        	  return new ShooterGame(this);
          case 2: 
        	  return new EndGameScreen(this, ShooterGame.getScore(), ShooterGame.getStatus(), ShooterGame.getType());
          case 3:
        	  return new EndGameScreen(this, Multiplayer.getScore(), Multiplayer.getStatus(), Multiplayer.getType());
          case 4:
        	  return new Multiplayer(this);
       }	  
		  return null;
	}
	
	public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new SquirtEngine(), new Dimension(1200, 800), false);
        loader.start();
    }

}
