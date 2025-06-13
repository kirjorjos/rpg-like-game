package map;

public class WildernessMap extends TileMap {
	private static final String[] TILES = new String[] {"G", "T", "HP", "VP", "NWP", "NEP", "SWP", "SEP"};
	
	public WildernessMap(String filePath) {
	  super(filePath, "/tile/groundTileMap.png", TILES);
	}
}