package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import main.Game;
import static main.GameStates.*;
import ui.MyButton;

public class Menu extends GameScene implements SceneMethods {

	private BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	private Random random;

	private MyButton bPlaying, bSetting, bQuit;
	
	public Menu(Game game) {
		super(game);
		random = new Random();
		importImg();
		loadSprites();
		initButtons();
	}

	private void initButtons() {

		int w = 170;
		int h = w / 3;
		int x = 540 / 2 - w / 2;
		int y = 200;
		int yOffset = 100;

		bPlaying = new MyButton("Play", x, y, w, h);
		bSetting = new MyButton("Setting", x, y + yOffset, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);

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

	}

	private void importImg() {

		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadSprites() {

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x * 27, y * 27, 27, 27));
			}
		}

	}

	private int getRndInt() {
		return random.nextInt(100);
	}

	@Override
	public void mouseClicked(int x, int y) {
		
		if(bPlaying.getBounds().contains(x, y)) {
			setGameState(PLAYING);
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

		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
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
	}

    @Override
    public void mouseDragged(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}