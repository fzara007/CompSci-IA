import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import javafx.application.Application;
public class StatsPanel extends JPanel implements ActionListener{
    private JPanel sortOpt = new JPanel();
    private CustomRadioButton sortN = new CustomRadioButton("Name");
    private CustomRadioButton sortD = new CustomRadioButton("Date");
    private CustomRadioButton sortC = new CustomRadioButton("Cost");
    private CustomRadioButton sortR = new CustomRadioButton("Revenue");
    private CustomRadioButton sortP = new CustomRadioButton("Profit");
    private ButtonGroup btns = new ButtonGroup();
    
    private JPanel infoPanel = new JPanel();

    private CustomButton lineChart = new CustomButton("Line Graph*");
    private CustomButton pieChart = new CustomButton("Pie Graphs");

    private String[] psuedoArgs;

    public StatsPanel(String[] args)
    {
        this.setLayout(new BorderLayout());
        this.infoPanel.setLayout(new GridLayout(1, 3));
        this.sortOpt.setLayout(new BoxLayout(this.sortOpt, BoxLayout.X_AXIS));
        this.sortOpt.add(this.sortN);
        this.sortOpt.add(this.sortD);
        this.sortOpt.add(this.sortC);
        this.sortOpt.add(this.sortR);
        this.sortOpt.add(this.sortP);
        this.btns.add(this.sortN);
        this.btns.add(this.sortD);
        this.btns.add(this.sortC);
        this.btns.add(this.sortR);
        this.btns.add(this.sortP);
        TitledBorder tb = new TitledBorder("Sort by:");
        tb.setTitleFont(new Font(this.getFont().getName(), Font.PLAIN, 23));
        this.sortOpt.setBorder(tb);
        this.lineChart.addActionListener(this);
        this.pieChart.addActionListener(this);
        this.sortN.addActionListener(this);
        this.sortD.addActionListener(this);
        this.sortC.addActionListener(this);
        this.sortR.addActionListener(this);
        this.sortP.addActionListener(this);
        this.redo();
    }


    public void redo(){
        this.removeAll();
        this.add(this.sortOpt, BorderLayout.NORTH);
        this.infoPanel.removeAll();
        
        this.add(infoPanel, BorderLayout.SOUTH);

        if(DataHub.selectedHotel == null) return;

        this.infoPanel.add(this.lineChart);
        this.infoPanel.add(this.pieChart);
        
        this.infoPanel.add(new CustomLabel(" Total Profit: " + 
                (DataHub.selectedHotel.getTotalRevenue() - DataHub.selectedHotel.getTotalCost())));
        String[] columns = {"Event", "Start Date", "End Date", "Cost", "Revenue", "Profit", "Type"};
        String[][] data = new String[DataHub.selectedHotel.getEvents().size() + 1][7];
        data[0] = columns;
        for(int e=1; e<DataHub.selectedHotel.getEvents().size()+1; e++)
        {
            HotelEvent event = DataHub.selectedHotel.getEvents().get(e-1);
            data[e][0] = event.getName();
            data[e][1] = "" + event.getsDate();
            data[e][2] = "" + event.geteDate();
            data[e][3] = "" + event.getData()[0];
            data[e][4] = "" + event.getData()[1];
            data[e][5] = "" + event.getData()[2];
            int t = event.getType();
            if(t==1)
            data[e][6] = "Conference";
            if(t==2)
            data[e][6] = "Entertainment";
            if(t==3)
            data[e][6] = "Miscellaneous"; 
        }
        JTable jT = new JTable(data, columns);
        jT.setFont(new Font(this.getFont().getName(), Font.PLAIN, 23));
        jT.setRowHeight(30); 
        this.add(jT, BorderLayout.CENTER);
        this.repaint();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(this.lineChart))
        {
            Application.launch(StatsLineChart.class, psuedoArgs);
        }
        if(e.getSource().equals(this.pieChart))
        {
            new DIYFrame('p');
            new DIYFrame('c');
            new DIYFrame('r');
        }
        if(e.getSource().equals(this.sortN))
        {
            SortName.sort(DataHub.selectedHotel.getEvents());
            this.redo();
        }
        if(e.getSource().equals(this.sortD))
        {
            SortDate.sort(DataHub.selectedHotel.getEvents());
            this.redo();
        }
        if(e.getSource().equals(this.sortC))
        {
            SortMoney.sort(DataHub.selectedHotel.getEvents(), 0);
            this.redo();
        }
        if(e.getSource().equals(this.sortR))
        {
            SortMoney.sort(DataHub.selectedHotel.getEvents(), 1);
            this.redo();
        }
        if(e.getSource().equals(this.sortP))
        {
            SortMoney.sort(DataHub.selectedHotel.getEvents(), 2);
            this.redo();
        }
    }
}