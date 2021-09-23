package components;

import managers.GuessButtonListener;
import managers.HangmanGame;

import javax.swing.*;
import java.awt.*;

public class GameFieldsPanel extends JPanel {

    private HangmanGame hangmanGame;

    private JPanel userResponsePanel;
    private JPanel textInputPanel;
    private JPanel headerInfoPanel;
    private JPanel guessedWordsPanel;

    private JLabel wordGuessLabel;
    private JLabel progressLabel;
    private JLabel characterCountLabel;
    private JLabel guessedLettersLabel;
    private JLabel lettersGuessed;
    private JLabel inputResponseMessage;
    private JButton wordGuessButton;
    private JTextField wordGuessField;

    public GameFieldsPanel(int numberLetters, GuessButtonListener guessButtonListener) {
        //setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setLayout(new GridLayout(3, 1));

        //HEADER INFO PANEL
        headerInfoPanel = new JPanel();
        headerInfoPanel.setLayout(new BorderLayout());

        progressLabel = new JLabel("", SwingConstants.CENTER);
        progressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerInfoPanel.add(progressLabel, BorderLayout.NORTH);

        characterCountLabel = new JLabel("Your word has " + Integer.toString(numberLetters) + " letters", SwingConstants.CENTER);
        characterCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerInfoPanel.add(characterCountLabel, BorderLayout.CENTER);

        wordGuessLabel = new JLabel("Enter letter or guess word: ", SwingConstants.CENTER);
        wordGuessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerInfoPanel.add(wordGuessLabel, BorderLayout.SOUTH);

        add(headerInfoPanel);

        //USER INPUT PANEL
        userResponsePanel = new JPanel();
        userResponsePanel.setLayout(new BoxLayout(userResponsePanel, BoxLayout.Y_AXIS));

        textInputPanel = new JPanel();
        textInputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        wordGuessField = new JTextField(14);
        wordGuessField.addActionListener(guessButtonListener);
        textInputPanel.add(wordGuessField);
        wordGuessButton = new JButton("guess");
        wordGuessButton.addActionListener(guessButtonListener);
        textInputPanel.add(wordGuessButton);

        userResponsePanel.add(textInputPanel);
        userResponsePanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        inputResponseMessage = new JLabel("", SwingConstants.CENTER);
        inputResponseMessage.setForeground(Color.RED);
        inputResponseMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputResponseMessage.setAlignmentY(Component.TOP_ALIGNMENT);

        userResponsePanel.add(inputResponseMessage, BorderLayout.SOUTH);

        add(userResponsePanel);

        //MORE DETAILS PANEL
        guessedWordsPanel = new JPanel();
        guessedWordsPanel.setLayout(new BoxLayout(guessedWordsPanel, BoxLayout.Y_AXIS));

        guessedLettersLabel = new JLabel("Letters guessed:", SwingConstants.CENTER);
        guessedLettersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessedWordsPanel.add(guessedLettersLabel);

        lettersGuessed = new JLabel("",SwingConstants.CENTER);
        lettersGuessed.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessedWordsPanel.add(lettersGuessed);

        add(guessedWordsPanel);

    }

    public void updateInputResponseMessage(String text) {
        inputResponseMessage.setText(text);
        inputResponseMessage.repaint();
    }

    public void updateProgress(String newProgress) {
        progressLabel.setText("Progress: " + newProgress);
        progressLabel.repaint();
    }

    public void updateLettersGuessed(String text) {
        lettersGuessed.setText(text);
        lettersGuessed.repaint();
    }

    public void clearInputField() {
        inputResponseMessage.setText("");
        inputResponseMessage.repaint();
    }

    public String getInputField() {
        return wordGuessField.getText();
    }
}
