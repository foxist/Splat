package view;

import io.FileText;
import model.Data;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.tree.TreePath;
import java.awt.*;

public class FolderFrame extends JFrame {
    private Data data;

    public FolderFrame(Data data) {
        super("TreeFolder");
        setPreferredSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(600, 400));
        setLayout(new GridLayout(0, 2));
        this.data = data;
        add(getLeftPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public Data getData() {
        return data;
    }

    public JPanel getLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        JTree tree = new JTree(getData().getTop());
        JScrollPane treeView = new JScrollPane(tree);
        leftPanel.add(treeView, BorderLayout.CENTER);
        tree.addTreeSelectionListener(new SelectionListener());
        return leftPanel;
    }

    public JPanel getRightPanel(String textFile) {
        try {
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BorderLayout());
            JTextPane text = new JTextPane();
            text.setText(new FileText().isFindFile(textFile));

            int index = text.getText().indexOf(getData().getSearchText());
            text.getHighlighter().addHighlight(index, index + getData().getSearchText().length(), new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN));
            int lastIndex = text.getText().lastIndexOf(getData().getSearchText());
            text.getHighlighter().addHighlight(lastIndex, lastIndex + getData().getSearchText().length(), new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN));

            JScrollPane scrollPane = new JScrollPane(text);
            rightPanel.add(scrollPane, BorderLayout.CENTER);
            return rightPanel;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Искомого текста необнаружено!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    class SelectionListener implements TreeSelectionListener {

        public void valueChanged(TreeSelectionEvent e) {
            JTree tree = (JTree) e.getSource();
            TreePath[] selected = tree.getSelectionPaths();
            int[] rows = tree.getSelectionRows();

            assert selected != null;
            for (TreePath aSelected : selected) {
                assert rows != null;
                if (getRightPanel(aSelected.getPathComponent(1) + "\\" + aSelected.getLastPathComponent()) != null) {
                    add(getRightPanel(aSelected.getPathComponent(1) + "\\" + aSelected.getLastPathComponent()));
                    revalidate();
                }
            }
        }
    }
}
