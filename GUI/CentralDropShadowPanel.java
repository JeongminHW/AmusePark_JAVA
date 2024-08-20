package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CentralDropShadowPanel extends JPanel {
    private int shadowSize;
    private Color shadowColor;
    private int setRad;

    public CentralDropShadowPanel(int par, Color color) {
        this.shadowSize = par;
        this.shadowColor = color;
        setRad = 40;
        setOpaque(false); // 패널을 투명하게 설정하여 배경을 보이게 함
        setBorder(new EmptyBorder(shadowSize, shadowSize, shadowSize, shadowSize)); // 패딩 설정
    }

    public CentralDropShadowPanel(Color c, int setRad){
        shadowSize = 6;
        this.setRad = setRad;
        this.shadowColor = c;
        setOpaque(false); // 패널을 투명하게 설정하여 배경을 보이게 함
        setBorder(new EmptyBorder(shadowSize, shadowSize, shadowSize, shadowSize)); // 패딩 설정
    }

    @Override
    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g2 = (Graphics2D) g1.create();

        // 안티앨리어싱 활성화
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 섀도우 색상 및 투명도 설정
        g2.setColor(shadowColor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        // 부드러운 섀도우 효과를 위한 블러 적용
        for (int i = shadowSize; i > 0; i--) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) i / (shadowSize * 12)));
            g2.fillRoundRect(shadowSize - i, shadowSize - i, getWidth() - (shadowSize - i) * 2, getHeight() - (shadowSize - i) * 2, setRad+10, setRad+10);
        }


        // 패널 본체 그리기
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.setColor(getBackground());
        g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, setRad, setRad);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += shadowSize * 2;
        size.height += shadowSize * 2;
        return size;
    }
}
