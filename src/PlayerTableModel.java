import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PlayerTableModel extends AbstractTableModel {
    private final List<Player> playerList;
    private final String[] columnNames = new String[] {"Player Name", "Position", "Pitch"};
    private final Class[] columnClass = new Class[] {String.class, String.class, String.class};

    public PlayerTableModel(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return playerList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player row = playerList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getName();
        }
        else if (1 == columnIndex) {
            return row.getDefensePosition();
        }
        else if (2 == columnIndex) {
            return row.getPitch();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       Player row = playerList.get(rowIndex);
        if (0 == columnIndex) {
            row.setName((String) aValue);
        }
        else if (1 == columnIndex) {
            row.setDefensePosition((String) aValue);
        }
        else if (2 == columnIndex) {
            row.setPitch((String) aValue);
        }
    }
}
