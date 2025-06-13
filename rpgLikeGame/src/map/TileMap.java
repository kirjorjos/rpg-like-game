package map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import main.GamePanel;
import main.Spritesheet;

public class TileMap extends Spritesheet {
	
	private int[][] compressedMap;
	private Tile[] tileSet;
	
	protected TileMap(String filePath, String tilePath, String[] tiles) {
		super(tilePath, GamePanel.baseTileSize, GamePanel.baseTileSize);
		try {
			loadMap(filePath, tiles);
		} catch (IOException e) {
			System.out.println("Invalid map file loaded.");
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath, String[] tiles) throws IOException {
	    Scanner scanner = new Scanner(this.getClass().getResourceAsStream(filePath));
	    Set<Integer> uniqueSet = new LinkedHashSet<>();

	    ArrayList<int[]> rawRows = new ArrayList<int[]>();

	    // Step 1: Parse lines and store raw tile IDs
	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine().trim();
	        String[] tokens = line.split("\\s+");
	        int[] rawRow = new int[tokens.length];

	        for (int i = 0; i < tokens.length; i++) {
	            String code = tokens[i];

	            // Find index in tiles[]
	            for (int j = 0; j < tiles.length; j++) {
	                if (code.equals(tiles[j])) {
	                    rawRow[i] = j;        // raw tile ID
	                    uniqueSet.add(j);     // collect unique tile ID
	                    break;
	                }
	            }
	        }

	        rawRows.add(rawRow);
	    }

	    int[] uniqueTiles = uniqueSet.stream().mapToInt(Integer::intValue).toArray();
	    tileSet = new Tile[uniqueTiles.length];
	    HashMap<Integer, Integer> rawToCompactId = new HashMap<>();

	    for (int i = 0; i < uniqueTiles.length; i++) {
	        int rawId = uniqueTiles[i];
	        tileSet[i] = new Tile(getSprite(rawId), false);
	        rawToCompactId.put(rawId, i);
	    }

	    compressedMap = new int[rawRows.size()][];
	    for (int row = 0; row < rawRows.size(); row++) {
	        int[] rawRow = rawRows.get(row);
	        int[] compactRow = new int[rawRow.length];

	        for (int col = 0; col < rawRow.length; col++) {
	            compactRow[col] = rawToCompactId.get(rawRow[col]);
	        }

	        compressedMap[row] = compactRow;
	    }

	    scanner.close();
	    for (int i = 0; i < compressedMap.length; i++) {
	    	for (int j = 0; j < compressedMap[i].length; j++) {
	    		System.out.print(compressedMap[i][j] + " ");
	    	}
	    	System.out.println();
	    }
	}

	
	public Tile getTile(int index) {
		return tileSet[index];
	}
	
	public void draw(Graphics g2) {
		for (int row = 0; row < compressedMap.length; row++) {
		    for (int col = 0; col < compressedMap[row].length; col++) {
		        int index = compressedMap[row][col];
		        BufferedImage texture = tileSet[index].getTexture();
		        g2.drawImage(texture, col * GamePanel.tileSize, row * GamePanel.tileSize, GamePanel.tileSize, GamePanel.tileSize, null);
		    }
		}
	}
}
