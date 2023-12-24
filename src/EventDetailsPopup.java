

import java.awt.event.ActionEvent;

import javax.swing.*;

public class EventDetailsPopup extends AddHotelEventPopup{
    private HotelEvent theEvent;
    private CustomButton deleteBtn = new CustomButton("Delete");

    public EventDetailsPopup(MainPanel mp, HotelEvent e)
    {
        super(mp);
        this.deleteBtn.addActionListener(this);
        this.setTitle("Event Details");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.addHotelEventPanel.setLayout(new BoxLayout(this.addHotelEventPanel, BoxLayout.Y_AXIS));

        this.theEvent = e;
        
        this.nameField.setText(theEvent.getName());
        this.detailsField.setText(theEvent.getDetails());
        this.sDateField.setText(theEvent.getsDate());
        this.eDateField.setText(theEvent.geteDate());
        this.costField.setText("" + theEvent.getData()[0]);
        this.revenueField.setText("" + theEvent.getData()[1]);
        if(theEvent.getType() == 1) this.conferenceBtn.setSelected(true);
        if(theEvent.getType() == 2) this.entertainmentBtn.setSelected(true);
        if(theEvent.getType() == 3) this.miscellaneousBtn.setSelected(true);

        this.addHotelEventPanel.add(this.submitBtn);
        this.addHotelEventPanel.add(this.deleteBtn);

        this.setContentPane(this.addHotelEventPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        if(e.getSource().equals(this.deleteBtn))
        {
            int j = JOptionPane.showConfirmDialog(null, "Delete this event?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(j == JOptionPane.YES_OPTION)
            {
                DataHub.selectedHotel.removeHotelEvent(getName());
                this.mp.redo();
                this.dispose();
            }
        }
    }
}
