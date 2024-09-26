public class Box {
    double height, width, depth;
    Box(double height,double width, double depth)
    {
        this.height= height;                    //instance variable hiding;
        this.width= width;
        this.depth= depth;

    }
    void displayvol()
    {
        double vol= height * width * depth;
        System.out.println(" BoxVolume= "+vol);
        System.out.println("\n");
    }
}
