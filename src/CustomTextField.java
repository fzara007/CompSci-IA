import java.awt.Font;

import javax.swing.JTextField;


public class CustomTextField extends JTextField{
    public CustomTextField()
    {
        super();
        this.setFont(getFont().deriveFont(Font.PLAIN, 20));
    }
    public CustomTextField(String str)
    {
        super(str);
        this.setFont(getFont().deriveFont(Font.PLAIN, 20));
    }
    public CustomTextField(int num)
    {
        super(num);
        this.setFont(getFont().deriveFont(Font.PLAIN, 20));
    }
}
