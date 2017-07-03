import java.awt.Point;
 
class FourDPoint extends Point {
    int z;
    int t;

    FourDPoint(int x, int y, int inZ, int inT) {
        super(x,y);
        this.z = inZ;
        this.t = inT;
    }

    public static void main(String[] arguments) {
        FourDPoint np = new FourDPoint(5, 5, 4, 100);
        System.out.println("x is " + np.x);
        System.out.println("y is " + np.y);
        System.out.println("z is " + np.z);
        System.out.println("t is " + np.t);
    }
}
