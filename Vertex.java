import java.util.*;

public class Vertex{
    private double x;
    private double y;
    private double z;
    private int color;

    public Vertex(double x, double y, double z, int c)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        color = c;
    }

    public Vertex rotateAboutY(Vertex center, double angle) {
        // Translate the vertex to the origin by subtracting the center
        Vertex translatedVertex = this.subtract(center);

        // Perform the rotation
        double newX = translatedVertex.getX() * Math.cos(angle) + translatedVertex.getZ() * Math.sin(angle);
        double newZ = -translatedVertex.getX() * Math.sin(angle) + translatedVertex.getZ() * Math.cos(angle);

        // Create a new Vertex with the rotated coordinates
        Vertex rotatedVertex = new Vertex(newX, translatedVertex.getY(), newZ);

        // Translate the vertex back to its original position by adding the center
        return rotatedVertex.add(center);
    }

    public Vertex rotateAboutX(Vertex center, double angle) {
        // Translate the vertex to the origin by subtracting the center
        Vertex translatedVertex = this.subtract(center);

        // Perform the rotation
        double newY = translatedVertex.getY() * Math.cos(angle) - translatedVertex.getZ() * Math.sin(angle);
        double newZ = translatedVertex.getY() * Math.sin(angle) + translatedVertex.getZ() * Math.cos(angle);

        // Create a new Vertex with the rotated coordinates
        Vertex rotatedVertex = new Vertex(translatedVertex.getX(), newY, newZ);

        // Translate the vertex back to its original position by adding the center
        return rotatedVertex.add(center);
    }

    public Vertex(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex(ArrayList<Double> c, int color) throws Exception 
    {
        this.x = c.get(0);
        this.y = c.get(1);
        this.z = c.get(2);
        this.color = color;
    }

    public Vertex(ArrayList<Double> c) throws Exception 
    {
        this.x = c.get(0);
        this.y = c.get(1);
        this.z = c.get(2);
    }
    

    public ArrayList<Double> getCordinates()
    {
        return new ArrayList<>(Arrays.asList(x , y , z));
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    public void setCordinates(ArrayList<Double> c) throws Exception
    {
        x = c.get(0);
        y = c.get(1);
        z = c.get(2);
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setZ(double z)
    {
        this.z = z;
    }

    public Vertex subtract(Vertex v) {
        return new Vertex(this.x - v.getX(), this.y - v.getY(), this.z - v.getZ());
    }

    public Vertex crossProduct(Vertex v) {
        double resultX = this.y * v.getZ() - this.z * v.getY();
        double resultY = this.z * v.getX() - this.x * v.getZ();
        double resultZ = this.x * v.getY() - this.y * v.getX();
        return new Vertex(resultX, resultY, resultZ);
    }

    public double dotProduct(Vertex v) {
        return this.x * v.getX() + this.y * v.getY() + this.z * v.getZ();
    }

    public int getColor()
    {
        return color;
    }

    public Vertex add(Vertex v) {
        double newX = this.x + v.getX();
        double newY = this.y + v.getY();
        double newZ = this.z + v.getZ();
        return new Vertex(newX, newY, newZ);
    }
    public Vertex multiply(double scalar) {
        return new Vertex(this.x * scalar, this.y * scalar, this.z * scalar);
    }
    

    public Vertex normalize() {
        double length = Math.sqrt(x * x + y * y + z * z);
        return new Vertex(x / length, y / length, z / length);
    }

    public void setColor(int c)
    {
        color = c;
    }
}