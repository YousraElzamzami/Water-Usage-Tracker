import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class WaterUsageTrackerGUI extends JFrame implements ActionListener {

    private final JLabel totalLabel;
    private final JLabel dailyLabel;
    private final JLabel weeklyLabel;
    private final JLabel monthlyLabel;

    private final JTextField inputField;
    private final JButton submitButton;

    private double totalWaterUsage = 0;
    private double dailyWaterUsage = 0;
    private double weeklyWaterUsage = 0;
    private double monthlyWaterUsage = 0;

    private static final int MAX_DAYS_IN_WEEK = 7;
    private static final int MAX_DAYS_IN_MONTH = 30;

    public WaterUsageTrackerGUI() {
        setTitle("Water Usage Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));
        setBackground(new Color(105, 198, 112));

              totalLabel = new JLabel("Total water usage: 0 gallons");
        dailyLabel = new JLabel("Daily water usage: 0 gallons");
        weeklyLabel = new JLabel("Weekly water usage: 0 gallons");
        monthlyLabel = new JLabel("Monthly water usage: 0 gallons");

        inputField = new JTextField(10);
        submitButton = new JButton("Submit");

               add(new JLabel("Enter today's water usage in gallons:"));
        add(inputField);
        add(submitButton);
        add(new JLabel());
        add(totalLabel);
        add(new JLabel()); 
        add(dailyLabel);
        add(new JLabel()); 
        add(weeklyLabel);
        add(new JLabel()); 
        add(monthlyLabel);
        add(new JLabel()); 

        submitButton.addActionListener(this);

        
        pack();
        setVisible(true);
    }

    
    private void updateWaterUsage(double todayWaterUsage) {

        totalWaterUsage += todayWaterUsage;
        dailyWaterUsage += todayWaterUsage;
        weeklyWaterUsage += todayWaterUsage;
        monthlyWaterUsage += todayWaterUsage;

        
        if (dailyWaterUsage > 0 && dailyWaterUsage % MAX_DAYS_IN_WEEK == 0) {
            dailyWaterUsage = 0;
        }

        
        if (weeklyWaterUsage > 0 && weeklyWaterUsage % MAX_DAYS_IN_MONTH == 0) {
            weeklyWaterUsage = 0;
        }

        
        DecimalFormat df = new DecimalFormat("#.##");
        totalLabel.setText("Total water usage: " + df.format(totalWaterUsage) + " gallons");
        dailyLabel.setText("Daily water usage: " + df.format(dailyWaterUsage) + " gallons");
        weeklyLabel.setText("Weekly water usage: " + df.format(weeklyWaterUsage) + " gallons");
        monthlyLabel.setText("Monthly water usage: " + df.format(monthlyWaterUsage) + " gallons");
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            double todayWaterUsage = Double.parseDouble(inputField.getText());
            updateWaterUsage(todayWaterUsage);
            JOptionPane.showMessageDialog(null, "Thank you for submitting your water usage for today! Keep up the good work in conserving water.");
            
            inputField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number for today's water usage.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WaterUsageTrackerGUI();
            }
        });
    }
}
