package swing.user_page;

import dao.DBConn;
import service.user_modules.SearchGameModule;
import service.user_modules.SearchTeamModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class GamesInfoPage extends AbstractSearchPage {
    private JPanel menuPanel;
    private JPanel mainPanel;
    private JPanel searchBarPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JLabel globalPositionLabel;
    private JPanel tablePanel;
    private JScrollPane scrollPanel;
    private JTable table;
    private JButton playerBtn;
    private JButton teamBtn;
    private JButton awayTeamBtn;
    private JPanel bottomPanel;
    private JButton backBtn;
    private List<List<Integer>> index;
    private int[] source;

    public GamesInfoPage(Connection conn, String username, int[] source) {
        super(conn, username);
        this.source = source;

        this.setContentPane(mainPanel);
        setVisible(true);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SearchTeamsPage(conn, username);
            }
        });
    }

    private void createUIComponents() {
        menuPanel = super.menuPanel;
        searchField = super.searchField;
        searchBtn = super.searchBtn;

        // set table
        int s1 = source[0], s2 = source[1];
        if (s1 == 0) {
            String[] header = {"season", "date", "home", "away", "winner", "scores", "assists", "rebounds"};
            List<List<String>> listData = new SearchGameModule(conn).getTeamGames(s2);
            int m = listData.size(), n = listData.get(0).size();
            Object[][] data = new Object[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    data[i][j] = listData.get(i).get(j);
                }
            }
            DefaultTableModel defaultModel = new DefaultTableModel(data, header);
            table = new JTable(defaultModel);
        } else if (s1 == 1) {
            String[] header = {"team", "name", "min", "pts", "fgm", "fga", "fg3m", "fg3a", "ftm", "fta",
                    "oreb", "dreb", "reb", "ast", "blk", "_to", "pf"};
            List<List<String>> listData = new SearchGameModule(conn).getPlayerGames(s2);
            int m = listData.size(), n = listData.get(0).size();
            Object[][] data = new Object[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    data[i][j] = listData.get(i).get(j);
                }
            }
            DefaultTableModel defaultModel = new DefaultTableModel(data, header);
            table = new JTable(defaultModel);
        } else {
            String[] header = {"game id", "team id", "player id", "min", "pts", "fgm", "fga", "fg3m", "fg3a", "ftm", "fta",
                    "oreb", "dreb", "reb", "ast", "blk", "_to", "pf"};
            List<List<String>> listData = new SearchGameModule(conn).getGameDetails(s2);
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
    }
}
