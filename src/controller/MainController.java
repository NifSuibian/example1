package controller;

import config.Environments;
import context.DashBoard;
import context.Login;
import context.RegisterForm;
import data.ProductManager;
import data.models.Product;
import i18n.Translations;

public class MainController {
    ProductManager productManager = new ProductManager(Environments.pathFile);
    Translations translations;
    Login login;
    DashBoard dashBoard;
    RegisterForm registerForm;

    public MainController() {
        System.out.println("MainController");
        translations = new Translations();
        login = new Login(translations, translations.getI18nText("title-text"));
        dashBoard = new DashBoard(translations, translations.getI18nText("DashBoard"));
        registerForm = new RegisterForm(dashBoard, productManager, translations, translations.getI18nText("title-text"));
        dashBoard.setData(productManager.getProducts());
        login.addEventListener((String actionEvent, Object data) -> {
            if (actionEvent.equals("click-login")) {
                login.hideWindow();
                dashBoard.showWindow();
            }
        });
        dashBoard.addEventListener((String actionEvent, Object data) -> {
            if (actionEvent.equals("click-register")) {
                registerForm.showWindow();
            }
        });
        registerForm.addEventListener((String actionEvent, Object data) -> {
            Product product = (Product) data;
            if (actionEvent.equals("click-register")) {
                productManager.saveProduct(product);
                dashBoard.refresh(product);
            }
        });
        login.showWindow();
    }

    public static void main(String[] args) {
        new MainController();
    }
}
