public class Product {
    String name;
    String productionDate;
    String manufacturer;
    String country;
    int price;
    boolean isReserved;

    public Product(String name, String productionDate, String manufacturer, String country, int price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + country);
        System.out.println("Цена: " + price);
        System.out.println("Забронирован: " + (isReserved ? "да" : "нет"));
        System.out.println();
    }
}
