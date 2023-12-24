import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class StatsLineChart extends Application {
    private Stage stage;
 
    @Override public void start(Stage st) {
        this.stage = st;
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day");       
        
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Costs, Revenue, and Profit");
                                
        XYChart.Series costSeries = new XYChart.Series();
        costSeries.setName("Cost");

        XYChart.Series revenueSeries = new XYChart.Series();
        revenueSeries.setName("Revenue");

        XYChart.Series profitSeries = new XYChart.Series();
        profitSeries.setName("Profit");

        if(DataHub.selectedHotel!= null)
        for(int i=0; i<DataHub.selectedHotel.getEvents().size(); i++)
        {
            costSeries.getData().add(new XYChart.Data(
                    DataHub.selectedHotel.getEvents().get(i).geteDate(), 
                    DataHub.selectedHotel.getEventData().get(i)[0]));
            revenueSeries.getData().add(new XYChart.Data(
                    DataHub.selectedHotel.getEvents().get(i).geteDate(), 
                    DataHub.selectedHotel.getEventData().get(i)[1]));
            profitSeries.getData().add(new XYChart.Data(
                    DataHub.selectedHotel.getEvents().get(i).geteDate(), 
                    DataHub.selectedHotel.getEventData().get(i)[2]));
        }
        
        
        Scene scene  = new Scene(lineChart,400,300);
        lineChart.getData().add(costSeries);
        lineChart.getData().add(revenueSeries);
        lineChart.getData().add(profitSeries);
        
        stage.setScene(scene);
        stage.show();
    }
}