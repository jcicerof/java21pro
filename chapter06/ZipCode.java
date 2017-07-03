public class ZipCode {
    private int zipCode = -1;

    public ZipCode(int inZipCode) {
        setZipCode(inZipCode);
    }

    public void setZipCode(int inZipCode) {
        if ((inZipCode > 9999) & (inZipCode < 100000))
            zipCode = inZipCode;
    }

    public int getZipCode() {
        return zipCode;
    }

    public static void main(String[] arguments) {
        ZipCode zip1 = new ZipCode(75044);
        ZipCode zip2 = new ZipCode(320840);
        if (zip1.getZipCode() != -1)
            System.out.println("Zip1: " + zip1.getZipCode());
        if (zip2.getZipCode() != -1)
            System.out.println("Zip2: " + zip2.getZipCode());
    }
}
