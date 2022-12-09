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
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Connection conn;
        String username;
        String password;

        // get connection
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Input your database username: ");
            username = sc.nextLine();
            System.out.print("Input your database password: ");
            password = sc.nextLine();
            conn = DBConn.getConn(username, password);

            if (conn == null) {
                System.out.println("Wrong username or password, please try again");
            } else
                break;
        }

        LoginPage login = new LoginPage(conn, "NBA data system");
    }
}
