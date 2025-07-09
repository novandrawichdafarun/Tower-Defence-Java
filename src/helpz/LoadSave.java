package helpz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class LoadSave {

	public static BufferedImage getSpriteAtlas() {

		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return img;
	}

	//* txt file */
	public static void CreateFile() {
		File txtFile = new File("res/test.txt");
		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//TODO: create a new lvl with default values
	public static void CreateLevel(String name, int[] idArr) {
		File newLevel = new File("res/" + name + ".txt");
		if(newLevel.exists()) {
			System.out.println("Level " + name + " already exists!");
			return;
		} else {
			try {
				newLevel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			WriteToFile(newLevel, idArr);
		}

	}

	private static void WriteToFile(File file, int[] idArr) {
		try {
			PrintWriter pw = new PrintWriter(file);
			for (Integer i : idArr) {
				pw.println(i);
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void SaveLevel(String name, int[][] idArr) {
		File lvlFile = new File("res/" + name + ".txt");
		
		if (lvlFile.exists()) {
			WriteToFile(lvlFile, Utilz.TwoDto1DintArr(idArr));
		} else {
			System.out.println("Level " + name + " does not exist!");
		}
	}

	private static ArrayList<Integer> ReadFromFile(File file) {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int[][] getLevelData(String name) {
		File lvlFile = new File("res/" + name + ".txt");
		
		if (lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile(lvlFile);
			return Utilz.ArrayListTo2Dint(list, 20, 20);
		} else {
			System.out.println("Level " + name + " does not exist!");
			return null;
		}
	}

	//TODO: Save 2d int array to file

	//TODO: Load int array from file

	

}
