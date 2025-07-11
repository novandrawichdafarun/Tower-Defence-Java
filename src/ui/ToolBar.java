package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static main.GameStates.MENU;
import static main.GameStates.setGameState;
import objects.Tile;
import scenes.Editing;

public class ToolBar extends Bar {

  private Editing editing;
  private MyButton bMenu, bSave;
  private Tile selectedTile;

  private ArrayList<MyButton> tileButtons = new ArrayList<>();

  public ToolBar(int x, int y, int width, int height, Editing editing) {
    super(x, y, width, height);
    this.editing = editing;
    initButtons();
  }

  private void initButtons() {
    bMenu = new MyButton("Menu", 5, 545, 100, 30);
    bSave = new MyButton("Save", 5, 580, 100, 30);

    int w = 50;
    int h = 50;
    int xStart = 110;
    int yStart = 550;
    int xOffset = (int) (w * 1.1f);

    for (Tile tile : editing.getGame().getTileManager().tiles) {
      tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * tileButtons.size(), yStart, w, h, tileButtons.size()));
    }
  }

  public void draw(Graphics g) {
    //* Background */
    g.setColor(new Color(0x7630ff));
    g.fillRect(x, y, width, height);

    //* Buttons */
		drawButtons(g);
  }

  private void drawButtons(Graphics g) {
    bMenu.draw(g);
    bSave.draw(g);

    drawTileButtons(g);
    drawSelectedTile(g);
  }
  
    private void drawSelectedTile(Graphics g) {
    if (selectedTile != null) {
      g.drawImage(selectedTile.getSprite(), 450, 550, 50, 50, null);
      g.setColor(Color.black);
      g.drawRect(450, 550, 50, 50);
    }
  }

  private void drawTileButtons(Graphics g) {
    for (MyButton b : tileButtons) {
      //* Sprite */
      g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);

      //* MouseOver */
      if (b.isMouseOver()) {
        g.setColor(Color.WHITE);
      } else {
        g.setColor(Color.BLACK);
      }

      //* Border */
      g.drawRect(b.x, b.y, b.width, b.height);

      //* MousePressed */
      if (b.isMousePressed()) {
        g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
        g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
      }

    }
  }

  public BufferedImage getButtImg(int id) {
    return editing.getGame().getTileManager().getSprite(id);
  }


  public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			setGameState(MENU);
		} else if (bSave.getBounds().contains(x, y)) {
      saveLevel();
    } else {
      for (MyButton b : tileButtons) {
        if (b.getBounds().contains(x, y)) {
          selectedTile = editing.getGame().getTileManager().getTile(b.getId());
          editing.setSelectedTile(selectedTile);
          return;
        }
      }
    }
	}

	private void saveLevel() {
    editing.saveLevel();
  }

  public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
    bSave.setMouseOver(false);
    for (MyButton b : tileButtons) {
      b.setMouseOver(false);
    }
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
    } else if (bSave.getBounds().contains(x, y)) {
      bSave.setMouseOver(true);
    } else {
      for (MyButton b : tileButtons) {
        if (b.getBounds().contains(x, y)) {
          b.setMouseOver(true);
          return;
        }
      }
    }
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
    } else if (bSave.getBounds().contains(x, y)) {
      bSave.setMousePressed(true);
    } else {
      for (MyButton b : tileButtons) {
        if (b.getBounds().contains(x, y)) {
          b.setMousePressed(true);
          return;
        }
      }
    }
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSave.resetBooleans();
		for (MyButton b : tileButtons) {
      b.resetBooleans();
    }
	}
}
