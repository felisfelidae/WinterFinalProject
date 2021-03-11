package finalprojectwinter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileReadFrame extends JFrame implements ActionListener {
   private JScrollPane scrollPane;       // Container adds scroll bar
   private JTextArea outputArea;         // Holds file output
   private JLabel selectedFileLabel;     // Label for file name
   private JLabel outputLabel;           // Label for file contents
   private JTextField selectedFileField; // Holds name of file
   private JFileChooser fileChooser;     // Enables user to select file
   private JButton openFileButton;       // Trigger file open

   /* Called when openFileButton is pressed. */
   @Override
   public void actionPerformed(ActionEvent event) {
      FileInputStream fileByteStream = null; // File input stream
      Scanner inFS = null;                   // Scanner object
      String readLine;                       // Input from file
      File readFile = null;                  // Input file
      int fileChooserVal;                    // File chooser

      // Open file chooser dialog and get the file to open
      fileChooserVal = fileChooser.showOpenDialog(this);

      // Check if file was selected
      if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
         readFile = fileChooser.getSelectedFile();

         // Update selected file field
         selectedFileField.setText(readFile.getName());

         // Ensure file is valid
         if (readFile.canRead()) {
            try {
               fileByteStream = new FileInputStream(readFile);
               inFS = new Scanner(fileByteStream);

               // Clear output area
               outputArea.setText(""); 

               // Read until end-of-file
               while (inFS.hasNext()) {
                  readLine = inFS.nextLine();
                  outputArea.append(readLine + "\n");
               }

            } catch (IOException e) {
               outputArea.append("\n\nError occurred while creating file stream! " + e.getMessage());
            }
         }
         else { // Can't read file
            // Show failure dialog
            JOptionPane.showMessageDialog(this, "Can't read file!");
         }
      }
   }
}

