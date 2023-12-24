import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class DataHub{
    private static ArrayList<Hotel> hotels = new ArrayList<Hotel>();
    private static ArrayList<String> names = new ArrayList<String>();
    public static Hotel selectedHotel;

    public static void addHotel(String n, String abb)
    {
        Hotel newHotel = new Hotel(n, abb);
        DataHub.hotels.add(newHotel);
        DataHub.names.add(newHotel.getName());
        DataHub.selectedHotel = newHotel;
        System.out.println("Hotels num at " + hotels.size());
    }

    public static void removeHotel(String n) throws Exception
    {
        if(DataHub.findHotel(n) == null) throw new Exception();
        DataHub.hotels.remove(DataHub.findHotel(n));
    }

    public static void setHotel(String name)
    {
        selectedHotel = DataHub.findHotel(name);
        System.out.println("Set hotel " + DataHub.selectedHotel.getName());

    }

    public static ArrayList<String> getAllHotels()
    {
        return DataHub.names;
    }

    public static String getHotel(String n)
    {
        return DataHub.findHotel(n).getAbbr();
    }

    private static Hotel findHotel(String n)
    {
        for(int i=0; i<DataHub.names.size(); i++)
        {
            if(DataHub.names.get(i).equals(n.trim()))
                return DataHub.hotels.get(i);
        }

        for(int i=0; i<DataHub.names.size(); i++)
        {
            if(DataHub.hotels.get(i).getAbbr().equals(n.trim()))
                return DataHub.hotels.get(i);
        }

        return null;
    }       
    
    public static int calculateHotelProfit(Hotel hotel)
    {
        int sum = 0;
        for(int i=0; i<hotel.getEventData().size(); i++)
        {
            sum += hotel.getEventData().get(i)[3];
        }
        return sum;
    }
    
    public static int calculateHotelProfit()
    {
        return DataHub.calculateHotelProfit(DataHub.selectedHotel);
    }

    public static int calculateMonthTotalProfit()
    {
        int sum = 0;
        for(int i=0; i<DataHub.hotels.size(); i++)
        {
            sum += DataHub.calculateHotelProfit(hotels.get(i));
        }
        return sum;
    }

    public static ArrayList<HotelEvent> sort(ArrayList<HotelEvent> a, int type)
    {
        return a;
    }

    public static void open()
    {
        try {
            File f = new File("./allData.txt");
            RandomAccessFile reader = new RandomAccessFile(f, "rw");

            try{
                while(reader.getFilePointer() < reader.length())
                {
                    String line = reader.readLine();
                    
                    if(line.startsWith("Hotel: "))
                    {
                        String name = line.substring(7, line.indexOf("*|*")).trim();
                        String abbr = line.substring(line.indexOf("*|*") + 3).trim();
                        DataHub.addHotel(name, abbr);
                    }
                    if(line.contains("Event: "))
                    {
                        String name = line.substring(line.indexOf("Event: ") + 7, line.indexOf("*||*")).trim();
                        line = line.substring(line.indexOf("*||*") + 4).trim();
                        String details = line.substring(0, line.indexOf("*||*"));
                        line = line.substring(line.indexOf("*||*") + 4).trim();
                        String sDate = line.substring(0, line.indexOf("*||*"));
                        line = line.substring(line.indexOf("*||*") + 4).trim();
                        String eDate = line.substring(0, line.indexOf("*||*"));
                        line = line.substring(line.indexOf("*||*") + 4).trim();
                        String cost = line.substring(0, line.indexOf("*||*"));
                        line = line.substring(line.indexOf("*||*") + 4).trim();
                        String revenue = line.substring(0, line.indexOf("*||*"));
                        line = line.substring(line.indexOf("*||*") + 4).trim();
                        String type = line.trim();

                        DataHub.selectedHotel.addHotelEvent(name, details, Integer.parseInt(
                            sDate.replaceAll(" ", "")),
                            Integer.parseInt(eDate.replaceAll(" ", "")), Integer.parseInt(cost.trim()), 
                            Integer.parseInt(revenue.trim()), Integer.parseInt(type));
                    }
                }
                
                reader.setLength(0);
                reader.close();
            }
            catch(IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();    
            }
        } 
        catch (FileNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void save()
    {
        try {
            File f = new File("./allData.txt");
            FileWriter writer = new FileWriter(f, true);
            PrintWriter printer = new PrintWriter(writer);

            for(Hotel h : DataHub.hotels)
            {
                printer.println("Hotel: " + h.getName() + "*|*" + h.getAbbr());

                for(HotelEvent e: h.getEvents())
                {
                    printer.println("\tEvent: " + e.getName() + "*||*" + e.getDetails() + "*||*" + e.getsDate() + "*||*" + 
                                e.geteDate() + "*||*" + e.getData()[0] + "*||*" + e.getData()[1] + "*||*" + e.getType());
                }
            }

            printer.close();
        } catch (IOException event) {
            // TODO Auto-generated catch block
            event.printStackTrace();
        }
    }
}
