public class HelloUser {
    public static void main(String[] arguments) {
        String username = System.getProperty("user.name");
        System.out.println("Hello, " + username);
    }
}
