import java.awt.*;

public class Map1 extends java.applet.Applet {
    public void paint(Graphics screen) {
        setBackground(Color.blue);
        // Draw waves
        screen.setColor(Color.white);
        for (int ax = 10; ax < 340; ax += 10)
            for (int ay = 30; ay < 340 ; ay += 10) {
                screen.drawArc(ax, ay, 10, 10, 0, -180);
            }
        // Draw Florida
        screen.setColor(Color.green);
        Polygon fl = new Polygon();
        fl.addPoint(10, 12);
        fl.addPoint(234, 15);
        fl.addPoint(253, 25);
        fl.addPoint(261, 71);
        fl.addPoint(344, 209);
        fl.addPoint(336, 278);
        fl.addPoint(295, 310);
        fl.addPoint(259, 274);
        fl.addPoint(205, 188);
        fl.addPoint(211, 171);
        fl.addPoint(195, 174);
        fl.addPoint(191, 118);
        fl.addPoint(120, 56);
        fl.addPoint(94, 68);
        fl.addPoint(81, 49);
        fl.addPoint(12, 37);
        screen.fillPolygon(fl);
        // Draw ovals
        screen.setColor(Color.black);
        screen.fillOval(235, 140, 15, 15);
        screen.fillOval(225, 130, 15, 15);
        screen.fillOval(245, 130, 15, 15);
    }
}
