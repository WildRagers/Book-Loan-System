import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class BookDetailsUI {
    private JFrame frame;
    private JPanel panel;
    private String filePath;

    public BookDetailsUI(String filePath) {
        this.filePath = filePath;

        frame = new JFrame("Loaned Books");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        List<String> bookLines = readAllLines(filePath);

        for (String line : bookLines) {
            String[] details = line.split(", ");
            String bookTitle = details[0];

            JLabel titleLabel = new JLabel(bookTitle);
            JButton detailsButton = new JButton("Details");

            panel.add(titleLabel);
            panel.add(detailsButton);

            detailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showDetailsPopup(details);
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showDetailsPopup(String[] details) {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        detailsPanel.add(new JLabel("<html><b>Title:</b> " + details[0] + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Author:</b> " + details[1] + "</html>"));
        detailsPanel.add(new JLabel("<html><b>ISBN:</b> " + details[2] + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Category:</b> " + details[3] + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Fiction/Non-fiction:</b> " + details[4] + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Date Loaned:</b> " + details[5] + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Loaned to:</b> " + details[6] + "</html>"));
        detailsPanel.add(new JLabel("<html><b>Returned:</b> " + details[7] + "</html>"));

        JButton deleteButton = new JButton("Delete Loan");
        JButton returnButton = new JButton("Return Book");
        JButton reloanButton = new JButton("Relone");
        JButton changeNameButton = new JButton("Change Loaned Name");
        detailsPanel.add(deleteButton);
        detailsPanel.add(returnButton);
        detailsPanel.add(reloanButton);
        detailsPanel.add(changeNameButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLoan(details[0]);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook(details[0]);
            }
        });

        reloanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloneBook(details[0]);
            }
        });

        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLoanedName(details[0]);
            }
        });

        JOptionPane.showMessageDialog(frame, detailsPanel, "Book Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private List<String> readAllLines(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            return List.of();
        }
    }

    private void deleteLoan(String bookTitle) {
        List<String> bookLines = readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : bookLines) {
            if (!line.startsWith(bookTitle)) {
                updatedLines.add(line);
            } else {
                found = true;
            }
        }

        if (found) {
            try {
                Files.write(Paths.get(filePath), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
                JOptionPane.showMessageDialog(frame, "Loan deleted successfully!");
                frame.dispose();
                new BookDetailsUI(filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error deleting loan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook(String bookTitle) {
        List<String> bookLines = readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : bookLines) {
            String[] details = line.split(", ");
            if (details[0].equals(bookTitle) && details[7].equals("no")) {
                line = line.substring(0, line.lastIndexOf(", ")) + ", yes";
                found = true;
            }
            updatedLines.add(line);
        }

        if (found) {
            try {
                Files.write(Paths.get(filePath), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
                JOptionPane.showMessageDialog(frame, "Book returned successfully!");
                frame.dispose();
                new BookDetailsUI(filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error returning book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Book not found or already returned.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reloneBook(String bookTitle) {
        List<String> bookLines = readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : bookLines) {
            String[] details = line.split(", ");
            if (details[0].equals(bookTitle) && details[7].equals("yes")) {
                line = line.substring(0, line.lastIndexOf(", ")) + ", no";
                found = true;
            }
            updatedLines.add(line);
        }

        if (found) {
            try {
                Files.write(Paths.get(filePath), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
                JOptionPane.showMessageDialog(frame, "Book reloaned successfully!");
                frame.dispose();
                new BookDetailsUI(filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error reloaning book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Book not found or already loaned.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeLoanedName(String bookTitle) {
        List<String> bookLines = readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;
    
        for (String line : bookLines) {
            String[] details = line.split(", ");
            if (details[0].equals(bookTitle)) {
                String newName = JOptionPane.showInputDialog(frame, "Enter new loaned name:");
                if (newName != null && !newName.trim().isEmpty()) {
                    details[6] = newName; // Change the loaned to name (field 6)
                    line = String.join(", ", details);
                    found = true;
                }
            }
            updatedLines.add(line);
        }
    
        if (found) {
            try {
                Files.write(Paths.get(filePath), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
                JOptionPane.showMessageDialog(frame, "Loaned name updated successfully!");
                frame.dispose();
                new BookDetailsUI(filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error changing loaned name: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
