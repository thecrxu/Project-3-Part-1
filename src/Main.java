import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;


public class Main
{
    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new SquirtEngine(), new Dimension(1200, 800), false);
        loader.start();
    }
}
