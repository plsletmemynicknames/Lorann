package model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CellData;
import model.LevelData;
import model.MapData;

public abstract class LevelDAO extends AbstractDAO {

	final static private String sqlMapWithID = "{call findMapWithID(?)}";
	final static private String sqlCellsByMapID = "{call findCellsByMapID(?)}";
	final static private String sqlAllMaps = "{call findAllMaps}";
	final static private String sqlUpdateMap = "{call updateMap(?, ?, ?)}";
	final static private String sqlInsertCell = "{call insertCell(?, ?, ?, ?)}";
	final static private String sqlRemoveCellsWithMapID = "{call removeCellsWithMapID(?)}";

	public static MapData getMapWithID(final int id) throws SQLException {
		final CallableStatement callStatement = prepareCall(sqlMapWithID);
        MapData mapData = null;
        callStatement.setInt(1, id);
        if (callStatement.execute()) {
            final ResultSet result = callStatement.getResultSet();
            if (result.first()) {
                mapData = new MapData(	result.getInt(MapData.IDCOL),
                						result.getInt(MapData.WIDTHCOL),
                						result.getInt(MapData.HEIGHTCOL)	);
            }
            result.close();
        }
        return mapData;
	}
	
	public static List<CellData> getCellsByMapID(final int id) throws SQLException {
		final ArrayList<CellData> cells = new ArrayList<>();
        final CallableStatement callStatement = prepareCall(sqlCellsByMapID);
        callStatement.setInt(1, id);
        if (callStatement.execute()) {
            final ResultSet result = callStatement.getResultSet();

            for (boolean isResultLeft = result.first(); isResultLeft; isResultLeft = result.next()) {
                cells.add(new CellData(		result.getInt(CellData.IDCOL),
                							result.getInt(CellData.XCOL),
                							result.getInt(CellData.YCOL),
                							(char) ((String) result.getObject(CellData.SYMBOLCOL)).charAt(0),
                							result.getInt(CellData.IDMAPCOL)				));
            }
            result.close();
        }
        return cells;
	}
	
	public static List<MapData> getAllMaps() throws SQLException {
		final ArrayList<MapData> maps = new ArrayList<>();
        final CallableStatement callStatement = prepareCall(sqlAllMaps);
        if (callStatement.execute()) {
            final ResultSet result = callStatement.getResultSet();

            for (boolean isResultLeft = result.first(); isResultLeft; isResultLeft = result.next()) {
                maps.add(new MapData(	result.getInt(MapData.IDCOL),
                						result.getInt(MapData.WIDTHCOL),
                						result.getInt(MapData.HEIGHTCOL)	));
            }
            result.close();
        }
        return maps;
	}
	
	private static void updateMap(final MapData map) throws SQLException {
		final CallableStatement callStatement = prepareCall(sqlUpdateMap);
        callStatement.setInt(1, map.getID());
        callStatement.setInt(2, map.getWidth());
        callStatement.setInt(3, map.getHeight());
        callStatement.execute();
	}
	
	private static void insertCell(final CellData cell) throws SQLException {
		final CallableStatement callStatement = prepareCall(sqlInsertCell);
        callStatement.setInt(1, cell.getX());
        callStatement.setInt(2, cell.getY());
        callStatement.setString(3, "" + cell.getSymbol());
        callStatement.setInt(4, cell.getMapID());
        callStatement.execute();
	}
	
	private static void removeCellsWithMapID(final int mapID) throws SQLException {
		final CallableStatement callStatement = prepareCall(sqlRemoveCellsWithMapID);
        callStatement.setInt(1, mapID);
        callStatement.execute();
	}

	public static void saveFromFile(final File file, final int mapID) throws FileNotFoundException, SQLException {
		LevelData levelData = new LevelData(file);
		MapData mapData = (MapData) levelData.getMap();
		List<CellData> cells = levelData.getGrid();
		
		mapData.setID(mapID);
		updateMap(mapData);
		removeCellsWithMapID(mapID);
		
		for (final CellData cell : cells) {
			cell.setMapID(mapID);
			insertCell(cell);
		}
	}
	
	public static void saveFromFile(final File file) throws FileNotFoundException, SQLException {
		List<MapData> maps = getAllMaps();
		saveFromFile(file, maps.size()+1);
	}
}
