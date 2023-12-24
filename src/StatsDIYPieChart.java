import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StatsDIYPieChart extends JPanel{
    private char type;
    
    public StatsDIYPieChart(char t)
    {
        type = t;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        int centerX = this.getWidth()/2;
        int centerY = this.getHeight()/2;
        
        g.setFont(new Font(g.getFont().getName(), Font.BOLD, 30));


        if(type=='r')
        {
            g.drawString("Revenue by Type", centerX-135, centerY-115);
            double total = DataHub.selectedHotel.getTotalRevenue();
            int conference = DataHub.selectedHotel.getStatsByType(1)[1];
            int entertainment = DataHub.selectedHotel.getStatsByType(2)[1];
            int miscellaneous = DataHub.selectedHotel.getStatsByType(3)[1];
            
            if(total==0) return;
            int angle = 0;
            g.setColor(new Color(140, 185, 215));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*conference/total));
            angle += (int) (360*conference/total);
            g.setColor(new Color(215, 145, 140));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*entertainment/total));
            angle += (int) (360*entertainment/total);
            g.setColor(new Color(160, 215, 160));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*miscellaneous/total));
            angle += (int) (360*miscellaneous/total);
        }
        if(type=='c')
        {
            g.drawString("Cost by Type", centerX-115, centerY-115);

            int total = DataHub.selectedHotel.getTotalCost();
            int conference = DataHub.selectedHotel.getStatsByType(1)[0];
            int entertainment = DataHub.selectedHotel.getStatsByType(2)[0];
            int miscellaneous = DataHub.selectedHotel.getStatsByType(3)[0];
            if(total==0) return;
            int angle = 0;
            g.setColor(new Color(140, 185, 215));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*conference/total));
            angle += (int) (360*conference/total);
            g.setColor(new Color(215, 145, 140));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*entertainment/total));
            angle += (int) (360*entertainment/total);
            g.setColor(new Color(160, 215, 160));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*miscellaneous/total));
            angle += (int) (360*miscellaneous/total);
        }
        if(type=='p')
        {
            g.drawString("Profit by Type", centerX-125, centerY-115);

            int total = DataHub.selectedHotel.getTotalRevenue() - DataHub.selectedHotel.getTotalCost();
            int conference = DataHub.selectedHotel.getStatsByType(1)[1] - DataHub.selectedHotel.getStatsByType(1)[0];
            int entertainment = DataHub.selectedHotel.getStatsByType(2)[1] - DataHub.selectedHotel.getStatsByType(2)[0];
            int miscellaneous = DataHub.selectedHotel.getStatsByType(3)[1] - DataHub.selectedHotel.getStatsByType(3)[0];
            if(total==0) return;
            int angle = 0;
            g.setColor(new Color(140, 185, 215));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*conference/total));
            angle += (int) (360*conference/total);
            g.setColor(new Color(215, 145, 140));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*entertainment/total));
            angle += (int) (360*entertainment/total);
            g.setColor(new Color(160, 215, 160));
            g.fillArc(centerX-40, centerY-100, 230, 230, angle, (int) (360*miscellaneous/total));
            angle += (int) (360*miscellaneous/total);
        }

        g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 18));
        g.setColor(new Color(140, 185, 215));
        g.fillOval(centerX-190, centerY-50, 20, 20);
        g.setColor(new Color(215, 145, 140));
        g.fillOval(centerX-190, centerY, 20, 20);
        g.setColor(new Color(160, 215, 160));
        g.fillOval(centerX-190, centerY+50, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("Conference", centerX-165, centerY-35);
        g.drawString("Concerts", centerX-165, centerY);
        g.drawString("Sports", centerX-165, centerY+35);
        g.drawString("Miscelllaneous", centerX-165, centerY+65);

    }
}
