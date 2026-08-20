public class Animal {
    String name;
    static int animalCount = 0;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public void run(int distance) {
        System.out.println(name + " пробежал" + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл" + distance + " м.");
    }
}
