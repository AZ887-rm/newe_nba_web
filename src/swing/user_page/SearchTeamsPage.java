package swing.user_page;

import dao.DBConn;
import service.user_modules.SearchTeamModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SearchTeamsPage extends AbstractSearchPage {
    private JPanel mainPanel;
    private JTextField searchField;
    private JPanel searchBarPanel;
    private JButton searchBtn;
    private JPanel menuPanel;
    private JScrollPane scrollPanel;
    private JTable table;
    private JPanel tablePanel;
    private JPanel sidebarPanel;
    private JButton searchTeamBtn;
    private JButton searchGameBtn;
    private JButton backBtn;
    private JLabel globalPositionLabel;
    private JPanel bottomPanel;
    private List<Integer> index;

    public SearchTeamsPage(Connection conn, String username) {
        super(conn, username);

        this.setContentPane(mainPanel);
        setVisible(true);

        // add actionListener to buttons
        searchTeamBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int teamId = index.get(row);
                    dispose();
                    new TeamInfoPage(conn, username, teamId);
                } else
                    JOptionPane.showMessageDialog(null, "Please select a row in the table");
            }
        });

        searchGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int teamId = index.get(row);
                    dispose();
                    new GamesInfoPage(conn, username, new int[] {0, teamId});
                } else
                    JOptionPane.showMessageDialog(null, "Please select a row in the table");
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserMainMenu(conn, username);
            }
        });
    }

    private void createUIComponents() {
        menuPanel = super.menuPanel;
        searchField = super.searchField;
        searchBtn = super.searchBtn;

        // set table
        String[] header = {"team full name", "team abbreviation", "nickname"};
        index = new ArrayList<>();
        List<List<String>> listData = new SearchTeamModule(conn).getAllTeams(index);

        int m = listData.size(), n = listData.get(0).size();
        Object[][] data = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = listData.get(i).get(j);
            }
        }

        DefaultTableModel defaultModel = new DefaultTableModel(data, header);
        table = new JTable(defaultModel);
    }

    public static void main(String[] args) {
        new SearchTeamsPage(DBConn.getConn("root", "12345678"), "123");
    }
}
