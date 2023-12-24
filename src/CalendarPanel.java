
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
// import JDatePicker

public class CalendarPanel extends JPanel implements ActionListener{
    public CustomButton addEventBtn = new CustomButton("Add Event");
    private JPanel actualThing = new JPanel();
    private JPanel[] days;
    private JPanel info = new JPanel();
    private MainPanel mp;
    private int month;

    public CalendarPanel(MainPanel p)
    {
        // determining the month and how many days are in that month
        this.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if(this.month==2)
        { 
            if(Calendar.getInstance().get(Calendar.YEAR)%4==0) this.days = new JPanel[29];
            else this.days = new JPanel[28];
        }
        else if((this.month < 8 && this.month%2 == 1) || (this.month > 7 && this.month%2 == 0)) 
            this.days = new JPanel[31];
        else this.days = new JPanel[30];


        this.mp = p;
        this.addEventBtn.setFont(new Font(this.getFont().getName(), Font.PLAIN, 22));

        this.addEventBtn.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.actualThing.setLayout(new GridLayout(6, 7));

        this.redo();
        
        this.info.setLayout(new BoxLayout(this.info, BoxLayout.X_AXIS));
        CustomLabel month = new CustomLabel(new SimpleDateFormat("MMMM").
                    format(Calendar.getInstance().getTime()) + "          ");
        month.setFont(new Font(this.getFont().getName(), Font.PLAIN, 22));
        this.info.add(month);
        this.info.add(this.addEventBtn);

        this.add(this.actualThing);
        this.add(this.info);

        setVisible(true);
    }

    public void redo()
    {
        if(DataHub.selectedHotel == null) this.addEventBtn.setEnabled(false);
        else this.addEventBtn.setEnabled(true);

        this.actualThing.removeAll();
        this.actualThing.setLayout(new GridLayout(6, 7));
        int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int count =0;
        
        for(int i=0; i<7 - (today%7 - weekday); i++)
        {
            JScrollPane j = new JScrollPane(new DayPanel(this.mp));
            j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.actualThing.add(j);
            count++;
        }
        for(int i=0; i<days.length; i++)
        {
            this.days[i] = new DayPanel(this.mp, i+1);
            JScrollPane j = new JScrollPane(this.days[i]);
            j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.actualThing.add(j);
            count ++;
        }
        for(int i=0; i<42-count; i++)
        {
            JScrollPane j = new JScrollPane(new DayPanel(this.mp));
            j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.actualThing.add(j);
        }

        // int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        // int weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        // System.out.print(weekday);
        // int monthsWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);

        // (monthsWeek-1)*7 + weekday - today

        for(int d=0; d<this.days.length; d++)
        {
            ((DayPanel) this.days[d]).redo();
            Integer day = Integer.parseInt(((JLabel) this.days[d].getComponent(0)).getText().trim());

            if(DataHub.selectedHotel == null || DataHub.selectedHotel.getEvents() == null) return;

            for(int event=0; event<DataHub.selectedHotel.getEvents().size(); event++)
            {
                HotelEvent ev = DataHub.selectedHotel.getEvents().get(event);
                Integer sd = Integer.parseInt(ev.getsDate()); 
                Integer ed = Integer.parseInt(ev.geteDate()); 
                if((ed==sd && day==sd) || sd<=day && day <= ed)
                    ((DayPanel) this.days[d]).newEvent(ev);
            }
        }

        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.addEventBtn))
        {
            new AddHotelEventPopup(this.mp);
            this.redo();
        }
    }
}
