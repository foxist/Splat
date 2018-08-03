package model;

import java.io.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Data {
    private DefaultMutableTreeNode top;
    private String searchText, filePath, expansion;

    public String getExpansion() {
        return expansion;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public boolean findFiles(String folder, String ext) {
        File dir;
        final String[] string = ext.split(",");

        StringBuilder stringBuilder = new StringBuilder();
        for (char itr : folder.toCharArray()) {
            stringBuilder.append(itr);
            if (itr == '\\') {
                dir = new File(stringBuilder.toString());
                    if (!dir.exists()) {
                        JOptionPane.showMessageDialog(null, dir + " Папка не существует!\n Проверьте правильность введенных данных!",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }

                File[] folderEntries = dir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        for (String str : string)
                            if (name.toLowerCase().endsWith(str))
                                return true;
                        return false;
                    }
                });

                assert folderEntries != null;
                if (folderEntries.length == 0)
                    System.out.println(dir + " не содержит файлов с расширением " + ext);

                for (File entry : folderEntries) {
                    if (entry.isDirectory()) {
                        findFiles(entry.getPath(), ext);
                    } else {
                        getTreeTop(entry);
                    }
                }
            }
        }
        return true;
    }

    public void setTop(DefaultMutableTreeNode treeNode) {
        this.top = treeNode;
    }

    public DefaultMutableTreeNode getTop() {
        return this.top;
    }

    private void getTreeTop(File file) {
        DefaultMutableTreeNode path;
        if (top.getChildCount() == 0)
            path = new DefaultMutableTreeNode(file.getParent());
        else {
            if (top.getLastChild().toString().equals(file.getParent()))
                path = (DefaultMutableTreeNode) top.getLastChild();
            else
                path = new DefaultMutableTreeNode(file.getParent());
        }

        DefaultMutableTreeNode fileName = new DefaultMutableTreeNode(file.getName());
        path.add(fileName);
        top.add(path);
    }
}
