class SeeLibraryPath {
    public static void main(String[] arguments) {
        String n = System.getProperty("java.library.path");
        System.out.println("java.library.path: " + n);
    }
}
