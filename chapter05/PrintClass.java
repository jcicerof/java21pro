class PrintClass {
    int x = 0;
    int y = 1;

    void printMe() {
        System.out.println("x is " + x + ", y is " + y);
        System.out.println("I am an instance of the class " +
        this.getClass().getName());
    }
}

class PrintSubClass extends PrintClass {
    int z = 3;

    public static void main(String[] arguments) {
        PrintSubClass obj = new PrintSubClass();
        obj.printMe();
    }
}
