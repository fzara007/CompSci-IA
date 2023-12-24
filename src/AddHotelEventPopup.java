

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;


public class AddHotelEventPopup extends JFrame implements ActionListener{
    public JPanel sizePanel = new JPanel();
    public JPanel addHotelEventPanel = new JPanel();
    protected MainPanel mp;
    protected CustomTextField nameField;
    protected CustomTextField detailsField;
    protected CustomTextField sDateField;
    protected CustomTextField eDateField;
    protected CustomTextField costField ;
    protected CustomTextField revenueField;
    protected ButtonGroup types = new ButtonGroup();
    // 1, 2, 3
    protected JRadioButton conferenceBtn = new JRadioButton("Conference");
    protected JRadioButton entertainmentBtn = new JRadioButton("Concert/Sport");
    protected JRadioButton miscellaneousBtn = new JRadioButton("Miscellaneous");
    protected CustomButton submitBtn = new CustomButton("Submit");

    public AddHotelEventPopup(MainPanel p)
    {
        this.mp = p;
        this.setTitle("Add Hotel Event");
        this.setSize(200, 600);
        this.sizePanel.setLayout(new GridLayout(1,1));

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.addHotelEventPanel.setLayout(new BoxLayout(this.addHotelEventPanel, BoxLayout.Y_AXIS));
        
        this.submitBtn.addActionListener(this);
        this.conferenceBtn.addActionListener(this);
        this.entertainmentBtn.addActionListener(this);
        this.miscellaneousBtn.addActionListener(this);
        this.types.add(this.conferenceBtn);
        this.types.add(this.entertainmentBtn);
        this.types.add(this.miscellaneousBtn);
        
        this.nameField = new CustomTextField();
        this.detailsField = new CustomTextField();
        this.sDateField = new CustomTextField();
        this.eDateField = new CustomTextField();
        this.costField = new CustomTextField();
        this.revenueField = new CustomTextField();
        

        Font f = new Font(this.miscellaneousBtn.getFont().getName(), Font.PLAIN, 20);
        this.conferenceBtn.setFont(f);
        this.entertainmentBtn.setFont(f);
        this.miscellaneousBtn.setFont(f);

        this.addHotelEventPanel.add(new CustomLabel("Name:"));
        this.addHotelEventPanel.add(this.nameField);
        this.addHotelEventPanel.add(new CustomLabel("Details:"));
        this.addHotelEventPanel.add(this.detailsField);
        this.addHotelEventPanel.add(new CustomLabel("Start Date (dd):"));
        this.addHotelEventPanel.add(this.sDateField);
        this.addHotelEventPanel.add(new CustomLabel("End Date (dd):"));
        this.addHotelEventPanel.add(this.eDateField);
        this.addHotelEventPanel.add(new CustomLabel("Cost:"));
        this.addHotelEventPanel.add(this.costField);
        this.addHotelEventPanel.add(new CustomLabel("Revenue:"));
        this.addHotelEventPanel.add(this.revenueField);
        this.addHotelEventPanel.add(new CustomLabel("Type"));
        this.addHotelEventPanel.add(this.conferenceBtn);
        this.addHotelEventPanel.add(this.entertainmentBtn);
        this.addHotelEventPanel.add(this.miscellaneousBtn);

        this.addHotelEventPanel.add(this.submitBtn);

        this.sizePanel.add(this.addHotelEventPanel);
        this.setContentPane(this.sizePanel);

        this.setResizable(false);
        this.setVisible(true);
    }
    
    private boolean isEmpty(CustomTextField jf)
    {
        return jf.getText() ==null || jf.getText().trim().equals("");
    }

    private boolean dateInput(CustomTextField jf)
    {
        String input = jf.getText().trim();
        if(input.length() > 3) return false;
        try { 
            int in = Integer.parseInt(input);
            return in <= Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)
                    && in > 0 ;
        } catch(NumberFormatException e) { 
            return false; 
        }
    }

    private boolean isPosInt(CustomTextField jf)
    {
        String input = jf.getText().trim();
        try { 
            if(Integer.parseInt(input)<0)
                return false;
        } catch(NumberFormatException e) { 
            return false; 
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DataHub.selectedHotel.removeHotelEvent(this.nameField.getText());
        
        int type = -1;

        if(this.conferenceBtn.isSelected()) type = 1;
        if(this.entertainmentBtn.isSelected()) type = 2;
        if(this.miscellaneousBtn.isSelected()) type = 3;
        
        if(e.getSource().equals(this.submitBtn))
        {
            if(this.isEmpty(this.nameField) || this.isEmpty(this.detailsField) || this.isEmpty(this.sDateField) 
                            || this.isEmpty(this.eDateField) || this.isEmpty(this.costField) || this.isEmpty(this.revenueField))
            {
                JOptionPane.showMessageDialog(this, "Error: Incomplete input, please fill everything out.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!this.dateInput(this.sDateField) || !this.dateInput(this.eDateField))
            {
                JOptionPane.showMessageDialog(this, "Error: Input for date is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!this.isPosInt(this.costField) || !this.isPosInt(this.revenueField))
            {
                JOptionPane.showMessageDialog(this, "Error: Input for money is not a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(type==-1)
            {
                JOptionPane.showMessageDialog(this, "Error: Please select an event type.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DataHub.selectedHotel.addHotelEvent(this.nameField.getText(), this.detailsField.getText(), Integer.parseInt(this.sDateField.getText().replaceAll(" ", "")),
                            Integer.parseInt(this.eDateField.getText().replaceAll(" ", "")), Integer.parseInt(this.costField.getText().replaceAll(" ", "")), Integer.parseInt(this.revenueField.getText().replaceAll(" ", "")), type);
            // this.setVisible(false);
            
            mp.redo();
            this.dispose();
        }
    }
}
