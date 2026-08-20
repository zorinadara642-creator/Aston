public class Cat extends Animal {
    static int catCount = 0;
    private static final int RUN_LIMIT = 200;
    private boolean satiety = false;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= RUN_LIMIT) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. (лимт " + RUN_LIMIT + " м.)");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public void eat(Bowl bowl, int amount) {
        if (bowl.decreaseFood(amount)) {
            satiety = true;
            System.out.println(name + " поел " + amount + " ед. еды. Сытость: true");
        } else {
            System.out.println(name + " не стал есть - мало еды в миске. Сытость: false");
        }
    }

    public boolean isSatiety() {
        return satiety;
    }
}
