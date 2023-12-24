

import java.awt.event.*;

import javax.swing.*;

public class AddHotelPopup extends JFrame implements ActionListener{
    private JPanel addHotelPanel = new JPanel();
    private MainPanel mp;
    private CustomTextField nameField = new CustomTextField(20);
    private CustomTextField abbrevField = new CustomTextField(20);
    
    private CustomButton submitBtn = new CustomButton("Submit");
    private Boolean isSubmitted = false;

    public AddHotelPopup(MainPanel p)
    {
        this.mp = p;
        this.setTitle("Add Hotel");
        this.setSize(150, 200);

        this.addHotelPanel.setLayout(new BoxLayout(this.addHotelPanel, BoxLayout.Y_AXIS));

        this.addHotelPanel.add(new CustomLabel("Name:"));
        this.addHotelPanel.add(this.nameField);
        this.addHotelPanel.add(new CustomLabel("Abbreviation:"));
        this.addHotelPanel.add(this.abbrevField);

        this.submitBtn.addActionListener(this);
        this.addHotelPanel.add(this.submitBtn);

        this.setContentPane(this.addHotelPanel);

        this.setResizable(false);
        this.setVisible(true);
    }

    public String getHotelName()
    {
        return this.nameField.getText();
    }

    public boolean isSubmit()
    {
        return this.isSubmitted;
    }

    private boolean isEmpty(CustomTextField jf)
    {
        return jf.getText() ==null || jf.getText().equals("");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.submitBtn))
        {
            if(this.isEmpty(this.nameField) || this.isEmpty(this.abbrevField))
            {
                JOptionPane.showMessageDialog(this, "Error: Incomplete input, please fill everything out.", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Should be erroring");
            }
            if(DataHub.getAllHotels().contains(this.nameField.getText()))
            {
                JOptionPane.showMessageDialog(this, "Error: This hotel already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Should be erroring");
            }
            else
            {
                DataHub.addHotel(this.nameField.getText(), this.abbrevField.getText());
                this.isSubmitted = true;
                mp.redo();
                this.dispose();
            }
        }
    }
}
