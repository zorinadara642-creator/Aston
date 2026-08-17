public class Park {
    public class Attraction {
        String name;
        String workingHours;
        int price;

        public Attraction(String name, String workingHours, int price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Атракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price + "рублей");
            System.out.println();
        }
    }
}
