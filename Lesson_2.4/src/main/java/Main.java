public class Main {
    public static void main(String[] args) {
        Dog dogBobik = new Dog("Бобик");
        dogBobik.run(150);
        dogBobik.swim(5);
        dogBobik.swim(15);

        Cat cat1 = new Cat("Мурзик");
        cat1.run(100);
        cat1.run(250);
        cat1.swim(5);

        System.out.println("Животных: " + Animal.animalCount);
        System.out.println("Собак: " + Dog.dogCount);
        System.out.println("Котов " + Cat.catCount);

        Bowl bowl = new Bowl(20);
        Cat[] cats = {
                new Cat("Барсик"),
                new Cat("Васька"),
                new Cat("Рыжик")
        };

        int[] portions = {10, 15, 5};

        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(bowl, portions[i]);
        }

        System.out.println("Остаток еды в миске " + bowl.getFood());

        for (Cat cat : cats) {
            System.out.println(cat.name + " сытость = " + cat.isSatiety());
        }
        bowl.addFood(30);
    }
}
