import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Window extends JFrame {

    //Panel for text and random number selection tools.
    JPanel textPanel = new JPanel();
    int number;
    Random random = new Random();

    //Messages that are displayed based on if user guessed right and if the user
    private String askUserToGuess = "Guess a number from 1 - 10";
    private String userGuessedCorrectly = "Correct! Well done";
    private String userGuessedIncorrectly = "Incorrect! Try again";

    public Window(){
    }

    /**
     * This show screen method displays the Window and Start Button, panel and start button.
     * It also allows the user to start playing the number guessing game.
     * */
    void showScreen(){
        textPanel.setPreferredSize(new Dimension(400, 400));

        JButton startButton = new JButton("Start");
        startButton.setFocusable(false);

        textPanel.add(startButton);

        startButton.addActionListener((e) -> {
            startGame();
            number = setGuessingNumber(random);
            System.out.println(number);
        });
        this.setTitle("Number Guessing Game");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(textPanel);
    }

   /**
    * This method displays the buttons and allows the user to enter their guess
    * */
    private void startGame(){
        clearScreen();
        JPanel gamePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3));

        //Title
        JLabel label = new JLabel();
        label.setText(askUserToGuess);
        label.setPreferredSize(new Dimension(400, 50));
        JButton[] buttons = new JButton[10];


        //Making number buttons and their functions
        for (int i=0; i<buttons.length; i++ ){
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].setFocusable(false);
            buttonPanel.add(buttons[i]);
            final int value = i;

            buttons[i].addActionListener((e -> {
                if (label.getText().equals(askUserToGuess) ||
                        label.getText().equals(userGuessedIncorrectly) ||
                        label.getText().equals(userGuessedCorrectly)||
                        label.getText().equals("")){
                    label.setText("");
                }
                label.setText(label.getText() + String.valueOf(value));

            }));
        }
        //Adding buttons to clear guess and confirm it
        JButton enter = new JButton("ent");
        JButton clear = new JButton("CLR");
        setClearButton(clear, label);
        setEnterButton(label, enter, random);
        System.out.println(number);

        buttonPanel.add(enter);
        buttonPanel.add(clear);
        gamePanel.add(label);

        this.add(gamePanel, BorderLayout.NORTH);
        this.add(buttonPanel);
        this.revalidate();
        this.repaint();

    }

    /**
     * This method removes panels from the frame.
     * It's main purpose is really just to make switching between screens easier
     * */
    private void clearScreen(){
        this.remove(textPanel);
        this.revalidate();
        this.repaint();
    }

    /**
     * A simple random number generator
     * */
    private int setGuessingNumber(Random random){
        return random.nextInt(0, 11);
    }

    /**
     * Custom made method specifically for the clear button
     * */
    private void setClearButton(JButton clearButton, JLabel label){
        clearButton.setFocusable(false);
        clearButton.addActionListener((e -> {
            label.setText("");
        }));
    }

    /**
     * Custom made button for the enter button that confirms user number
     * This button resets the number so that the user can guess again and sets label back to
     * 0
     * */
    private void setEnterButton(JLabel label, JButton button, Random random){
        button.setFocusable(false);
        button.addActionListener(e -> {

            if (label.getText().equals(askUserToGuess) ||
                    label.getText().equals(userGuessedIncorrectly) ||
                    label.getText().equals(userGuessedCorrectly)||
            label.getText().equals("")){
                label.setText("");
            }

            else if (Integer.parseInt(label.getText()) == number){
                label.setText(userGuessedCorrectly);
                number = setGuessingNumber(random);
                System.out.println(number);
            }

            else {
                label.setText(userGuessedIncorrectly);
            }

        });

    }

}
