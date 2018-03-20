import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author TimoshenkoStanislav
 */
public class CatalogTableModel implements TableModel {

    static final String[] columnNames = new String[]{"Type", "Brand", "Model", "Price"};
    static final Class[] columnTypes = new Class[]{String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class};
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private ArrayList<DeviceNode> infoNodes;

    public CatalogTableModel() {
        infoNodes = new ArrayList<DeviceNode>();
    }

    public CatalogTableModel(ArrayList<DeviceNode> al) {
        this.infoNodes = al;
    }

    public void setInfoArray(ArrayList<DeviceNode> al) {
        infoNodes = al;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return infoNodes.size();
    }

    public Class getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DeviceNode dn = infoNodes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dn.getType();
            case 1:
                return dn.getBrand();
            case 2:
                return dn.getModel();
            case 3:
                return dn.getPrice();
        }
        return "";
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    }
}
