public interface Figure {

    double getArea();

    default void printInfo() {
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());
    }

    double getPerimeter();

    String getFillColor();

    String getBorderColor();
}
