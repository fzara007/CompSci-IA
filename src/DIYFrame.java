import javax.swing.JFrame;

public class DIYFrame extends JFrame{
    public DIYFrame(char t)
    {
        this.setTitle("Pie Chart");

        this.setSize(450, 375);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(new StatsDIYPieChart(t));

        this.setResizable(false);
        this.setVisible(true);
    }
}
