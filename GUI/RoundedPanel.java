package GUI;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {
    private int radius;

    public RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false); // 패널을 불투명하지 않게 설정하여 배경이 보이도록 함
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 안티앨리어싱 설정

        // 패널의 크기를 얻어옴
        Dimension size = getSize();
        int width = size.width;
        int height = size.height;

        // 둥근 모서리를 가진 사각형 그리기
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, radius, radius);

        // 필요 시 테두리 추가
        //g2.setColor(getForeground());
        //g2.drawRoundRect(0, 0, width - 1, height - 1, radius, radius);
    }
}
