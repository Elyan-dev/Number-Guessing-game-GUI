import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Window extends JFrame {
    //Panel needed
    JPanel textPanel = new JPanel();


    //For random number
    int number;
    Random random = new Random();

    //to make multiple number
    boolean typable = false;

    public Window(){

        showScreen();

        //GameLoop






        this.pack();
        this.setVisible(true);
    }


    private void showScreen(){
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


    private void startGame(){
        clearScreen(textPanel);
        JPanel gamePanel = new JPanel();
        JPanel buttonPanel = new JPanel();


        buttonPanel.setLayout(new GridLayout(4, 3));

        //Title
        String labelValue = "Guess number from 1-10";
        JLabel label = new JLabel();
        label.setText(labelValue);

        JButton[] buttons = new JButton[10];


        //Making number buttons and their functions
        for (int i=0; i<buttons.length; i++ ){
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].setFocusable(false);
            buttonPanel.add(buttons[i]);

            final int value = i;

            buttons[i].addActionListener((e -> {
                if (label.getText().equals(labelValue)){
                    label.setText("");
                }

                label.setText(label.getText() + String.valueOf(value));

            }));
        }

        //Adding special buttons

        JButton enter = new JButton("ent");
        JButton clear = new JButton("CLR");
        setClearButton(clear, label);
        setEnterButton(label, enter, number);
        System.out.println(number);

        buttonPanel.add(enter);
        buttonPanel.add(clear);



        gamePanel.add(label);
        this.add(gamePanel, BorderLayout.NORTH);
        this.add(buttonPanel);
        this.revalidate();
        this.repaint();

    }


    //To swing from start screen to game
    private void clearScreen(JPanel panel){
        this.remove(textPanel);

        this.revalidate();
        this.repaint();
    }

    //For deciding what the random number is

    private int setGuessingNumber(Random random){

        random = new Random();
        return random.nextInt(0, 10);

    }

    //Methods to make special buttons clear and enter choice
    private void setClearButton(JButton clearButton, JLabel label){
        clearButton.setFocusable(false);

        clearButton.addActionListener((e -> {
            label.setText("");
        }));
    }

    private void setEnterButton(JLabel label, JButton button, int number){

        button.setFocusable(false);

        button.addActionListener(e -> {

            try {
                if (Integer.parseInt(label.getText()) == number){
                    System.out.println("you got it.");
                }else {
                    System.out.println("Incorrect");
                }
            } catch (NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

}
