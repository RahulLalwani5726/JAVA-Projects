import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Scanner;
import javax.swing.*;

public class system {
    Scanner sc = new Scanner(System.in);
    Integer streak = 0, longStreak = 0, HabbitNo = 0;
    Boolean complete = false;
    String Habbit = new String();
    LocalDate date;

    void addHabit(int i) {
        date = LocalDate.now();
        System.out.println("Enter Habbit: ");
        Habbit = sc.nextLine();
        HabbitNo = i;
    }

    void CompleteHabbit() {
        if (streak == 30) {
            streak = 0;
            System.out.println("YAY You Complete a Monthly Streak !!");
            sc.nextLine();
            sc.nextLine();
        } else {
            streak += 1;

        }
        this.date = LocalDate.now();
        if (longStreak < streak) {
            longStreak = streak;
        } else {
            longStreak += 1;
        }
        complete = true;
    }

    void Show_Details() {
        System.out.print(HabbitNo + ". " + Habbit + " \n");
        int progressPercentage = (int) ((streak / (double) 30) * 100);
        int blocksFilled = progressPercentage / 10; // Each block represents 10%
        int blocksEmpty = 10 - blocksFilled; // Total blocks is 10

        // Build progress bar string
        StringBuilder progressBar = new StringBuilder();
        for (int i = 0; i < blocksFilled; i++) {
            progressBar.append("|"); // Filled block
        }
        for (int i = 0; i < blocksEmpty; i++) {
            progressBar.append("-"); // Empty block
        }

        // Display progress bar and percentage
        System.out.println("Progress: [" + progressBar + "] " + progressPercentage + "%" + "\nCurrent Streak -> "
                + streak + " Days " + "\nLongest Streak -> " + longStreak + " Days");
    }

    void ShowGraph() {
        System.out.println("Habbit: " + Habbit);
        System.err.println("Today Complete: " + complete);
        System.err.println("Week Progress: ");
    }

    void FileDataSet(int HabbitNo, String Habbit, int streak, int longStreak, String date, boolean complete) {
        this.Habbit = Habbit;
        this.streak = streak;
        this.HabbitNo = HabbitNo;
        this.longStreak = longStreak;
        this.date = LocalDate.parse(date);
        this.complete = complete;
    }

    // GUI Part

    void Show_Details_By_Frame() {
        JFrame frame = new JFrame();
        JPanel pannel = new JPanel();
        JPanel pannel2 = new JPanel();
        JProgressBar bar = new JProgressBar(0, 30);
        JLabel Days = new JLabel("Current Streak:- " + streak);
        JLabel LongStreak = new JLabel("Longest Streak:- " + longStreak);
        JLabel Habb = new JLabel("Habbit Name:- " + Habbit);
        JLabel HabbNo = new JLabel("Habbit No:- " + HabbitNo);

        HabbNo.setVerticalAlignment(SwingConstants.CENTER);
        HabbNo.setHorizontalAlignment(SwingConstants.CENTER);


        HabbNo.setFont(new Font("My Boli", Font.BOLD, 20));
        Days.setFont(new Font("My Boli", Font.BOLD, 20));
        Habb.setFont(new Font("My Boli", Font.BOLD, 20));
        LongStreak.setFont(new Font("My Boli", Font.BOLD, 20));

        bar.setStringPainted(true);
        // bar.setPreferredSize(new Dimension(200,50));
        bar.setBounds(20,60,200,50);
        // bar.setBounds(50, 50, 200, 25);
        // bar.setBounds(0 , 100, 400, 100);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(pannel,BorderLayout.NORTH);
        panel3.add(pannel2,BorderLayout.CENTER);


        pannel.add(bar);
        // pannel2.setBounds(75, 80, 150, 250);
        pannel2.setPreferredSize(new Dimension(200,100));
        // pannel2.add(bar);
        pannel2.add(Habb);
        pannel2.add(Days);
        pannel2.add(LongStreak);
        pannel2.setLayout(new BoxLayout(pannel2, BoxLayout.Y_AXIS ));

        frame.setLayout(new BorderLayout());
        frame.add(HabbNo , BorderLayout.NORTH);
        frame.add(panel3 , BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(300,400);
        Timer timer = new Timer(10, new ActionListener() {
            int value = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (value < streak) {
                    value++;
                    bar.setValue(value);
                    // bar.setString("Progress: " + value + "%");
                    if(value >= 30){
                        bar.setString("Yay You Completed A Month Streak");
                    }
                } else {
                    // Stop the timer once progress reaches 100%
                    ((Timer) e.getSource()).stop();
                    // Close the app after a short delay
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        timer.start();

    }

    void addHabit_For_Frame(int HabbitNo , String Habbit){
        this.HabbitNo = HabbitNo;
        this.Habbit = Habbit;
        this.date = LocalDate.now();
    }

    void EditHabbit_FOR_Frame(){
        JFrame frame = new JFrame();

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel , BoxLayout.Y_AXIS));

        JPanel HNUmber = new JPanel();
        JPanel Hname = new JPanel();
        JPanel Stri = new JPanel();
        JPanel LStri = new JPanel();
        JPanel BarP = new JPanel();
        JButton Submit = new JButton("Submit");
       

        JLabel HAbb = new JLabel("Habbit :- ");
        HAbb.setFont(new Font("My Boli", Font.BOLD, 20));
        JLabel NOHAbb = new JLabel("Habbit NO :- ");
        NOHAbb.setFont(new Font("My Boli", Font.BOLD, 20));
        JLabel St = new JLabel("Streak :- ");
        St.setFont(new Font("My Boli", Font.BOLD, 20));
        JLabel LSt = new JLabel("Long Streak :- ");
        LSt.setFont(new Font("My Boli", Font.BOLD, 20));
        JLabel BarText = new JLabel("Monthly Progress :- ");
        BarText.setFont(new Font("My Boli" , Font.BOLD , 17));
        
        JTextField HabbitNUmber = new JTextField();
        HabbitNUmber.setFont(new Font("My Boli", Font.BOLD, 10));
        HabbitNUmber.setPreferredSize(new Dimension(200,30));
        HabbitNUmber.setText(this.HabbitNo.toString());
        HabbitNUmber.setEditable(false);

        JTextField Ha = new JTextField();
        Ha.setFont(new Font("My Boli", Font.BOLD, 15));
        Ha.setPreferredSize(new Dimension(200,30));
        Ha.setText(this.Habbit);

        JTextField STr = new JTextField();
        STr.setFont(new Font("My Boli", Font.BOLD, 15));

        STr.setPreferredSize(new Dimension(200,30));
        STr.setText(this.streak.toString());
        STr.setEditable(false);

        JTextField LStr = new JTextField();
        LStr.setFont(new Font("My Boli", Font.BOLD, 15));
        LStr.setPreferredSize(new Dimension(200,30));
        LStr.setText(this.longStreak.toString());
        LStr.setEditable(false);

        JProgressBar BAR = new JProgressBar(0,30);
        BAR.setStringPainted(true);
        BAR.setPreferredSize(new Dimension(200,50));

        HNUmber.add(NOHAbb);
        HNUmber.add(HabbitNUmber);

        Hname.add(HAbb);
        Hname.add(Ha);

        Stri.add(St);
        Stri.add(STr);

        LStri.add(LSt);
        LStri.add(LStr);

        BarP.add(BarText);
        BarP.add(BAR);

        Submit.addActionListener((ActionEvent e) ->{
            this.Habbit = Ha.getText();
            frame.dispose();
    
        });
        Submit.setFont(new Font("My Boli", Font.BOLD, 15));

        MainPanel.add(HNUmber);
        MainPanel.add(Hname);
        MainPanel.add(Stri);
        MainPanel.add(LStri);
        MainPanel.add(BarP);
        MainPanel.add(Submit);
        
        frame.setLayout(new BorderLayout());
        frame.add(MainPanel , BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(400,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Timer timer = new Timer(50, new ActionListener() {
            int value = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (value < streak) {
                    value++;
                    BAR.setValue(value);
                    BAR.setString("Progress: " + value + "%");
                    if(value >= 30+1){
                        BAR.setString("Yay You Completed A Month Streak");
                    }
                } else {
                   
                    ((Timer) e.getSource()).stop();
                 
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        timer.start();
    }
}

