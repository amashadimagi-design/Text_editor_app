import javax.swing.*;
import java.awt.*;
import java.io.*;  // Needed for File, FileReader, BufferedReader, FileWriter, BufferedWriter

public class Notepad extends JFrame {

    JTextArea textArea; // Text editing area

    public Notepad() {
        // Set the title of the window
        setTitle("My Notepad");

        // Set window size
        setSize(600, 500);

        // Close the program when the window is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the text area
        textArea = new JTextArea();

        // Add scroll functionality
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // --- Start File Menu ---
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // line before Exit
        fileMenu.add(exitItem);

        // --- Add action listeners here ---
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    textArea.read(reader, null);
                    reader.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file");
                }
            }
        });

        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    textArea.write(writer);
                    writer.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file");
                }
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        menuBar.add(fileMenu);

        // --- Start Edit Menu ---
        JMenu editMenu = new JMenu("Edit");

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

// Add action listeners
        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        menuBar.add(editMenu);
// --- End Edit Menu ---

        // --- Start Help Menu ---
        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");

        helpMenu.add(aboutItem);

// Action listener
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "My Notepad\nName: Amasha Ranathunga\nIndex: s16477", "About", JOptionPane.INFORMATION_MESSAGE);
        });

// Add Help menu to menu bar
        menuBar.add(helpMenu);
// --- End Help Menu ---
        
        setJMenuBar(menuBar);
        // --- End File Menu ---

        // Make the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new Notepad(); // Launch the Notepad
    }
}

