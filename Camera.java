import java.util.*;

public class Camera {
    private Vertex Pos;
    private Vertex Origin;
    private double zoom;
    private int xRotation;
    private int yRotation;
    private int zRotation;
    private ArrayList<Integer> resolution;    


    public Camera(Vertex Pos, int w, double z)
    {
        this.Pos = Pos;
        double d = calculateDistance(new Vertex(0,0,0));
        Origin = pointOnLineAtDistance(Pos, new Vertex(0,0,0), (d/21)*z + (d/21));
        resolution = new ArrayList<Integer>(Arrays.asList(230*w, 82*w));
        zoom = z;
    }

    public Vertex pointOnLineAtDistance(Vertex A, Vertex B, double distanceFromA) {
        // Calculate the distance between A and B
        double distanceAB = Math.sqrt(Math.pow(B.getX() - A.getX(), 2)
                + Math.pow(B.getY() - A.getY(), 2)
                + Math.pow(B.getZ() - A.getZ(), 2));

        // Calculate the parameter 't' for the parametric equation
        double t = distanceFromA / distanceAB;

        // Calculate the coordinates of the point P using the parametric equation
        double xP = A.getX() + t * (B.getX() - A.getX());
        double yP = A.getY() + t * (B.getY() - A.getY());
        double zP = A.getZ() + t * (B.getZ() - A.getZ());

        return new Vertex(xP, yP, zP);
    }

    public double calculateDistance(Vertex p) {
        // Using the 3D Euclidean distance formula: distance = sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)
        double deltaX = p.getX() - Pos.getX();
        double deltaY = p.getY() - Pos.getY();
        double deltaZ = p.getZ() - Pos.getZ();

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        return distance;
    }

    public double calculateOriginDistance(Vertex p) {
        // Using the 3D Euclidean distance formula: distance = sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)
        double deltaX = p.getX() - Origin.getX();
        double deltaY = p.getY() - Origin.getY();
        double deltaZ = p.getZ() - Origin.getZ();

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        return distance;
    }

    public  Vertex extendLine() {

        Vertex vectorOP = Origin.subtract(Pos);

        double magnitudeOP = Math.sqrt(vectorOP.dotProduct(vectorOP));

        Vertex normalizedVector = new Vertex(
                vectorOP.getX() / magnitudeOP,
                vectorOP.getY() / magnitudeOP,
                vectorOP.getZ() / magnitudeOP
        );

        double scalingFactor = 1733.0; 

        normalizedVector = new Vertex(
                scalingFactor * normalizedVector.getX(),
                scalingFactor * normalizedVector.getY(),
                scalingFactor * normalizedVector.getZ()
        );

        normalizedVector = new Vertex(
                Pos.getX() + normalizedVector.getX(),
                Pos.getY() + normalizedVector.getY(),
                Pos.getZ() + normalizedVector.getZ()
        );

        return normalizedVector;
    }

    public ArrayList<Integer> getResolution()
    {
        return resolution;
    }

    public void setResolution(ArrayList<Integer> r)
    {
        resolution = r;
    }

    public Vertex getPosition()
    {
        return Pos;
    }

    public double getZoom()
    {
        return zoom;
    }

    public void setPos(Vertex Pos)
    {
        this.Pos = Pos;
    }

    public void setZoom(double z)
    {
        zoom = z;        
        double d = calculateDistance(new Vertex(0,0,0));
        Origin = pointOnLineAtDistance(Pos, new Vertex(0,0,0), (d/21)*z + (d/21));
    }

    public Vertex getOrigin()
    {
        return Origin;
    }

    public int getXRotation()
    {
        return xRotation;
    }

    public int getYRotation()
    {
        return yRotation;
    }

    public int getZRotation()
    {
        return zRotation;
    }

    public void setXRotation(int r)
    {
        xRotation = r;
    }

    public void setYRotation(int r)
    {
        yRotation = r;
    }

    public void setZRotation(int r)
    {
        zRotation = r;
    }
}
