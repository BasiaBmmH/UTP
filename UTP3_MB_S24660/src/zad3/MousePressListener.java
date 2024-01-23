package zad3;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePressListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("ok");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // No action needed here
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // No action needed here
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No action needed here
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No action needed here
    }
}