package ui.custom.grid.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import data.models.User;

public class UserTableModel extends AbstractTableModel {
    private ArrayList<Object> data;
    private String[] columns = {"Name", "Email", "Password", "Phone"};

    public UserTableModel(ArrayList<Object> data) {
        this.data = data;
    }

    public void addRow(Object item) {
        this.data.add(item);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void setData(ArrayList<?> data) {
        this.data = new ArrayList<>(data);
        fireTableDataChanged();
    }

    public ArrayList<Object> getData() {
        return this.data;
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object item = this.data.get(rowIndex);

        if (item instanceof User) {
            User user = (User) item;
            switch (columnIndex) {
                case 0:
                    return user.getName();
                case 1:
                    return user.getEmail();
                case 2:
                    return "********"; // Puedes cambiar esto según tus necesidades
                case 3:
                    return user.getPhone();
                default:
                    return null;
            }
        }

        return null;
    }
}
