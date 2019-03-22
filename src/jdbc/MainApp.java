package jdbc;

import jdbc.db.DBConnecton;

public class MainApp {

    public static void main(String[] args) {
        if (DBConnecton.getConnection() != null) {
            System.out.println("connection success");
        } else {
            System.out.println("connection failed");
        }
    }
}
