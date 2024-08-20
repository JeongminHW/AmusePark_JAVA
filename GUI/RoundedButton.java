package GUI;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RoundedButton extends JButton{
    int setRad;
    public RoundedButton(ImageIcon leftIcon, int setRad) {
        super(leftIcon);
        this.setRad = setRad;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }
    
    public RoundedButton(String label, int setRad) {
        super(label);
        this.setRad = setRad;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 버튼의 배경 그리기
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), setRad, setRad));

        // 텍스트 그리기
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        Rectangle textRect = new Rectangle(0, 0, getWidth(), getHeight());
        int textHeight = fm.getAscent();
        int textWidth = fm.stringWidth(getText());
        int x = (textRect.width - textWidth) / 2;
        int y = (textRect.height - textHeight) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}
