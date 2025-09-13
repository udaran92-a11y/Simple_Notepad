import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SimpleNotepad extends JFrame {

    private final JTextArea textArea;

    public SimpleNotepad() {

        setTitle("Simple Notepad");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);


        JMenuBar menuBar = new JMenuBar();


        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> openFile());

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> saveFile());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);


        JMenu editMenu = new JMenu("Edit");

        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.addActionListener(e -> textArea.cut());

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(e -> textArea.copy());

        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.addActionListener(e -> textArea.paste());

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        menuBar.add(editMenu);


        JMenu formatMenu = new JMenu("Format");

        JMenuItem fontItem = new JMenuItem("Change Font");
        fontItem.addActionListener(e -> {
            String fontName = JOptionPane.showInputDialog(
                    this,
                    "Enter font name (e.g., Arial, Times New Roman):",
                    "Change Font",
                    JOptionPane.PLAIN_MESSAGE
            );
            if (fontName != null && !fontName.trim().isEmpty()) {
                textArea.setFont(new Font(fontName, Font.PLAIN, 16));
            }
        });

        JMenuItem colorItem = new JMenuItem("Change Color");
        colorItem.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Text Color", Color.BLACK);
            if (color != null) {
                textArea.setForeground(color);
            }
        });

        formatMenu.add(fontItem);
        formatMenu.add(colorItem);
        menuBar.add(formatMenu);


        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Advanced Notepad\nName:Udara \nID:s16649",
                "About",
                JOptionPane.INFORMATION_MESSAGE
        ));

        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);


        setJMenuBar(menuBar);
    }


    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleNotepad().setVisible(true));
    }
}
