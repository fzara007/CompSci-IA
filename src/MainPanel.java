import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MainPanel extends JPanel implements ActionListener{
    // the side bar that holds the hotels and shows which is selected
    private JPanel sidePanel = new JPanel(new BorderLayout());
    private JPanel hBtns = new JPanel();

    private ArrayList<CustomButton> allHBtns = new ArrayList<CustomButton>();
    
    // just to hold the add and remove
    private JPanel btnPanel = new JPanel();
    private CustomButton addHotelBtn = new CustomButton("Add");
    private CustomButton removeHotelBtn = new CustomButton("Remove");
    
    // holds my toggle and the actual display panels
    private JTabbedPane displayToggle = new JTabbedPane();
    
    private StatsPanel sPanel;
    private CalendarPanel cPanel = new CalendarPanel(this);

    private JPanel content = new JPanel(new BorderLayout());

    public MainPanel(String[] arg)
    {
        this.setLayout(new GridLayout(1,1));

        sPanel = new StatsPanel(arg);

        this.displayToggle.setFont(new Font(this.getFont().getName(), Font.PLAIN, 20));

        this.setSize(750,750);

        // actionlistners to buttons
        this.addHotelBtn.addActionListener(this);
        this.removeHotelBtn.addActionListener(this);

        // setting up add and remove buttons for the hotels as well as the panel to hold hotels
        this.btnPanel.setLayout(new BoxLayout(this.btnPanel, BoxLayout.X_AXIS));
        this.btnPanel.add(this.addHotelBtn);
        this.btnPanel.add(this.removeHotelBtn);

        TitledBorder tb = new TitledBorder("Hotels");
        tb.setTitleFont(new Font(this.getFont().getName(), Font.PLAIN, 23));
        this.sidePanel.setBorder(tb);
        this.sidePanel.add(btnPanel, BorderLayout.SOUTH);
        this.sidePanel.add(this.hBtns, BorderLayout.CENTER);


        //setting up display panel with toggle bar and necessary buttons 
        this.displayToggle.add("Calendar", this.cPanel);
        this.displayToggle.add("Statistics", this.sPanel);
        
        //setting up the main panel by adding necessary components 
        this.content.setLayout(new BorderLayout());
        this.content.add(this.sidePanel, BorderLayout.WEST);
        this.content.add(this.displayToggle, BorderLayout.CENTER);

        this.add(this.content);
        this.redo();
    }


    public void redo()
    {
        this.hBtns.removeAll();
        this.allHBtns.clear();
        this.hBtns.setLayout(new GridLayout(this.allHBtns.size(), 1));

        if(DataHub.getAllHotels().size() == 0) this.removeHotelBtn.setEnabled(false);
        else this.removeHotelBtn.setEnabled(true);
        this.cPanel.redo();
        this.sPanel.redo();
        for(int hot=0; hot<DataHub.getAllHotels().size(); hot++)
        {
            System.out.println(DataHub.getAllHotels());
            CustomButton btn = new CustomButton(DataHub.getHotel(DataHub.getAllHotels().get(hot)));
            btn.addActionListener(this);
            btn.setBackground(Color.LIGHT_GRAY);
            
            this.allHBtns.add(btn);
            this.hBtns.add(btn);
            btn.setSize(new Dimension(160, 50));
        }
        this.repaint();
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.addHotelBtn))
        {
            // makes a popup to add new hotel
            new AddHotelPopup(this);
        }
        if(e.getSource().equals(this.removeHotelBtn))
        {
            // makes a popup to remove a hotel
            new RemoveHotelPopup(this);
        }
        for(int hBtn = 0; hBtn<this.allHBtns.size(); hBtn++)
        {
            if(e.getSource().equals(this.allHBtns.get(hBtn)))
            {
                System.out.println(this.allHBtns.size());
                DataHub.setHotel(this.allHBtns.get(hBtn).getText());
                
                this.redo();
                this.allHBtns.get(hBtn).setBackground(new Color(250, 190, 100));
            }
        }
        
    }

}

// https://github.com/fzara007/CompSci-IA.git