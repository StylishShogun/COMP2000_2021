import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

class Cell extends Rectangle {
    static int size = 35;
    public int elevation = getRandomNumber(-500, 6000);
    public String landscape;
    private int eleRGB = 0;
    private String hoverText = "error";
    public int landType = generateLandType(); // 0 = grass, 1 = road, 2 = water, 3= mountain, 4 = building
    Color eleColor = setElevationColor();

    public Cell(int x, int y){
        super(x, y, size, size);
    }

    int generateLandType (){ 
        int rand = getRandomNumber(1, 100);
        if (rand <= 10) {
            hoverText = "Road";
            return 1;
        }
        else if (rand <= 30){
            hoverText = "Water";
            return 2;
        }
        else if (rand <=70){
            hoverText = "Grass";
            return 0;
        }
        else if (rand <=95){
            hoverText = "Mountain";
            return 3;
        }
        else {
            hoverText = "Building";
            return 4;
        }
    }

    Color setElevationColor () {
        if (landType == 0) {
        eleRGB = ((elevation +500) / 26); // The math here is a bit dirty, its only 0-250 instead of 0-255
        Color Color = new Color(eleRGB, 255, eleRGB);
        return Color;
        }
        else if (landType == 1) {
            eleRGB = ((elevation +500) / 26); 
            Color Color = new Color(eleRGB, eleRGB, eleRGB); // It's more of a white/black instead of grey, sorry
            return Color;
        }
        else if (landType == 2) {
            eleRGB = ((elevation +500) / 26); 
            Color Color = new Color(eleRGB, eleRGB, 255);
            return Color;
        }
        else if (landType == 3) {
            eleRGB = ((elevation +500) / 26); 
            Color Color = new Color(255, 255, eleRGB);
            return Color;
        }
        else {
            elevation = 0; // It states a building "doesn't have an elevation field" so I just set it to
            Color Color = new Color(165, 42, 42);
            return Color;
        }

    }

    void paint(Graphics g, Point mousePos){
        if(contains(mousePos)){
            g.setColor(Color.GRAY);
            g.drawString(String.valueOf(elevation), 720, 360);
            g.drawString(hoverText, 720, 390);
        } else {
            g.setColor(eleColor);
            //Infobox.setText(" ");
        }
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public boolean contains(Point p){
        if (p != null){
            return(super.contains(p));
        } else {
            return false;
        }
    }
}