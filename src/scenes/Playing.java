package scenes;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import ui.ActionBar;


public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;

	private ActionBar bottomBar;
	private int mouseX, mouseY;


	public Playing(Game game) {
		super(game);
		loadDefaultLevel();

		bottomBar = new ActionBar(0, 540, 540, 100, this);

	}

	private void loadDefaultLevel() {
		lvl = LoadSave.getLevelData("new_level");
	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	@Override
	public void render(Graphics g) {
		drawLevel(g);
		bottomBar.draw(g);
	}

	private void drawLevel(Graphics g) {
    for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(getSprite(id), x * 27, y * 27, null);
			}
		}
  }

	private BufferedImage getSprite(int id) {
		return game.getTileManager().getSprite(id);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 540) {
			bottomBar.mouseClicked(x, y);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 540) {
			bottomBar.mouseMoved(x, y);
		} else {
			mouseX = (x / 27) * 27;
			mouseY = (y / 27) * 27;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 540) {
			bottomBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bottomBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {
		
	}

}