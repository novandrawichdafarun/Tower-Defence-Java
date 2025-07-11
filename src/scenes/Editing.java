package scenes;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import objects.Tile;
import ui.ToolBar;

public class Editing extends GameScene implements SceneMethods {

  private int[][] lvl;
	private Tile selectedTile;

  private int mouseX, mouseY;
	private int lastTileX, lastTileY, lastTileId;
	private boolean drawSelect;
  private ToolBar toolBar;

  public Editing(Game game) {
    super(game);
		loadDefaultLevel();
    toolBar = new ToolBar(0, 540, 540, 100, this);
  }

  private void loadDefaultLevel() {
		lvl = LoadSave.getLevelData("new_level");
	}

  @Override
  public void render(Graphics g) {
    drawLevel(g);
    toolBar.draw(g);
    drawSelectedTile(g);
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

  private void drawSelectedTile(Graphics g) {
		if (selectedTile != null && drawSelect) {
			g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 27, 27, null);
		}
	}

  public void saveLevel() {
		LoadSave.SaveLevel("new_level", lvl);
    game.getPlaying().setLevel(lvl);
	}

  public void setSelectedTile(Tile tile) {
		drawSelect = true;
		this.selectedTile = tile;
	}

  private void changeTile(int x, int y) {
		if (selectedTile != null) {
			int tileX = x / 27;
			int tileY = y / 27;

			if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId()) {
				return; 
			}

			lastTileX = tileX;
			lastTileY = tileY;

			lvl[tileY][tileX] = selectedTile.getId();
		}
	}

  @Override
  public void mouseClicked(int x, int y) {
    if (y >= 540) {
			toolBar.mouseClicked(x, y);
		} else {
			changeTile(mouseX, mouseY);
		}
  }

  @Override
  public void mouseMoved(int x, int y) {
    if (y >= 540) {
			toolBar.mouseMoved(x, y);
			drawSelect = false;
		} else {
			drawSelect = true;
			mouseX = (x / 27) * 27;
			mouseY = (y / 27) * 27;
		}
  }

  @Override
  public void mousePressed(int x, int y) {
    
  }

  @Override
  public void mouseReleased(int x, int y) {

  }

  @Override
  public void mouseDragged(int x, int y) {
    if (y >= 540) {

		} else {
			changeTile(x, y);
		}
  }
  
}
