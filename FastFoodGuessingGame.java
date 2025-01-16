import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class FastFoodGuessingGame extends JFrame implements ActionListener {
    private JTextField userInput;
    private final JLabel feedbackLabel;
    private JLabel imageLabel;
    private JLabel imageIcon;
    private final JButton submitButton;
    private final JButton endGameButton;
    private final JButton promptButton;
    private int correctGuesses = 0;
    private int totalGuesses = 0;
    private FastFoodItem currentItem;

    public FastFoodGuessingGame() {

        //JFrames
        JFrame frame = new JFrame();
        setTitle("Fast Food Guessing Game");
        frame.setSize(1024, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        ImageIcon icon = new ImageIcon("images/game_icon.png");
        ImageIcon correctAnswerIcon = new ImageIcon("correctAnswerIcon.jpg");
        ImageIcon wrongAnswerIcon = new ImageIcon("wrongAnswerIcon.png");
//JLabels
        JLabel gameIcon = new JLabel();
        gameIcon.setText("Fast Food Guessing Game");
        gameIcon.setIcon(icon);
        frame.add(gameIcon);
//Icons
        JLabel correctAnswerLabel = new JLabel();
        correctAnswerIcon.setImage(correctAnswerIcon.getImage());
        JLabel wrongAnswerLabel = new JLabel();
        wrongAnswerIcon.setImage(wrongAnswerIcon.getImage());
        this.add(gameIcon);

        // TextFields

        JTextField userInput = new JTextField();
        userInput.setPreferredSize(new Dimension(200, 30));
        this.add(userInput);

        // Przycisk "Submit Guess" - zamiana na RoundedButton
        submitButton = new RoundedButton("Submit Guess");
        submitButton.setFocusable(false);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        submitButton.addActionListener(e -> handleGuess());
        submitButton.setBackground(Color.GREEN);
        add(submitButton);

        // Przycisk "End Game" - zamiana na RoundedButton
        endGameButton = new RoundedButton("End Game");
        endGameButton.setFocusable(false);
        endGameButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        endGameButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        endGameButton.addActionListener(e -> endGame());
        endGameButton.setBackground(Color.RED);
        add(endGameButton);

        // Przycisk "Prompt" - zamiana na RoundedButton
        promptButton = new RoundedButton("Prompt");
        promptButton.setFocusable(false);
        promptButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        promptButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        promptButton.addActionListener(e -> showPrompt());
        promptButton.setBackground(Color.LIGHT_GRAY);
        add(promptButton);

        // Etykieta feedback
        feedbackLabel = new JLabel("");
        add(submitButton);
        add(endGameButton);
        add(promptButton);
        add(feedbackLabel);

        // Dodanie nasłuchiwaczy akcji do przycisków
        submitButton.addActionListener(e -> handleGuess());
        endGameButton.addActionListener(e -> endGame());
        promptButton.addActionListener(e -> showPrompt());
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(userInput.getText());
            int actualCalories = currentItem.getCalories();
            totalGuesses++;

            if (Math.abs(userGuess - actualCalories) <= 80) {
                feedbackLabel.setForeground(Color.GREEN);
                feedbackLabel.setText(
                        "You guessed correctly! Fat: " + currentItem.fat + "g, Carbs: " + currentItem.carbs + "g, Protein: " + currentItem.protein + "g, Calories: " + actualCalories);
                correctGuesses++;
                loadNextItem();
            } else {
                feedbackLabel.setForeground(Color.RED);
                feedbackLabel.setText("Not Correct. Fat: " + currentItem.fat + "g, Carbs: " + currentItem.carbs + "g, Calories: " + actualCalories);
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setForeground(Color.RED);
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    private void loadNextItem() {
        currentItem = FastFood.getRandomMenuItem();

        if (currentItem != null) {
            String imagePath = "src/images/" + currentItem.getImagePath();
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            userInput.setText("");
            imageLabel.setIcon(imageIcon);
        } else {
            JOptionPane.showMessageDialog(this, "No data found! ");
        }
    }

    private void endGame() {
        JOptionPane.showMessageDialog(this,
                "You guessed correctly " + correctGuesses + " out of " + totalGuesses + " times.");
        resetGame();
    }

    private void resetGame() {
        correctGuesses = 0;
        totalGuesses = 0;
        feedbackLabel.setText("");
        userInput.setText("");
    }

    private void showPrompt() {
        feedbackLabel.setForeground(Color.BLUE);
        feedbackLabel.setText("Try guessing within 80 calories range!");
    }



    private void initializeFrame() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //Buttons
    class RoundedButton extends JButton implements ActionListener {
        public RoundedButton(String label){
            super(label);
            setContentAreaFilled(false);
            setOpaque(false);
        }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == submitButton){
            handleGuess();
        }else if(e.getSource() == endGameButton){
            endGame();
        }else if(e.getSource() == promptButton){
            showPrompt();
        }

        if(e.getSource() == submitButton ){
            feedbackLabel.setText("That's right! Keep going!");
        imageLabel.setVisible(true);
            handleGuess();
    }else if (e.getSource() == submitButton){
        feedbackLabel.setText("Try again! You can do it!");
        imageLabel.setVisible(true);
        handleGuess();
    }
    }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Rysowanie zaokrąglonego tła
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

            // Rysowanie tekstu
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
    }}
