package ui;

import static main.GameStates.MENU;
import static main.GameStates.setGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import objects.Tile;
import scenes.Playing;

public class BottomBar {

  private int x, y, width, height;
  private Playing playing;
  private MyButton bMenu;

  private ArrayList<MyButton> tileButtons = new ArrayList<>();


  public BottomBar(int x, int y, int width, int height, Playing playing) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.playing = playing;

		initButtons();
  }

  private void initButtons() {
		bMenu = new MyButton("Menu", 5, 645, 100, 30);

    int w = 50;
    int h = 50;
    int xStart = 110;
    int yStart = 650;
    int xOffset = (int) (w * 1.1f);

    int i = 0;
    for(Tile tile : playing.getTileManager().tiles) {
      tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i++, yStart, w, h));
    }

	}

  private void drawButtons(Graphics g) {
		bMenu.draw(g);

    for (MyButton b : tileButtons) {
      b.draw(g);
    }
	}

  public void draw(Graphics g) {
    //* Background */
    g.setColor(new Color(220,123,15));
    g.fillRect(x, y, width, height);

    //* Buttons */
		drawButtons(g);
  }

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			setGameState(MENU);
		}
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
    }
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
    }
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		
	}

}
