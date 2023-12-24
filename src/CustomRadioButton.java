import java.awt.Font;

import javax.swing.JRadioButton;


public class CustomRadioButton extends JRadioButton{
    public CustomRadioButton()
    {
        super();
        this.setFont(getFont().deriveFont(Font.PLAIN, 20));
    }
    public CustomRadioButton(String str)
    {
        super(str);
        this.setFont(getFont().deriveFont(Font.PLAIN, 20));
    }
}
