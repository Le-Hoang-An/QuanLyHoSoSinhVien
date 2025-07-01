import view.LoginView;
import controller.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginView login = new LoginView();
        login.setVisible(true);
        new LoginController(login);
    }
}
