package cmp.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CentralDropShadowArea extends JTextArea {
    private int shadowSize;
    private Color shadowColor;

    public CentralDropShadowArea(int shadowSize, Color shadowColor) {
        this.shadowSize = shadowSize;
        this.shadowColor = shadowColor;
        setOpaque(false); // 텍스트 필드를 투명하게 설정하여 배경을 보이게 함
        setBorder(new EmptyBorder(shadowSize, shadowSize, shadowSize, shadowSize)); // 패딩 설정
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 안티앨리어싱 활성화
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 섀도우 색상 및 투명도 설정
        g2.setColor(shadowColor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        
        // 부드러운 섀도우 효과를 위한 블러 적용
        // for (int i = shadowSize; i > 0; i--) {
        //     int alpha = (int) (255 * ((float) i / (shadowSize * 2))); // 섀도우의 투명도를 점진적으로 증가
        //     g2.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), alpha));
        //     g2.fillRoundRect(
        //         shadowSize - i, 
        //         shadowSize - i, 
        //         getWidth() - (shadowSize - i) * 2, 
        //         getHeight() - (shadowSize - i) * 2, 
        //         15, 15
        //     );
        // }

        for (int i = shadowSize; i > 0; i--) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) i / (shadowSize * 12)));
            g2.fillRoundRect(shadowSize - i, shadowSize - i, getWidth() - (shadowSize - i) * 2, getHeight() - (shadowSize - i) * 2, 20, 20);
        }

        // 텍스트 필드 본체 그리기
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.setColor(getBackground());
        g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, 15, 15);

        g2.dispose();

        // 텍스트와 커서를 그리기 위해 원래의 컴포넌트 페인팅을 호출
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += shadowSize * 2;
        size.height += shadowSize * 2;
        return size;
    }
}
