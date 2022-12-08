package swing.user_page;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PlayerGraphPage extends AbstractSearchPage {
    private JPanel mainPanel;
    private JPanel searchBarPanel;
    private JPanel menuPanel;
    private JPanel bottomPanel;
    private JPanel graphPanel;
    private JPanel piePanel;
    private JPanel histogramPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JButton backBtn;

    public PlayerGraphPage(Connection conn, String username, int playerId) {
        super(conn, username);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void createUIComponents() {
        menuPanel = new JPanel();
        searchField = super.searchField;
        searchBtn = super.searchBtn;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
