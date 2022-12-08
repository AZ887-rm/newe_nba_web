package swing.user_page;

import dao.DBConn;
import service.user_modules.NewsModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class UserNewsPage extends AbstractPage {
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JLabel headLabel;
    private JPanel headPanel;
    private JTable table1;
    private JScrollPane tablePanel;
    private JPanel mainPanel;
    private JPanel turnPagePanel;
    private JPanel BottomPanel;
    private JLabel pageLabel;

    int page;

    public UserNewsPage(Connection conn, String username, int page) {
        super(conn, username);
        this.page = page;

        setContentPane(mainPanel);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (page == 0)
                    JOptionPane.showMessageDialog(null, "No previous page!");
                else {
                    dispose();
                    new UserNewsPage(conn, username, page - 1);
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new NewsModule(conn, username).getNews(page + 1).size() == 0)
                    JOptionPane.showMessageDialog(null, "Reach the bottom");
                else
                    new UserNewsPage(conn, username, page + 1);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn3) {
                    dispose();
                    new UserMainMenu(conn, username);
                }
            }
        });

        pageLabel.setText("Page " + (this.page + 1));

        setVisible(true);
    }

    private void createUIComponents() {
        String[] header = {"topic", "title", "summary", "author", "publish date"};
        List<List<String>>listData = new NewsModule(super.conn, super.username).getNews(this.page);
        int m = listData.size(), n = listData.get(0).size();
        Object[][] data = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = listData.get(i).get(j);
            }
        }

        DefaultTableModel defaultModel = new DefaultTableModel(data, header);
        table1 = new JTable(defaultModel);
    }
}
