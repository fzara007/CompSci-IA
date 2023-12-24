import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DayPanel extends JPanel implements ActionListener{
    private ArrayList<HotelEvent> dailyEvents = new ArrayList<HotelEvent>();
    private ArrayList<CustomButton> dailyEventsBtns = new ArrayList<CustomButton>();
    private int date = 0;
    MainPanel mp;

    public DayPanel(MainPanel p)
    {
        this.mp = p;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setSize(new Dimension(700, 100));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public DayPanel(MainPanel p, int day)
    {
        this(p);
        this.date = day;
        this.redo();
    }

    public void newEvent(HotelEvent e)
    {
        if(this.date == 0) return;

        this.dailyEvents.add(e);
        this.dailyEventsBtns.add(new CustomButton(e.getName()));
        
        this.redo();
    }

    public void redo()
    {
        this.removeAll();
        
        CustomLabel day = new CustomLabel(this.date + "      ");
        day.setFont(new Font(this.getFont().getName(), Font.PLAIN, 16));
        this.add(day);
        this.add(Box.createRigidArea(new Dimension(5, 10)));

        for (int i=0; i<this.dailyEvents.size(); i++)
        {
            CustomButton btn = this.dailyEventsBtns.get(i);
            btn.setFont(new Font(this.getFont().getName(), Font.PLAIN, 18));
            btn.addActionListener(this);
            // 1 - conference, 2 - concert/sport, 3 - miscellaneous
            if(this.dailyEvents.get(i).getType() == 1)
                btn.setBackground(new Color(140, 185, 215));
            if(this.dailyEvents.get(i).getType() == 2)
                btn.setBackground(new Color(215, 145, 140));
            if(this.dailyEvents.get(i).getType() == 3)
                btn.setBackground(new Color(160, 215, 160));
        
            this.add(btn);
        }
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int btn=0; btn<this.dailyEventsBtns.size(); btn++)
        {
            if(e.getSource().equals(this.dailyEventsBtns.get(btn)))
            {
                new EventDetailsPopup(this.mp, this.dailyEvents.get(btn));
            }
        }

    }

}