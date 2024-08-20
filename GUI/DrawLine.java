package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

class DrawLine extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK); // 선 색깔 설정
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); // 패널의 중앙에 수평선 그리기
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(210, 2); // 패널의 크기를 높이 1로 설정
    }
}
