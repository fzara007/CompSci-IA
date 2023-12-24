import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame implements WindowListener{
    public static void main(String[] args) throws Exception {
        new MainFrame(args);
    }

    public MainFrame(String[] arg)
    {
        this.addWindowListener(this);
        DataHub.open();
        this.setTitle("Calendar Whiz");
        this.setSize(1050, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(new MainPanel(arg));

        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        DataHub.save();
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
