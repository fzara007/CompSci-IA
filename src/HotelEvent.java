

public class HotelEvent {
    private String name;
    private String detail;
    private int startDate;
    private int endDate;
    private int cost;
    private int revenue;
    private int profit;
    // 1 - conference 2 - concert/sport 3 - miscellaneous
    private int type;

    // [cost, revenue, profit]
    private int[] data = new int[3];

    public HotelEvent(String n, String d, int sDate, int eDate,
                        int cost, int rev, int t)
    {
        this.name = n;
        this.detail = d;
        this.startDate = sDate;
        this.endDate = eDate;
        // this.duration = eda
        this.cost = cost;
        this.revenue = rev;
        this.profit = rev-cost;
        this.data[0] = this.cost;
        this.data[1] = this.revenue;
        this.data[2] = this.profit;
        this.type = t;
        System.out.println("Made an hotel event of name " + n);
    }
    
    public String getName()
    {
        return this.name;
    }
    public String getDetails()
    {
        return this.detail;
    }
    public String getsDate()
    {
        if(this.startDate/10 == 0)
            return "0" + this.startDate;
        return "" + this.startDate;
    }
    public String geteDate()
    {
        if(this.endDate/10 == 0)
            return "0" + this.endDate;
        return this.endDate+ "";
    }

    public int[] getData()
    {
        return this.data;
    }

    public int getType()
    {
        return this.type;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
