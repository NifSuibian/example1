package context;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.models.Product;
import i18n.TextCodes;
import i18n.Translations;
import ui.custom.BaseWindow;
import ui.custom.ButtonComponent;
import ui.custom.PanelBox;
import ui.custom.grid.GridTable;
import ui.custom.grid.table.ProductTableModel;
import utils.Palette;

public class DashBoard extends BaseWindow {
    private PanelBox leftBox;
    private PanelBox middle;
    private PanelBox rightBox;
    private Translations lang;
    private ProductTableModel productModel;
    private ArrayList<String> navList;

    public DashBoard(Translations lang, String title) {
        super(title);
        this.lang = lang;
        navList = new ArrayList<>();
        navList.add(this.lang.getI18nText(TextCodes.registerUserNav));
        navList.add(this.lang.getI18nText(TextCodes.registerUserMainNav));

        createPanels();
        createGrid();
        createMenu();
    }

    public void setData(ArrayList<Product> products) {
        productModel.setData(products);
        productModel.fireTableDataChanged();
    }

    public void refresh(Product product) {
        productModel.addRow(product);
        ArrayList<Product> updatedData = productModel.getData();
        productModel.setData(updatedData);
        showProductDetails(product);
    }

    public void showProductDetails(Product product) {
        String details = "Nombre: " + product.getName() + "\n"
                        + "Descripción: " + product.getDescription() + "\n"
                        + "Tipo: " + product.getType() + "\n"
                        + "Precio: " + product.getPrice();

        JOptionPane.showMessageDialog(this, details, "Detalles del Producto", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createMenu() {
        Integer bottomMargin = 10;
        for (String item : this.navList) {
            ButtonComponent button = new ButtonComponent(item, this.leftBox);
            button.setActionCommand(item);
            button.setSize(new Dimension(200, 40));
            Integer y = button.getHeight() * this.navList.indexOf(item) + bottomMargin;
            button.setPosition(y);
            button.addActionListener((event) -> {
                if (event.getActionCommand().equals(this.lang.getI18nText(TextCodes.registerUserNav))) {
                    this.getListener().onEvent("click-register", event);
                }
            });
            this.leftBox.add(button);
        }
    }

    private void createGrid() {
        ArrayList<Product> data = new ArrayList<>();
        productModel = new ProductTableModel(data);
        GridTable grid = new GridTable(productModel);
        this.middle.add(grid.getScrollPane());
    }

    private void createPanels() {
        this.leftBox = new PanelBox();
        leftBox.roundedBorder(20);
        leftBox.setSize(new Dimension(300, 670));
        leftBox.setLocation(10, 10);
        leftBox.setBackground(Palette.secondary);
        leftBox.setLayout(null);
        this.add(leftBox);

        this.middle = new PanelBox();
        middle.roundedBorder(20);
        middle.setSize(new Dimension(600, 670));
        middle.setLocation(320, 10);
        middle.setBackground(Palette.primary);
        middle.setLayout(null);
        this.add(middle);

        this.rightBox = new PanelBox();
        rightBox.roundedBorder(20);
        rightBox.setSize(new Dimension(250, 670));
        rightBox.setLocation(930, 10);
        rightBox.setBackground(Palette.third);
        rightBox.setLayout(null);
        this.add(rightBox);
    }
}
