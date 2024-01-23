import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args){
        Camera c = new Camera(new Vertex(0,0,500),1,-14);
        Stage stage = new Stage(c, 60);

        Vertex v1 = new Vertex(50,50,50);
        Vertex v2 = new Vertex(50,-50,50);
        Vertex v3 = new Vertex(-50,50,50);
        Triangle tri = new Triangle(v1 ,v2, v3, 32);
        stage.appendTri(tri);

        v1 = new Vertex(-50,-50,50);
        v2 = new Vertex(50,-50,50);
        v3 = new Vertex(-50,50,50); 
        tri = new Triangle(v1 ,v2, v3, 32);
        stage.appendTri(tri);

        v1 = new Vertex(50,50,-50);
        v2 = new Vertex(50,-50,-50);
        v3 = new Vertex(-50,50,-50);
        tri = new Triangle(v1 ,v2, v3, 31);
        stage.appendTri(tri);

        v1 = new Vertex(-50 ,-50,-50);
        v2 = new Vertex(50,-50,-50);
        v3 = new Vertex(-50,50,-50);
        tri = new Triangle(v1 ,v2, v3, 31);
        stage.appendTri(tri);

        v1 = new Vertex(50,50,50);
        v2 = new Vertex(50,50,-50);
        v3 = new Vertex(-50,50,50);
        tri = new Triangle(v1 ,v2, v3, 33);
        stage.appendTri(tri);

        v1 = new Vertex(-50,50,50);
        v2 = new Vertex(50,50,-50);
        v3 = new Vertex(-50,50,-50);
        tri = new Triangle(v1 ,v2, v3, 33);
        stage.appendTri(tri);

        v1 = new Vertex(50,-50,50);
        v2 = new Vertex(50,-50,-50);
        v3 = new Vertex(-50,-50,50);
        tri = new Triangle(v1 ,v2, v3, 35);
        stage.appendTri(tri);

        v1 = new Vertex(-50,-50,50);
        v2 = new Vertex(50,-50,-50);
        v3 = new Vertex(-50,-50,-50);
        tri = new Triangle(v1 ,v2, v3, 35);
        stage.appendTri(tri);

        v1 = new Vertex(-50,50,50);
        v2 = new Vertex(-50,50,-50);
        v3 = new Vertex(-50,-50,-50);
        tri = new Triangle(v1 ,v2, v3, 36);
        stage.appendTri(tri);

        v1 = new Vertex(-50,50,50);
        v2 = new Vertex(-50,-50,50);
        v3 = new Vertex(-50,-50,-50);
        tri = new Triangle(v1 ,v2, v3, 36);
        stage.appendTri(tri);

        v1 = new Vertex(50,50,50);
        v2 = new Vertex(50,50,-50);
        v3 = new Vertex(50,-50,-50);
        tri = new Triangle(v1 ,v2, v3, 37);
        stage.appendTri(tri);

        v1 = new Vertex(50,50,50);
        v2 = new Vertex(50,-50,50);
        v3 = new Vertex(50,-50,-50);
        tri = new Triangle(v1 ,v2, v3, 37);
        stage.appendTri(tri);


        JFrame myJFrame = new JFrame();
        myJFrame.setVisible(true);
        myJFrame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
              int keyCode = e.getKeyCode();
              if (keyCode == KeyEvent.VK_UP) {
                Camera c = stage.getCamera();
                if (stage.getCamera().getZoom() < -12)
                {
                  c.setZoom(stage.getCamera().getZoom() + 1);
                  stage.setCamera(c);
                  stage.updateStage(stage, c.getXRotation() ,c.getYRotation(),c.getZRotation());
                }
              }
              else if (keyCode == KeyEvent.VK_DOWN) {
                Camera c = stage.getCamera();
                if (stage.getCamera().getZoom() > -24)
                {
                  c.setZoom(stage.getCamera().getZoom() - 1);
                  stage.setCamera(c);
                  stage.updateStage(stage, c.getXRotation(),c.getYRotation(),c.getZRotation());
                }
              }
              else if (keyCode == KeyEvent.VK_W) {
                stage.updateStage(stage, c.getXRotation()+1,c.getYRotation(),c.getZRotation());
              }
              else if (keyCode == KeyEvent.VK_S) {
                stage.updateStage(stage, c.getXRotation()-1,c.getYRotation(),c.getZRotation());
              }
              else if (keyCode == KeyEvent.VK_E) {
                stage.updateStage(stage, c.getXRotation(),c.getYRotation()+1,c.getZRotation());
              }
              else if (keyCode == KeyEvent.VK_D) {
                stage.updateStage(stage, c.getXRotation(),c.getYRotation()-1,c.getZRotation());
              }
              else if (keyCode == KeyEvent.VK_R) {
                stage.updateStage(stage, c.getXRotation(),c.getYRotation(),c.getZRotation()+1);
              }
              else if (keyCode == KeyEvent.VK_F) {
                stage.updateStage(stage, c.getXRotation(),c.getYRotation(),c.getZRotation()-1);
              }
            }
          });
        stage.updateStage(stage, c.getXRotation(),c.getYRotation(),c.getZRotation());
    }
}