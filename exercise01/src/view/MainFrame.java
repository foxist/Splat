package view;

import model.Data;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    public MainFrame(Data data) {
        super("Поиск файлов");
        setPreferredSize(new Dimension(420, 300));
        setMinimumSize(new Dimension(420, 300));
        setMaximumSize(new Dimension(420, 300));
        getMainFrame(data);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void getMainFrame(Data data) {
        JPanel first = new JPanel();
        first.setLayout(new GridLayout(2, 1));
        JTextField folderField = new JTextField(35);
        JPanel folderPanel = new JPanel();
        folderPanel.setLayout(new GridBagLayout());
        folderPanel.setBorder(BorderFactory.createTitledBorder("Укажите путь к папке/файлу"));
        folderPanel.add(folderField, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                new Insets(0,0,0,0), 0, 0));

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new GridBagLayout());
        secondPanel.setBorder(BorderFactory.createTitledBorder("Введите расширение файла: .txt"));
        JTextField expansion = new JTextField(35);
        expansion.setText(".log");
        secondPanel.add(expansion, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0,0,0,0), 0, 0));

        first.add(folderPanel);
        first.add(secondPanel);

        JPanel thirdPanel = new JPanel();
        JTextArea text = new JTextArea(6, 35);
        thirdPanel.setBorder(BorderFactory.createTitledBorder("Введите искомый текст"));
        thirdPanel.add(new JScrollPane(text), new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0,0,0,0), 0, 0));

        JPanel fourthPanel = new JPanel();
        SearchButton searchButton = new SearchButton();
        fourthPanel.setLayout(new GridBagLayout());
        fourthPanel.add(searchButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                new Insets(5,10,5,30), 0, 0));
        fourthPanel.add(new ExitButton(), new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(5,30,5,10), 0, 0));

       setLayout(new BorderLayout());
       add(first, BorderLayout.NORTH);
       add(thirdPanel, BorderLayout.CENTER);
       add(fourthPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.setTop(new DefaultMutableTreeNode("Main Folder"));
                data.setSearchText(text.getText());
                data.setFilePath(folderField.getText());
                if (data.getFilePath().charAt(folderField.getText().length() - 1) != '\\') {
                    data.setFilePath(folderField.getText() + "\\");
                }
                data.setExpansion(expansion.getText());
                if (data.findFiles(data.getFilePath(), data.getExpansion()))
                    new FolderFrame(data).setVisible(true);
            }
        });
    }
}
