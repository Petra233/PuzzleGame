import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JavaGame extends JFrame implements ActionListener {

    JPanel panelButtons = new JPanel();
    private int emptyButton;

    JPanel newGame = new JPanel();
    JButton newGameButton = new JButton("New Game!");

    private final ArrayList<JButton> buttons = new ArrayList<>();

    public JavaGame() {
        addButtons();
        setLayoutNewGameButton();
        addButtonsToPanel();
        findEmptyButton();
        layoutGame();
        mixButtons();

    }

    private void addButtons() {
        for (int i = 0; i < 16; i++) {
            JButton button = new JButton(i == 15 ? " " : String.valueOf(i + 1));
            buttons.add(button);
        }
    
        }

        private void setLayoutNewGameButton() {
            newGame.setLayout(new FlowLayout());
            newGame.add(newGameButton);
            newGameButton.addActionListener(this);
        }



        private void addButtonsToPanel() {
        panelButtons.setLayout(new GridLayout(4, 4));

        for (JButton button : buttons) { // lägger alla knappar på panelen
            panelButtons.add(button);
            button.addActionListener(this);
        }
    }

        private void findEmptyButton() {
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getText().equals(" ")) {
                    emptyButton = i;
                    break;
                }
            }
        }

        private void layoutGame() {

            setLayout(new BorderLayout());
            add("North", newGame);
            add("Center", panelButtons);

            setSize(300, 200);
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }


   private void mixButtons() {
        for (int i = 0; i < 100; i++) {
            int random = (int) (Math.random() * 16);
            swapPosition(random);
        }
    }

    public boolean checkPosition(int position) {

        if(position < 0 || position >= 16) return false;

        if(position + 4 == emptyButton) return true;

        if(position - 4 == emptyButton) return true;

        if(position - 1 == emptyButton && position % 4 != 0) return true;

        return position + 1 == emptyButton && (position + 1) % 4 != 0;
    }



    private void swapPosition(int position) {
        if(checkPosition(position)) {
            JButton movedButton = buttons.get(position);
            JButton emptyBtn = buttons.get(emptyButton);

            String tempText = movedButton.getText();
            movedButton.setText(emptyBtn.getText());
            emptyBtn.setText(tempText);

            emptyButton = position;

            checkWin();

        }
    }


    private void checkWin() {
        boolean won = true;

        for (int i = 0; i < 14; i++) {
            if(!buttons.get(i).getText().equals(String.valueOf(i +1))) { //
                won = false;
                break;
            }
        }
        if(!buttons.get(15).getText().equals(" ")){
            won = false;
        }
        if(won) {
            JOptionPane.showMessageDialog(this,"Congrats, you won!");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGameButton) {
            mixButtons();
            return;
        }
        for (int i = 0; i < 16; i++) {
            if(e.getSource() == buttons.get(i)) {
                swapPosition(i);
                break;
            }
        }
    }
    public static void main(String[] args) {
        new JavaGame();
    }
}
