package com.example.java_.solid.lsp.t2;

public class Run {
    public static void main(String[] args) {
        Run run = new Run();

        Shape circle = new Circle();
        run.drawShape(circle);

        Shape rectangle = new Rectangle();
        run.drawShape(rectangle);
    }

    void drawShape(Shape shape){
        shape.draw();
    }
}