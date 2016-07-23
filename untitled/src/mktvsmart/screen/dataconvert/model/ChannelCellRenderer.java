package mktvsmart.screen.dataconvert.model;

import javax.swing.*;
import java.awt.*;

public class ChannelCellRenderer implements ListCellRenderer
{

    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);


        return renderer;
    }
}
