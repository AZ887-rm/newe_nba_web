package test;

import dao.DBConn;
import swing.home_page.LoginPage;
import swing.user_page.UserMainMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) throws IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int update_row = 0;
        String sql = "";
        String username = "root";
        String password = "12345678";

        // get connection
        conn = DBConn.getConn(username, password);

//        Login status = new Login(conn);
//        status.run();
        LoginPage login = new LoginPage(conn, "NBA data system");
//        ImageIO.read(new File("photos/thumbnails/" + 1610612737 + ".png"));
//        photoLabel = new JLabel(new ImageIcon(pic.getScaledInstance(500, 350, Image.SCALE_SMOOTH)));
    }
}
