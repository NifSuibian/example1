package ui.custom.grid.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import data.models.Product;

public class ProductTableModel extends AbstractTableModel {
    private ArrayList<Product> data;
    private String[] columns = {"Nombre", "Descripción", "Tipo", "Precio"};

    public ProductTableModel(ArrayList<Product> data) {
        this.data = data;
    }

    public void addRow(Product product) {
        this.data.add(product);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void setData(ArrayList<Product> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public ArrayList<Product> getData() {
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
        Product product = this.data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.getName();
            case 1:
                return product.getDescription();
            case 2:
                return product.getType();
            case 3:
                return product.getPrice();
            default:
                return null;
        }
    }
}
