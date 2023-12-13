package radioController;

import javax.swing.*;
import java.awt.*;

public class ChannelListCellRenderer implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object selectedObject, int index, boolean isSelected, boolean cellHasFocus) {
            if (selectedObject instanceof JPanel)
            {
                Component component = (Component) selectedObject;
                component.setForeground (Color.white);
                //Changes the color of the selected object depending on if it's selected or not
                component.setBackground (isSelected ? Color.gray : Color.white);
                return component;
            }
            else
            {

                return new JLabel("???");
            }
        }
    }
