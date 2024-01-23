import java.util.*;
import java.io.BufferedOutputStream;

public class Stage {
    private Camera c;
    BufferedOutputStream outputStream = new BufferedOutputStream(System.out);
    printlnColor coloredPrinter = new printlnColor(outputStream);
    private Camera scanY;
    private double minValue = 0;
    private double maxValue;
    private Camera scanX;
    double toRad = Math.PI / 180;
    private Vertex cuVertex;
    private double fovVal;
    private double fovOverRow;
    private double fovOverCollum;
    List<List<Character>> outputList;
    List<List<Integer>> outputColorList;
    List<Character> outputRow;
    List<Integer> outputColorRow;
    private ArrayList<Character> charMap;
    boolean update = true;
    private ArrayList<Triangle> tris;

    public Stage(Camera c, double fov)
    {
        tris = new ArrayList<Triangle>();
        this.c = c;
        charMap = new ArrayList<>(Arrays.asList(',', '.', '-', ',', '_', '^', '=', ';', '>', '<', '+', '!', 'r', 'c', '*', '/', 'z', '?', 's', 'L', 'T', 'v', ')', 'J', '7', '(', '|', 'F', 'i', '{', 'C', '}', 'f', 'I', '3', '1', 't', 'l', 'u', '[', 'n', 'e', 'o', 'Z', '5', 'Y', 'x', 'j', 'y', 'a', ']', '2', 'E', 'S', 'w', 'q', 'k', 'P', '6', 'h', '9', 'd', '4', 'V', 'p', 'O', 'G', 'b', 'U', 'A', 'K', 'X', 'H', 'm', '8', 'R', 'D', '#', '$', 'B', 'g', '0', 'M', 'N', 'W', 'Q', '%', '&', '@'));
        maxValue =  (fov*fov)/3.2;
        scanY = new Camera(c.getPosition(),1, fov);
        scanX = new Camera(c.getPosition(),1, fov);
        fovVal = ((fov - (fov / 2)) * -1);
        fovOverRow = (fov / c.getResolution().get(1));
        fovOverCollum = (fov / c.getResolution().get(0));
    }

    public void appendTri(Triangle t)
    {
        tris.add(t);
    }

    public void setTris(ArrayList<Triangle> t)
    {
        tris = t;
    }

    public ArrayList<Triangle> getTris()
    {
        return tris;
    }

    private boolean isPointInsideTriangle(Vertex p, Vertex v1, Vertex v2, Vertex v3) {
        Vertex e1 = v2.subtract(v1);
        Vertex e2 = v3.subtract(v1);
        
        double dot00 = e1.dotProduct(e1);
        double dot01 = e1.dotProduct(e2);
        double dot02 = e1.dotProduct(p.subtract(v1));
        double dot11 = e2.dotProduct(e2);
        double dot12 = e2.dotProduct(p.subtract(v1));
    
        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
    
        return u >= 0 && v >= 0 && (u + v) <= 1;
    }
       

    public Vertex lineTriangleIntersection(Vertex p1, Vertex p2) {
        Vertex best = null;
        for(int i = 0; i < tris.size(); i++)
        {
            Triangle tri = tris.get(i);
            Vertex v1 = tri.getV1();
            Vertex v2 = tri.getV2();
            Vertex v3 = tri.getV3();

            Vertex dirLine = p2.subtract(p1);

            Vertex e1 = v2.subtract(v1);
            Vertex e2 = v3.subtract(v1);

            Vertex normal = e1.crossProduct(e2);

            double t = v1.subtract(p1).dotProduct(normal) / dirLine.dotProduct(normal);

            Vertex intersectionPoint = p1.add(dirLine.multiply(t));

            Vertex check = isPointInsideTriangle(intersectionPoint, v1, v2, v3) ? intersectionPoint : null;


            if(best == null && check != null || check != null && c.calculateOriginDistance(check) >  c.calculateOriginDistance(best))
            {
                best = check;
                best.setColor(tri.getColor());
            }
        }
        return best;
    }

    private void rotateStageZ(int zPrime) {
        double roll = Math.toRadians(zPrime - c.getZRotation());
        double cosRoll = Math.cos(roll);
        double sinRoll = Math.sin(roll);
    
        for (Triangle triangle : tris) {
            for (Vertex v : triangle.getVertices()) {
                double oldX = v.getX();
                v.setX(oldX * cosRoll - v.getZ() * sinRoll);
                v.setZ(v.getZ() * cosRoll + oldX * sinRoll);
            }
        }
        c.setZRotation(zPrime);
    }
    
    private void rotateStageY(int yPrime) {
        double pitch = Math.toRadians(yPrime - c.getYRotation());
        double cosPitch = Math.cos(pitch);
        double sinPitch = Math.sin(pitch);
    
        for (Triangle triangle : tris) {
            for (Vertex v : triangle.getVertices()) {
                double oldY = v.getY();
                v.setY(oldY * cosPitch - v.getZ() * sinPitch);
                v.setZ(v.getZ() * cosPitch + oldY * sinPitch);
            }
        }
        c.setYRotation(yPrime);
    }
    
    private void rotateStageX(int xPrime) {
        double yaw = Math.toRadians(xPrime - c.getXRotation());
        double cosYaw = Math.cos(yaw);
        double sinYaw = Math.sin(yaw);
    
        for (Triangle triangle : tris) {
            for (Vertex v : triangle.getVertices()) {
                double oldX = v.getX();
                v.setX(oldX * cosYaw - v.getY() * sinYaw);
                v.setY(v.getY() * cosYaw + oldX * sinYaw);
            }
        }
        c.setXRotation(xPrime);
    }
    

    public void updateStage(Stage stage, int xPrime, int yPrime, int zPrime) {
        if (xPrime != 0)
        {
            rotateStageX(xPrime);
        }
        if (yPrime != 0)
        {
            rotateStageY(yPrime);
        }
        if (zPrime != 0)
        {
            rotateStageZ(zPrime);
        }
        double range = (double) maxValue - minValue;
        System.out.print("\033[H\033[2J");
        Vertex Origin = c.getOrigin();
        Vertex cPos = c.getPosition();
        outputList = new ArrayList<>();
        outputColorList = new ArrayList<>();
        outputRow = new ArrayList<>();
        outputColorRow = new ArrayList<>();
        double rowRange = c.getResolution().get(1);
        double colRange = c.getResolution().get(0);

    
        for (int row = 0; row < rowRange; row++) {

            outputRow = new ArrayList<>();
            outputColorRow = new ArrayList<>();
            outputList.add(outputRow);
            outputColorList.add(outputColorRow);
    
            scanY.setPos(cPos.rotateAboutY(Origin,((fovOverRow * row) + fovVal) * toRad));
    
            for (int column = 0; column < colRange; column++) {

                scanX.setPos(scanY.getPosition().rotateAboutX(Origin, (((fovOverCollum * column) + fovVal)*toRad)));
                cuVertex = stage.lineTriangleIntersection(scanX.extendLine(), scanX.getPosition());
    
                if (cuVertex != null) {
                    outputRow.add(charMap.get((int) Math.round((maxValue - c.calculateOriginDistance(cuVertex) - minValue) * (charMap.size() - 1) / range)));
                    outputColorRow.add(cuVertex.getColor());
                } else {
                    outputRow.add(' ');
                    outputColorRow.add(31); // Default color when cuVertex is null
                }
            }
        }
        // Batch print the output
        coloredPrinter.printBatch(outputList, outputColorList);
    }
    
    public void setCamera(Camera c)
    {
        this.c = c;
    }

    public Camera getCamera()
    {
        return c;
    }
}
