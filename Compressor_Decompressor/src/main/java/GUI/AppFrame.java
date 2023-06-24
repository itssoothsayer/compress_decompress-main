package GUI;

import Compression.Compressor;
import Compression.Decompressor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AppFrame extends JFrame implements ActionListener {

    private JButton compressButton;
    private JButton decompressButton;

    public AppFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("File Compression Tool");
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(51, 71, 91));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 71, 91));

        compressButton = new JButton("Select File to Compress");
        compressButton.setBackground(new Color(12, 197, 197, 255));
        compressButton.setForeground(Color.WHITE);
        compressButton.setFocusPainted(false);
        compressButton.addActionListener(this);

        decompressButton = new JButton("Select File to Decompress");
        decompressButton.setBackground(new Color(12, 206, 5, 161));
        decompressButton.setForeground(Color.WHITE);
        decompressButton.setFocusPainted(false);
        decompressButton.addActionListener(this);

        buttonPanel.add(compressButton);
        buttonPanel.add(decompressButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compressButton) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showSaveDialog(this);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    Compressor.method(file);
                    JOptionPane.showMessageDialog(this, "File compressed successfully!", "Compression", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(this, "Error occurred during compression: " + ee.toString(), "Compression Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == decompressButton) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showSaveDialog(this);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    Decompressor.method(file);
                    JOptionPane.showMessageDialog(this, "File decompressed successfully!", "Decompression", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(this, "Error occurred during decompression: " + ee.toString(), "Decompression Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new AppFrame();
        });
    }
}