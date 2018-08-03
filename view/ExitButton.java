package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExitButton extends JButton {

    public ExitButton() {
        super("Exit");
        setMinimumSize(new Dimension(90, 30));
        setPreferredSize(new Dimension(90, 30));
        setMaximumSize(new Dimension(90, 30));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
