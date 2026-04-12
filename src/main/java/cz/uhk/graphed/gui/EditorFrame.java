package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.*;
import cz.uhk.graphed.model.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.function.Supplier;

public class EditorFrame extends JFrame {

    private final Random random = new Random();
    private Canvas canvas = new Canvas();
    private JToggleButton selectButton;

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

        selectButton = new JToggleButton("Výběr");
        selectButton.addActionListener(e -> canvas.setSelectMode(selectButton.isSelected()));
        toolBar.add(selectButton);
        toolBar.addSeparator();

        toolBar.add(createAction("Square", () -> {
            int a = 20 + random.nextInt(30);
            return new Square(randomPos(a, a), randomColor(), a);
        }));
        toolBar.add(createAction("Circle", () -> {
            int r = 20 + random.nextInt(30);
            return new Circle(randomPos(2 * r, 2 * r), randomColor(), r);
        }));
        toolBar.add(createAction("Rectangle", () -> {
            int a = 20 + random.nextInt(30);
            int b = 20 + random.nextInt(30);
            return new Rectangle(randomPos(a, b), randomColor(), a, b);
        }));
        toolBar.add(createAction("Triangle", () -> {
            int a = 20 + random.nextInt(30);
            int h = (int) Math.round(a * Math.sin(Math.toRadians(60)));
            Point pos = new Point(
                    random.nextInt(Math.max(1, canvas.width - a)),
                    h + random.nextInt(Math.max(1, canvas.height - h)));
            return new Triangle(pos, randomColor(), a);
        }));

        return toolBar;
    }

    private AbstractAction createAction(String name, Supplier<AbstractGraphicObject> factory) {
        return new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.add(factory.get());
                canvas.repaint();
            }
        };
    }

    private Color randomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private Point randomPos(int drawnWidth, int drawnHeight) {
        return new Point(
                random.nextInt(Math.max(1, canvas.width - drawnWidth)),
                random.nextInt(Math.max(1, canvas.height - drawnHeight)));
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
