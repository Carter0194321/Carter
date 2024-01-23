import java.util.*;

public class Triangle {
    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private int color;

    public Triangle(Vertex v1, Vertex v2, Vertex v3, int c)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        color = c;
    }

    public Triangle(ArrayList<Vertex> c, int col) throws Exception 
    {
        this.v1 = c.get(0);
        this.v2 = c.get(1);
        this.v3 = c.get(2);
        color = col;
    }

    public ArrayList<Vertex> getVertices()
    {
        return new ArrayList<>(Arrays.asList(v1,v2,v3));
    }

    public Vertex getV1()
    {
        return v1;
    }

    public Vertex getV2()
    {
        return v2;
    }

    public Vertex getV3()
    {
        return v3;
    }

    public int getColor()
    {
        return color;
    }

    public void setVerticies(ArrayList<Vertex> c) throws Exception
    {
        v1 = c.get(0);
        v2 = c.get(1);
        v3 = c.get(2);
    }

    public void setV1(Vertex v1)
    {
        this.v1 = v1;
    }

    public void setV2(Vertex v2)
    {
        this.v2 = v2;
    }

    public void setV3(Vertex v3)
    {
        this.v3 = v3;
    }

    public void setColor(int c)
    {
        color = c;
    }
    
}
