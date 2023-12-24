

import java.awt.event.*;

import javax.swing.*;

public class RemoveHotelPopup extends JFrame implements ActionListener{
    private JPanel removeHotelPanel = new JPanel();
    private CustomTextField nameField = new CustomTextField(20);
    
    private CustomButton submitBtn = new CustomButton("Submit");
    private MainPanel mp;

    public RemoveHotelPopup(MainPanel m)
    {
        this.mp = m;
        this.setTitle("Delete Hotel");
        this.setSize(150, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.removeHotelPanel.setLayout(new BoxLayout(this.removeHotelPanel, BoxLayout.Y_AXIS));

        this.removeHotelPanel.add(new CustomLabel("Name:"));
        this.removeHotelPanel.add(this.nameField);

        this.submitBtn.addActionListener(this);
        this.removeHotelPanel.add(this.submitBtn);

        this.setContentPane(this.removeHotelPanel);

        this.setResizable(true);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.submitBtn))
        {
            if(this.nameField.getText() == null || this.nameField.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Error: Incomplete input, please fill everything out.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try 
            {
                DataHub.removeHotel(this.nameField.getText());    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: Coud not find a hotel named " 
                        + this.nameField.getText() + ".", "Error", JOptionPane.ERROR_MESSAGE);
                this.nameField.setText("");
                return;
            }
            
            this.dispose();
            mp.redo();
        }
    }
}
