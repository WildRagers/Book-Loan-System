import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class loanABookUI {
    private JFrame frame;
    private JTextField titleField, authorField, isbnField, categoryField, fictionField, dateLoanField, nameField;
    private JButton submitButton;

    public loanABookUI() {
        frame = new JFrame("Book Loan System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(8, 2, 10, 10));


        frame.add(new JLabel("Book Title:"));
        titleField = new JTextField();
        frame.add(titleField);

        frame.add(new JLabel("Book Author:"));
        authorField = new JTextField();
        frame.add(authorField);

        frame.add(new JLabel("Book ISBN:"));
        isbnField = new JTextField();
        frame.add(isbnField);

        frame.add(new JLabel("Book Category:"));
        categoryField = new JTextField();
        frame.add(categoryField);

        frame.add(new JLabel("Fiction or Non-fiction:"));
        fictionField = new JTextField();
        frame.add(fictionField);

        frame.add(new JLabel("Date Loaned (DD/MM/YY):"));
        dateLoanField = new JTextField();
        frame.add(dateLoanField);

        frame.add(new JLabel("Name of Person Loaning:"));
        nameField = new JTextField();
        frame.add(nameField);

        submitButton = new JButton("Submit");
        frame.add(new JLabel()); 
        frame.add(submitButton);

        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String title = titleField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();
                String category = categoryField.getText();
                String fiction = fictionField.getText();
                String dateLoan = dateLoanField.getText();
                String name = nameField.getText();

            
                bookLoanSys bookLoanSystem = new bookLoanSys();
                bookLoanSystem.loanBook(title, author, isbn, category, fiction, dateLoan, name);

                JOptionPane.showMessageDialog(frame, "Book loaned successfully!");
            }
        });

        frame.setVisible(true);
    }

}
