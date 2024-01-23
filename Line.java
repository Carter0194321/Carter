import java.util.*;

public class Line {
    private Vertex v1;
    private Vertex v2;

    public Line(Vertex v1, Vertex v2)
    {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Line(ArrayList<Vertex> c) throws Exception 
    {
        this.v1 = c.get(0);
        this.v2 = c.get(1);
    }

    public ArrayList<Vertex> getVertices()
    {
        return new ArrayList<>(Arrays.asList(v1,v2));
    }

    public Vertex getV1()
    {
        return v1;
    }

    public Vertex getV2()
    {
        return v2;
    }

    public void setVerticies(ArrayList<Vertex> c) throws Exception
    {
        v1 = c.get(0);
        v2 = c.get(1);
    }

    public void setV1(Vertex v1)
    {
        this.v1 = v1;
    }

    public void setV2(Vertex v2)
    {
        this.v2 = v2;
    }
}
