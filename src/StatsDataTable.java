import javax.swing.JPanel;
import javax.swing.JTable;

public class StatsDataTable extends JPanel{
    StatsDataTable()
    {
        String[] columns = {"Event", "Start Date", "End Date", "Cost", "Profit", "Revenue", "Type"};
        String[][] data = new String[DataHub.selectedHotel.getEvents().size()][7];
        for(int e=0; e<DataHub.selectedHotel.getEvents().size(); e++)
        {
            HotelEvent event = DataHub.selectedHotel.getEvents().get(e);
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
            data[e][6] = "Concerts\nSports";
            if(t==3)
            data[e][6] = "Miscellaneous"; 
        }
        JTable jT = new JTable(data, columns);
        this.add(jT);
    }
}
