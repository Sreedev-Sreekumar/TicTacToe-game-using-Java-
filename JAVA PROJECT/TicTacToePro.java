import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TicTacToePro extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private JLabel statusLabel, scoreLabel;
    private int playerScore = 0, computerScore = 0, drawScore = 0;
    private boolean playerTurn = true;
    private Random random = new Random();

    public TicTacToePro() {

        setTitle("Tic Tac Toe - PRO Edition");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(20, 20, 20));

        // Title
        JLabel title = new JLabel("TIC TAC TOE", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.CYAN);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        add(title, BorderLayout.NORTH);

        // Game Panel
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3, 5, 5));
        gamePanel.setBackground(new Color(30, 30, 30));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        Font font = new Font("Arial", Font.BOLD, 80);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(new Color(50, 50, 50));
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].addActionListener(this);
                gamePanel.add(buttons[i][j]);
            }
        }

        add(gamePanel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 1));
        bottomPanel.setBackground(new Color(20, 20, 20));

        statusLabel = new JLabel("Your Turn (X)", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(Color.GREEN);

        scoreLabel = new JLabel(getScoreText(), JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(Color.ORANGE);

        JButton resetBtn = new JButton("Restart Game");
        resetBtn.setFont(new Font("Arial", Font.BOLD, 18));
        resetBtn.setBackground(Color.RED);
        resetBtn.setForeground(Color.WHITE);
        resetBtn.addActionListener(e -> resetBoard());

        bottomPanel.add(statusLabel);
        bottomPanel.add(scoreLabel);
        bottomPanel.add(resetBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("") || !playerTurn)
            return;

        clicked.setText("X");
        clicked.setForeground(Color.CYAN);

        if (checkWinner("X")) {
            playerScore++;
            highlightWinner("X");
            statusLabel.setText("🎉 You Win!");
            updateScore();
            return;
        }

        if (isBoardFull()) {
            drawScore++;
            statusLabel.setText("It's a Draw!");
            updateScore();
            return;
        }

        playerTurn = false;
        statusLabel.setText("Computer Thinking...");
        computerMove();
    }

    private void computerMove() {

        int row, col;

        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText("O");
                buttons[row][col].setForeground(Color.PINK);
                break;
            }
        }

        if (checkWinner("O")) {
            computerScore++;
            highlightWinner("O");
            statusLabel.setText("💻 Computer Wins!");
            updateScore();
            playerTurn = true;
            return;
        }

        if (isBoardFull()) {
            drawScore++;
            statusLabel.setText("It's a Draw!");
            updateScore();
            playerTurn = true;
            return;
        }

        playerTurn = true;
        statusLabel.setText("Your Turn (X)");
    }

    private boolean checkWinner(String symbol) {

        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(symbol) &&
                buttons[i][1].getText().equals(symbol) &&
                buttons[i][2].getText().equals(symbol))
                return true;

            if (buttons[0][i].getText().equals(symbol) &&
                buttons[1][i].getText().equals(symbol) &&
                buttons[2][i].getText().equals(symbol))
                return true;
        }

        if (buttons[0][0].getText().equals(symbol) &&
            buttons[1][1].getText().equals(symbol) &&
            buttons[2][2].getText().equals(symbol))
            return true;

        if (buttons[0][2].getText().equals(symbol) &&
            buttons[1][1].getText().equals(symbol) &&
            buttons[2][0].getText().equals(symbol))
            return true;

        return false;
    }

    private void highlightWinner(String symbol) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(symbol) &&
                buttons[i][1].getText().equals(symbol) &&
                buttons[i][2].getText().equals(symbol)) {
                buttons[i][0].setBackground(Color.GREEN);
                buttons[i][1].setBackground(Color.GREEN);
                buttons[i][2].setBackground(Color.GREEN);
            }

            if (buttons[0][i].getText().equals(symbol) &&
                buttons[1][i].getText().equals(symbol) &&
                buttons[2][i].getText().equals(symbol)) {
                buttons[0][i].setBackground(Color.GREEN);
                buttons[1][i].setBackground(Color.GREEN);
                buttons[2][i].setBackground(Color.GREEN);
            }
        }

        if (buttons[0][0].getText().equals(symbol) &&
            buttons[1][1].getText().equals(symbol) &&
            buttons[2][2].getText().equals(symbol)) {
            buttons[0][0].setBackground(Color.GREEN);
            buttons[1][1].setBackground(Color.GREEN);
            buttons[2][2].setBackground(Color.GREEN);
        }

        if (buttons[0][2].getText().equals(symbol) &&
            buttons[1][1].getText().equals(symbol) &&
            buttons[2][0].getText().equals(symbol)) {
            buttons[0][2].setBackground(Color.GREEN);
            buttons[1][1].setBackground(Color.GREEN);
            buttons[2][0].setBackground(Color.GREEN);
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(new Color(50, 50, 50));
            }
        statusLabel.setText("Your Turn (X)");
        playerTurn = true;
    }

    private void updateScore() {
        scoreLabel.setText(getScoreText());
    }

    private String getScoreText() {
        return "Player: " + playerScore + "   |   Computer: " + computerScore + "   |   Draws: " + drawScore;
    }

    public static void main(String[] args) {
        new TicTacToePro();
    }
}