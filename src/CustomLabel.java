import java.awt.Font;

import javax.swing.JLabel;


public class CustomLabel extends JLabel{
    public CustomLabel()
    {
        super();
        this.setFont(getFont().deriveFont(Font.PLAIN, 22));
    }
    public CustomLabel(String str)
    {
        super(str);
        this.setFont(getFont().deriveFont(Font.PLAIN, 22));
    }
}
