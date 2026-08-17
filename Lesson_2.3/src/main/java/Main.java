public class Main {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];

        productsArray [0] = new Product("Samsun s25",
                "01.02.2025",
                "Samsung Corp",
                "Korea",
                5599,
                true);

        productsArray [1] = new Product("iphone 16 Pro",
                "15.09.2024",
                "Apple Inc.",
                "USA",
                88500,
                false);

        productsArray [2] = new Product("Xiaomi 14",
                "10.11.2024",
                "Xiaomi",
                "China",
                3499,
                true);

        productsArray [3] = new Product("Sony 3000",
                "24.05.2023",
                "Sony",
                "Japan",
                12999,
                false);

        productsArray [4] = new Product("Lenovo 2500",
                "03.03.2024",
                "Lenovo",
                "China",
                2500,
                true);

        for (int i = 0; i < productsArray.length; i++) {
            productsArray[i].printInfo();
        }
    }
}
