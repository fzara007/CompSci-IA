import java.awt.Font;

import javax.swing.JButton;


public class CustomButton extends JButton{
    public CustomButton()
    {
        super();
        this.setFont(getFont().deriveFont(Font.PLAIN, 20));
    }
    public CustomButton(String str)
    {
        super(str);
        this.setFont(getFont().deriveFont(Font.PLAIN, 20));
    }
}
