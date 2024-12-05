import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainUI {
    private JFrame frame;
    private JButton loanButton;
    private JButton detailsButton;

    public mainUI() {
        frame = new JFrame("Book Loan System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        loanButton = new JButton("Create new Loan");
        loanButton.setBounds(50, 50, 200, 30);
        frame.add(loanButton);

        detailsButton = new JButton("View Loan Details");
        detailsButton.setBounds(50, 100, 200, 30);
        frame.add(detailsButton);

        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loanABookUI();
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookDetailsUI("loanedbooks.txt");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new mainUI();
    }
}
