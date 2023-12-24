

// import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Hotel {
    private String name;
    private String abbreviation;
    // private File savedData;
    private ArrayList<HotelEvent> events = new ArrayList<HotelEvent>();
    private ArrayList<int[]> eventData = new ArrayList<int[]>();
    private int[][] typeStats= new int[3][2];

    public Hotel(String n, String abb)
    {
        this.name = n;
        this.abbreviation = abb;
        System.out.println("Made new hotel of name " + this.name + " with abbrev of " + this.abbreviation);
    }

    public String getName()
    {
        return this.name;
    }

    public String getAbbr()
    {
        return this.abbreviation;
    }

    // private void saveData(File f){}

    public void addHotelEvent(String n, String d, int sDate, int eDate, 
                                int cost, int rev, int type)
    {
        if(this.findEvent(n) != null && this.findEvent(n).getType() == type)
        {
            new JOptionPane("Error: There is already a hotel event with this name and type.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.events.add(new HotelEvent(n, d, sDate, eDate, cost, rev, type));
        if(type == 1)
        {
            this.typeStats[0][0] += cost;
            this.typeStats[0][1] += rev;
        }
        if(type == 2)
        {
            this.typeStats[1][0] += cost;
            this.typeStats[1][1] += rev;
        }
        if(type == 3)
        {
            this.typeStats[2][0] += cost;
            this.typeStats[2][1] += rev;
        }
        this.eventData.add(this.events.get(this.events.size()-1).getData());
        System.out.println("successfully added event");
    }

    public void removeHotelEvent(String n)
    {
        if(this.findEvent(n) == null)
            new JOptionPane("Could not find Hotel Event " + n, JOptionPane.ERROR_MESSAGE);
        this.events.remove(this.findEvent(n));
    }

    public ArrayList<HotelEvent> getEvents(){ return this.events;}

    public ArrayList<int[]> getEventData(){ return this.eventData;}

    public int getTotalCost(){ return this.typeStats[0][0] + this.typeStats[1][0] + this.typeStats[2][0];}

    public int getTotalRevenue(){ return this.typeStats[0][1] + this.typeStats[1][1] + this.typeStats[2][1];}

    public int[] getStatsByType(int t){ return this.typeStats[t-1];}

    private HotelEvent findEvent(String n)
    {
        // searches the events to match name entered with event names 
        
        for(int i=0; i<this.events.size(); i++)
        {
            if(this.events.get(i).getName().equals(n))
                return this.events.get(i);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
