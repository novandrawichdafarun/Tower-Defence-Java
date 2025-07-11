package scenes;

import java.awt.Color;
import java.awt.Graphics;
import main.Game;
import static main.GameStates.*;
import ui.MyButton;

public class Menu extends GameScene implements SceneMethods {

	private MyButton bPlaying, bEdit, bSetting, bQuit;
	
	public Menu(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {

		int w = 170;
		int h = w / 3;
		int x = 540 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bPlaying = new MyButton("Play", x, y, w, h);
		bEdit = new MyButton("Edit", x, y + yOffset, w, h);
		bSetting = new MyButton("Setting", x, y + yOffset * 2, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK.darker());
		g.fillRect(0, 0, 540, 640);
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bSetting.draw(g);
		bQuit.drawExit(g);
		bEdit.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		
		if(bPlaying.getBounds().contains(x, y)) {
			setGameState(PLAYING);
		} else if (bEdit.getBounds().contains(x, y)) {
			setGameState(EDIT);
		} else if (bSetting.getBounds().contains(x, y)) {
			setGameState(SETTINGS);
		} else if (bQuit.getBounds().contains(x, y)) {
			System.exit(0);
		}

	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bSetting.setMouseOver(false);
		bQuit.setMouseOver(false);
		bEdit.setMouseOver(false);

		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		} else if(bEdit.getBounds().contains(x, y)) {
			bEdit.setMouseOver(true);
		} else if(bSetting.getBounds().contains(x, y)) {
			bSetting.setMouseOver(true);
		} else if(bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {

		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		} else if(bEdit.getBounds().contains(x, y)) {
			bEdit.setMousePressed(true);
		} else if(bSetting.getBounds().contains(x, y)) {
			bSetting.setMousePressed(true);
		} else if(bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSetting.resetBooleans();
		bQuit.resetBooleans();
		bEdit.resetBooleans();
	}

    @Override
    public void mouseDragged(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}