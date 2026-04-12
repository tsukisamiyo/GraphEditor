package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.*;
import cz.uhk.graphed.model.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class EditorFrame extends JFrame {

    private final Random random = new Random();

    private Canvas canvas = new Canvas();

    public EditorFrame() throws HeadlessException {
        super("FIM Graphic Editor");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(canvas, BorderLayout.CENTER);
        add(createToolbar(), BorderLayout.NORTH);
        initSampleData();
        pack();
    }

    private JToolBar createToolbar() {
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);

        Action square = createAction("Square", Square.class);
        toolBar.add(square);
        Action circle =  createAction("Circle", Circle.class);
        toolBar.add(circle);
        Action rectangle = createAction("Rectangle", Rectangle.class);
        toolBar.add(rectangle);
        Action triangle = createAction("Triangle", Triangle.class);
        toolBar.add(triangle);

        return toolBar;
    }

    private AbstractAction createAction(String name, Class<? extends AbstractGraphicObject> c) {
        return new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = 20 + random.nextInt(30);

                    Point pos = new Point(
                            random.nextInt(canvas.width - size),
                            random.nextInt(canvas.height - size));

                    int r = random.nextInt(255),
                            g = random.nextInt(255),
                            b = random.nextInt(255);

                    canvas.add(c.getDeclaredConstructor(Point.class, Color.class, int.class)
                            .newInstance(pos, new Color(r, g, b), size));
                    canvas.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    private void initSampleData() {
        canvas.add(new Square(new Point(100, 100), Color.BLACK, 50));
        canvas.add(new Square(new Point(100, 200), Color.RED, 50));
        canvas.add(new Rectangle(new Point(200, 100), Color.GREEN, 50, 100));
        canvas.add(new Circle(new Point(300, 200), Color.BLUE, 50));
        canvas.add(new Triangle(new Point(500, 500), Color.CYAN, 50));

        GraphicGroup graphicGroup = new GraphicGroup();
        canvas.add(graphicGroup);
        graphicGroup.add(new Square(new Point(400, 400), Color.BLACK, 50));
        graphicGroup.add(new Circle(new Point(450, 375), Color.BLACK, 50));

    }
}
