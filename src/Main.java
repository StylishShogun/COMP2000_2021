import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;

class Main extends JFrame {
    
    class App extends JPanel {
        
        Stage stage;

        public App() throws IOException {
            setPreferredSize(new Dimension(1024, 720));
            stage = new Stage();
            stage = StageReader.readStage("data/stage1.rvb");
        }

        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }

    }

    public static void main(String[] args) throws IOException {
        Main window = new Main();
        window.run();
    }

    private Main() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            this.repaint();
        }
    }
}