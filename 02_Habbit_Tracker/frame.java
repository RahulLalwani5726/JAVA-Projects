
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

public class frame extends JFrame implements ActionListener {

    // Resize Image
    static ImageIcon Resize(ImageIcon i, int height, int width) {

        Image img = i.getImage().getScaledInstance(height, width, Image.SCALE_DEFAULT);

        return new ImageIcon(img);

    }
    // Resize Image over

    static boolean openNewWindow = true;
    static ArrayList<system> Data = new ArrayList<system>();
    static int HabbitNumber = 0;

    ImageIcon AddHabbitIcon = new ImageIcon("hab.png");
    ImageIcon CompleteHabbitIcon = new ImageIcon("complete.png");
    ImageIcon Show_DetailIcon = new ImageIcon("full.png");
    ImageIcon EditHabbitIcon = new ImageIcon("Edit.png");
    ImageIcon CMDIcon = new ImageIcon("Gui.png");
    ImageIcon DeleteHabbitIcon = new ImageIcon("Del.png");
    ImageIcon CostmiseBtnIcon = new ImageIcon("Edit.png");
    ImageIcon ExitIcon = new ImageIcon("exit.png");

    JFrame Frm = new JFrame();

    static Color FrameColor = Color.WHITE;
    static Color BtnColor = Color.LIGHT_GRAY;
    static Color TextColor = Color.BLACK;

    // JButton AddHabit = new JButton();
    JButton AddHabit = new JButton("AddHabit ");
    JButton CompleteHabbit = new JButton("Complete Habbit ");
    JButton Show_Detail = new JButton("Show Detail");
    JButton EditHabbit = new JButton("Edit Habbit");
    JButton CMD = new JButton("Back To Normal");
    JButton DeleteHabbit = new JButton("Delete Habbit");
    JButton CostmiseBtn = new JButton("Costimise Color");
    JButton Exit = new JButton("Exit");

    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();

    JLabel intro = new JLabel("Welcome to Habbit Tracker GUI Part ");
    JLabel intro2 = new JLabel("*** Version :- Beta ***");

    // Search by name
    static int SearchBYName(String s) {
        for (int i = 0; i < Data.size(); i++) {
            if (s.equalsIgnoreCase(Data.get(i).Habbit)) {
                return i;
            }
        }
        return -1;
    }
    // Search by name over

    // Loading Frame
    static void loading_frame() {
        // Create the frame and panels
        JFrame EXitFrame = new JFrame("Exit Frame");
        JPanel TextPanel = new JPanel();
        JPanel barPanel = new JPanel();
        JLabel text = new JLabel("Loading...");
        JProgressBar bar = new JProgressBar(0, 100);

        TextPanel.setBackground(FrameColor);
        TextPanel.setForeground(TextColor);

        barPanel.setForeground(TextColor);
        barPanel.setBackground(FrameColor);

        text.setBackground(FrameColor);
        text.setOpaque(true);
        text.setForeground(TextColor);

        // Set the text properties
        text.setVerticalTextPosition(JLabel.CENTER);
        text.setHorizontalTextPosition(JLabel.CENTER);
        text.setHorizontalAlignment(SwingConstants.CENTER); // Ensure text is centered
        text.setVerticalAlignment(SwingConstants.BOTTOM); // Ensure text is centered
        text.setFont(new Font("My Boli", Font.BOLD, 20));

        TextPanel.setLayout(new java.awt.BorderLayout()); // Use BorderLayout for easier centering
        TextPanel.add(text, java.awt.BorderLayout.CENTER);
        TextPanel.setPreferredSize(new Dimension(500, 150));

        // Set the progress bar properties
        bar.setPreferredSize(new Dimension(400, 50));
        bar.setStringPainted(true);
        bar.setBounds(35, 20, 400, 50);
        // Use a BoxLayout for easy vertical stacking of components
        barPanel.setLayout(null);
        barPanel.setPreferredSize(new Dimension(500, 300));

        barPanel.add(bar);

        // Add panels to the frame
        EXitFrame.setLayout(new java.awt.BorderLayout());
        EXitFrame.add(TextPanel, java.awt.BorderLayout.NORTH);
        EXitFrame.add(barPanel, java.awt.BorderLayout.CENTER);

        // Set the frame properties
        EXitFrame.setSize(500, 500);
        EXitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EXitFrame.setVisible(true);
        EXitFrame.setResizable(false);

        // Timer to update the progress bar
        Timer timer = new Timer(50, new ActionListener() {
            int value = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (value <= 100) {
                    value++;
                    bar.setValue(value);
                    bar.setString("Progress: " + value + "%");
                    if (value > 95) {
                        bar.setString("Almost Done!");
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
                    System.exit(0);
                }
            }
        });
        timer.start();
    }

    frame(boolean FrameAction) {
        // Data Load
        if (FrameAction) {

            System.out.println("You Are in GUI Mode\n");
            try {

                BufferedReader Reader = new BufferedReader(new FileReader("HabbitFile.txt"));
                String k = new String();
                HabbitNumber = 0;
                while (k != null) {
                    k = Reader.readLine();
                    String[] s = k.split("!");
                    Data.add(new system());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date1 = LocalDate.parse(s[4].trim(), formatter);
                    LocalDate date2 = LocalDate.now();
                    LocalDate date3 = LocalDate.now();
                    date3 = date3.minusDays(1);

                    if (!date1.equals(date3) && !date1.equals(date2)) {
                        Data.get(HabbitNumber).FileDataSet(HabbitNumber, s[1].trim(),
                                Integer.valueOf(0), Integer.parseInt(s[3].trim()), s[4].trim(), false);
                    } else {
                        Data.get(HabbitNumber).FileDataSet(HabbitNumber, s[1].trim(),
                                Integer.parseInt(s[2].trim()), Integer.parseInt(s[3].trim()), s[4].trim(), true);
                    }
                    HabbitNumber += 1;
                }
                Reader.close();

            } catch (Exception e) {
            }

            if (new File("FrameColor.txt").exists()) {

                try {

                    BufferedReader ReadColor = new BufferedReader(new FileReader("FrameColor.txt"));
                    String k;

                    int switching = 0;
                    int FrameSwitch = 0;
                    k = new String();

                    int r = 0, g = 0, b = 0;
                    while (k != null) {

                        k = ReadColor.readLine();
                        for (int i = 0; i < k.length(); i++) {

                            if (k.charAt(i) == ',') {
                                switching += 1;
                            }
                            if (Character.isDigit(k.charAt(i))) {

                                switch (switching) {
                                    case 0:
                                        r *= 10;
                                        r += Character.getNumericValue(k.charAt(i));
                                        break;
                                    case 1:
                                        g *= 10;
                                        g += Character.getNumericValue(k.charAt(i));
                                        break;
                                    case 2:
                                        b *= 10;
                                        b += Character.getNumericValue(k.charAt(i));
                                        break;
                                }
                            }

                        }
                        switch (FrameSwitch) {
                            case 0:
                                FrameColor = new Color(r, g, b);
                                break;
                            case 1:
                                BtnColor = new Color(r, g, b);
                                break;
                            case 2:
                                TextColor = new Color(r, g, b);
                                break;
                        }
                        FrameSwitch += 1;
                        r = 0;
                        g = 0;
                        b = 0;
                        switching = 0;

                    }
                    ReadColor.close();

                } catch (Exception e) {
                }
            }
        }
        JProgressBar[] BarArray = new JProgressBar[Data.size()];
        JPanel panel4[] = new JPanel[Data.size()];
        JLabel[] TextArray = new JLabel[Data.size()];

        AddHabit.setIcon(Resize(AddHabbitIcon, 90, 100));
        AddHabit.setFocusable(false);
        AddHabit.addActionListener(this);
        AddHabit.setFont(new Font("My Boli", Font.BOLD, 18));
        AddHabit.setForeground(TextColor);

        CompleteHabbit.setIcon(Resize(CompleteHabbitIcon, 45, 40));
        CompleteHabbit.setFocusable(false);
        CompleteHabbit.addActionListener(this);
        CompleteHabbit.setFont(new Font("My Boli", Font.BOLD, 18));
        CompleteHabbit.setForeground(TextColor);

        Show_Detail.setIcon(Resize(Show_DetailIcon, 85, 55));
        Show_Detail.setFocusable(false);
        Show_Detail.addActionListener(this);
        Show_Detail.setFont(new Font("My Boli", Font.BOLD, 18));
        Show_Detail.setForeground(TextColor);

        EditHabbit.setIcon(Resize(EditHabbitIcon, 45, 40));
        EditHabbit.setFocusable(false);
        EditHabbit.addActionListener(this);
        EditHabbit.setFont(new Font("My Boli", Font.BOLD, 18));
        EditHabbit.setForeground(TextColor);

        CMD.setIcon(Resize(CMDIcon, 45, 40));
        CMD.setFocusable(false);
        CMD.addActionListener(this);
        CMD.setFont(new Font("My Boli", Font.BOLD, 18));
        CMD.setForeground(TextColor);

        CostmiseBtn.setIcon(Resize(CostmiseBtnIcon, 45, 40));
        CostmiseBtn.setFocusable(false);
        CostmiseBtn.addActionListener(this);
        CostmiseBtn.setFont(new Font("My Boli", Font.BOLD, 18));
        CostmiseBtn.setForeground(TextColor);

        Exit.setIcon(Resize(ExitIcon, 45, 40));
        Exit.setFocusable(false);
        Exit.addActionListener(this);
        Exit.setFont(new Font("My Boli", Font.BOLD, 18));
        Exit.setForeground(TextColor);

        DeleteHabbit.setIcon(Resize(DeleteHabbitIcon, 45, 40));
        DeleteHabbit.setFocusable(false);
        DeleteHabbit.addActionListener(this);
        DeleteHabbit.setFont(new Font("My Boli", Font.BOLD, 18));
        DeleteHabbit.setForeground(TextColor);

        intro.setFont(new Font("My Boli", Font.BOLD, 18));
        intro2.setFont(new Font("My Boli", Font.BOLD, 20));

        panel2.setPreferredSize(new Dimension(500, 100));

        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

        panel3.removeAll();
        for (int i = 0; i < Data.size(); i++) {
            BarArray[i] = new JProgressBar(0, 30);
            TextArray[i] = new JLabel();
            panel4[i] = new JPanel();
            
            BarArray[i].setValue(Data.get(i).streak);
            BarArray[i].setStringPainted(true);
            BarArray[i].setFont(new Font("My Boli", Font.BOLD, 18));
            
            TextArray[i].setText(Data.get(i).Habbit);
            TextArray[i].setFont(new Font("My Boli", Font.BOLD, 18));
            TextArray[i].setSize(200, 30);
            TextArray[i].setForeground(TextColor);
        
            
            panel4[i].add(TextArray[i]);
            panel4[i].add(BarArray[i]);
            panel4[i].setBackground(FrameColor);
            
            panel3.add(panel4[i]);
            panel3.setBackground(FrameColor);
        }
        JScrollPane scBar = new JScrollPane(panel3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scBar.setBackground(FrameColor);

        panel3.setBackground(FrameColor);

        AddHabit.setBackground(BtnColor);
        CompleteHabbit.setBackground(BtnColor);
        Show_Detail.setBackground(BtnColor);
        EditHabbit.setBackground(BtnColor);
        DeleteHabbit.setBackground(BtnColor);
        CMD.setBackground(BtnColor);
        CostmiseBtn.setBackground(BtnColor);
        Exit.setBackground(BtnColor);

        panel.add(AddHabit);
        panel.add(CompleteHabbit);
        panel.add(Show_Detail);
        panel.add(EditHabbit);
        panel.add(DeleteHabbit);
        panel.add(CMD);
        panel.add(CostmiseBtn);
        panel.add(Exit);
        // panel.setSize(500, 300);
        panel.setPreferredSize(new Dimension(500, 300));
        panel.setLayout(new GridLayout(4, 2));

        // panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(intro);
        panel2.add(intro2);

        intro.setForeground(TextColor);
        intro2.setForeground(TextColor);

        panel.setBackground(FrameColor);
        panel.setOpaque(true);
        panel2.setBackground(FrameColor);
        scBar.setBackground(FrameColor);

        this.setLayout(new BorderLayout());
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);
        this.add(scBar, BorderLayout.CENTER);
        this.setSize(500, 700);
        // this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        // this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if (e.getSource() == AddHabit) {
            JFrame Show_D = new JFrame();
            JLabel name = new JLabel("Enter Habbit Name: ");
            JTextField field = new JTextField();
            JButton btn = new JButton("Submit");

            JPanel p = new JPanel();
            Show_D.setBackground(FrameColor);
            p.setBackground(FrameColor);
            name.setBackground(FrameColor);
            name.setOpaque(true);
            name.setForeground(TextColor);
            btn.setBackground(BtnColor);
            btn.setForeground(TextColor);

            name.setBounds(0, 0, 200, 100);
            name.setFont(new Font("My Boli", Font.BOLD, 20));
            field.setPreferredSize(new Dimension(100, 25));
            field.setVisible(true);
            Show_D.setResizable(false);
            p.add(name);
            p.add(field);
            p.add(btn);
            p.setPreferredSize(new Dimension(300, 300));
            Show_D.add(p);
            Show_D.setVisible(true);
            Show_D.setSize(300, 300);
            btn.addActionListener((ShowE) -> {
                if (field.getText().isBlank()) {
                    Show_D.dispose();
                    new JOptionPane().showMessageDialog(null, "Unable To AddHabbit!");
                    new frame(false);
                } else {

                    Show_D.dispose();
                    Data.add(new system());
                    Data.get(HabbitNumber).addHabit_For_Frame(HabbitNumber, field.getText());
                    HabbitNumber += 1;
                    new frame(false);
                }
            });
        } else if (e.getSource() == CompleteHabbit) {
            this.dispose();
            JFrame Show_D = new JFrame();
            JLabel name = new JLabel("Enter Habbit Name: ");
            JTextField field = new JTextField();
            JButton btn = new JButton("Submit");

            JPanel p = new JPanel();
            Show_D.setBackground(FrameColor);
            p.setBackground(FrameColor);
            name.setBackground(FrameColor);
            name.setOpaque(true);
            name.setForeground(TextColor);
            btn.setBackground(BtnColor);
            btn.setForeground(TextColor);

            name.setBounds(0, 0, 200, 100);
            name.setFont(new Font("My Boli", Font.BOLD, 20));

            field.setPreferredSize(new Dimension(100, 25));
            field.setVisible(true);
            field.setFont(new Font("My Boli", Font.BOLD, 15));

            Show_D.setResizable(false);
            p.add(name);
            p.add(field);
            p.add(btn);

            p.setPreferredSize(new Dimension(300, 300));
            Show_D.add(p);
            Show_D.setVisible(true);
            Show_D.setSize(300, 300);
            btn.addActionListener((ShowE) -> {
                int i = SearchBYName(field.getText().trim());
                Show_D.dispose();
                if (i >= 0) {

                    Data.get(i).CompleteHabbit();
                    JFrame EXitFrame = new JFrame("Complete Habbit Frame");
                    JPanel TextPanel = new JPanel();
                    JPanel barPanel = new JPanel();
                    JLabel text = new JLabel("Loading...");
                    JProgressBar bar = new JProgressBar(0, 100);

                    TextPanel.setBackground(FrameColor);
                    TextPanel.setForeground(TextColor);

                    barPanel.setForeground(TextColor);
                    barPanel.setBackground(FrameColor);

                    text.setBackground(FrameColor);
                    text.setOpaque(true);
                    text.setForeground(TextColor);
                    // Set the text properties
                    text.setVerticalTextPosition(JLabel.CENTER);
                    text.setHorizontalTextPosition(JLabel.CENTER);
                    text.setHorizontalAlignment(SwingConstants.CENTER); // Ensure text is centered
                    text.setVerticalAlignment(SwingConstants.BOTTOM); // Ensure text is centered
                    text.setFont(new Font("My Boli", Font.BOLD, 20));
                    TextPanel.setLayout(new java.awt.BorderLayout()); // Use BorderLayout for easier centering
                    TextPanel.add(text, java.awt.BorderLayout.CENTER);
                    TextPanel.setPreferredSize(new Dimension(500, 150));

                    // Set the progress bar properties
                    bar.setPreferredSize(new Dimension(400, 50));
                    bar.setStringPainted(true);
                    bar.setValue(50); // Initial value
                    bar.setBounds(35, 20, 400, 50);
                    // Use a BoxLayout for easy vertical stacking of components
                    barPanel.setLayout(null);
                    barPanel.setPreferredSize(new Dimension(500, 300));

                    barPanel.add(bar);

                    // Add panels to the frame
                    EXitFrame.setLayout(new java.awt.BorderLayout());
                    EXitFrame.add(TextPanel, java.awt.BorderLayout.NORTH);
                    EXitFrame.add(barPanel, java.awt.BorderLayout.CENTER);

                    // Set the frame properties
                    EXitFrame.setSize(500, 500);
                    EXitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    EXitFrame.setVisible(true);
                    EXitFrame.setResizable(false);
                    // Timer to update the progress bar
                    Timer timer = new Timer(25, new ActionListener() {
                        int value = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (value <= 100) {
                                value++;
                                bar.setValue(value);
                                bar.setString("Progress: " + value + "%");
                                if (value > 95) {
                                    bar.setString("Done!");
                                }
                            } else {
                                // Stop the timer once progress reaches 100%
                                ((Timer) e.getSource()).stop();
                                // Close the app after a short delay
                                try {
                                    Thread.sleep(500);
                                    new frame(false);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                EXitFrame.dispose();

                            }
                        }
                    });
                    timer.start();
                } else {

                    new frame(false);
                    JFrame f = new JFrame();
                    JLabel notFound = new JLabel("Habbit Not Found 404!");

                    notFound.setBackground(FrameColor);
                    notFound.setForeground(TextColor);
                    notFound.setOpaque(true);
                    f.setVisible(true);
                    f.setSize(400, 400);
                    f.setLayout(new java.awt.BorderLayout());
                    f.add(notFound, java.awt.BorderLayout.CENTER);
                    notFound.setHorizontalAlignment(SwingConstants.CENTER);
                    notFound.setVerticalAlignment(SwingConstants.CENTER);

                    notFound.setBounds(0, 0, 200, 100);
                    notFound.setFont(new Font("My Boli", Font.BOLD, 20));
                }
            });
        } else if (e.getSource() == Show_Detail) {
            this.dispose();
            JFrame Show_D = new JFrame();
            JLabel name = new JLabel("Enter Habbit Name: ");
            JTextField field = new JTextField();
            JButton btn = new JButton("Submit");

            JPanel p = new JPanel();
            Show_D.setBackground(FrameColor);
            p.setBackground(FrameColor);
            name.setBackground(FrameColor);
            name.setOpaque(true);
            name.setForeground(TextColor);
            btn.setBackground(BtnColor);
            btn.setForeground(TextColor);
            p.setPreferredSize(new Dimension(300, 300));

            name.setBounds(0, 0, 200, 100);
            name.setFont(new Font("My Boli", Font.BOLD, 20));

            field.setPreferredSize(new Dimension(100, 25));
            field.setVisible(true);
            field.setFont(new Font("My Boli", Font.BOLD, 15));

            Show_D.setResizable(false);
            p.add(name);
            p.add(field);
            p.add(btn);
            Show_D.add(p);

            Show_D.setVisible(true);
            Show_D.setSize(300, 300);

            btn.addActionListener((ShowE) -> {
                int i = SearchBYName(field.getText().trim());
                Show_D.dispose();
                if (i >= 0) {
                    new frame(false);
                    Data.get(i).Show_Details_By_Frame();
                } else {
                    new frame(false);
                    JFrame f = new JFrame();
                    JLabel notFound = new JLabel("Habbit Not Found 404!");
                    f.setVisible(true);
                    f.setSize(400, 400);
                    f.setLayout(new java.awt.BorderLayout());
                    f.add(notFound, java.awt.BorderLayout.CENTER);
                    notFound.setHorizontalAlignment(SwingConstants.CENTER);
                    notFound.setVerticalAlignment(SwingConstants.CENTER);

                    notFound.setBounds(0, 0, 200, 100);
                    notFound.setFont(new Font("My Boli", Font.BOLD, 20));
                }
            });

        } else if (e.getSource() == EditHabbit) {
            this.dispose();
            JFrame Show_D = new JFrame();
            JLabel name = new JLabel("Enter Habbit Name: ");
            JTextField field = new JTextField();
            JButton btn = new JButton("Submit");

            JPanel p = new JPanel();
            Show_D.setBackground(FrameColor);
            p.setBackground(FrameColor);
            name.setBackground(FrameColor);
            name.setOpaque(true);
            name.setForeground(TextColor);
            btn.setBackground(BtnColor);
            btn.setForeground(TextColor);
            p.setPreferredSize(new Dimension(300, 300));

            name.setBounds(0, 0, 200, 100);
            name.setFont(new Font("My Boli", Font.BOLD, 20));

            field.setPreferredSize(new Dimension(100, 25));
            field.setVisible(true);
            field.setFont(new Font("My Boli", Font.BOLD, 15));

            Show_D.setResizable(false);
            p.add(name);
            p.add(field);
            p.add(btn);
            Show_D.add(p);

            Show_D.setVisible(true);
            Show_D.setSize(300, 300);
            btn.addActionListener((ShowE) -> {
                int i = SearchBYName(field.getText().trim());
                Show_D.dispose();
                if (i >= 0) {
                    new frame(false);
                    Data.get(i).EditHabbit_FOR_Frame();
                } else {
                    new frame(false);
                    JFrame f = new JFrame();
                    JLabel notFound = new JLabel("Habbit Not Found 404!");

                    notFound.setBackground(FrameColor);
                    notFound.setForeground(TextColor);
                    notFound.setOpaque(true);

                    f.setVisible(true);
                    f.setSize(400, 400);
                    f.setLayout(new java.awt.BorderLayout());
                    f.add(notFound, java.awt.BorderLayout.CENTER);
                    notFound.setHorizontalAlignment(SwingConstants.CENTER);
                    notFound.setVerticalAlignment(SwingConstants.CENTER);

                    notFound.setBounds(0, 0, 200, 100);
                    notFound.setFont(new Font("My Boli", Font.BOLD, 20));
                }

            });

        } else if (e.getSource() == CMD) {
            this.dispose();
            try {

                FileWriter File = new FileWriter("HabbitFile.txt");
                for (int i = 0; i < Data.size(); i++) {
                    File.append(Integer.valueOf(i).toString() + " ! " + Data.get(i).Habbit + " ! "
                            + Data.get(i).streak.toString() + " ! " + Data.get(i).longStreak.toString() + " ! "
                            + Data.get(i).date.toString() + "\n");
                }
                File.close();
                JFrame EXitFrame = new JFrame("Complete Habbit Frame");
                JPanel TextPanel = new JPanel();
                JPanel barPanel = new JPanel();
                JLabel text = new JLabel("Loading...");
                JProgressBar bar = new JProgressBar(0, 100);

                TextPanel.setBackground(FrameColor);
                TextPanel.setForeground(TextColor);

                barPanel.setForeground(TextColor);
                barPanel.setBackground(FrameColor);

                text.setBackground(FrameColor);
                text.setOpaque(true);
                text.setForeground(TextColor);

                // Set the text properties
                text.setVerticalTextPosition(JLabel.CENTER);
                text.setHorizontalTextPosition(JLabel.CENTER);
                text.setHorizontalAlignment(SwingConstants.CENTER); // Ensure text is centered
                text.setVerticalAlignment(SwingConstants.BOTTOM); // Ensure text is centered
                text.setFont(new Font("My Boli", Font.BOLD, 20));
                TextPanel.setLayout(new java.awt.BorderLayout()); // Use BorderLayout for easier centering
                TextPanel.add(text, java.awt.BorderLayout.CENTER);
                TextPanel.setPreferredSize(new Dimension(500, 150));

                // Set the progress bar properties
                bar.setPreferredSize(new Dimension(400, 50));
                bar.setStringPainted(true);
                bar.setValue(50); // Initial value
                bar.setBounds(35, 20, 400, 50);
                // Use a BoxLayout for easy vertical stacking of components
                barPanel.setLayout(null);
                barPanel.setPreferredSize(new Dimension(500, 300));

                barPanel.add(bar);

                // Add panels to the frame
                EXitFrame.setLayout(new java.awt.BorderLayout());
                EXitFrame.add(TextPanel, java.awt.BorderLayout.NORTH);
                EXitFrame.add(barPanel, java.awt.BorderLayout.CENTER);

                // Set the frame properties
                EXitFrame.setSize(500, 500);
                EXitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                EXitFrame.setVisible(true);
                EXitFrame.setResizable(false);
                // Timer to update the progress bar
                Timer timer = new Timer(25, new ActionListener() {
                    int value = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (value <= 100) {
                            value++;
                            bar.setValue(value);
                            bar.setString("Progress: " + value + "%");
                            if (value > 95) {
                                bar.setString("Done!");
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
                            EXitFrame.dispose();
                        }
                    }
                });
                timer.start();
                this.dispose();

            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } else if (e.getSource() == Exit) {
            this.dispose();
            try {

                FileWriter File = new FileWriter("HabbitFile.txt");
                for (int i = 0; i < Data.size(); i++) {
                    File.append(Integer.valueOf(i).toString() + " ! " + Data.get(i).Habbit + " ! "
                            + Data.get(i).streak.toString() + " ! " + Data.get(i).longStreak.toString() + " ! "
                            + Data.get(i).date.toString() + "\n");
                }
                File.close();
                loading_frame();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (e.getSource() == DeleteHabbit) {
            this.dispose();
            JFrame Show_D = new JFrame();
            JLabel name = new JLabel("Enter Habbit Name: ");
            JTextField field = new JTextField();
            JButton btn = new JButton("Submit");

            JPanel p = new JPanel();
            Show_D.setBackground(FrameColor);
            p.setBackground(FrameColor);
            name.setBackground(FrameColor);
            name.setOpaque(true);
            name.setForeground(TextColor);
            btn.setBackground(BtnColor);
            btn.setForeground(TextColor);
            p.setPreferredSize(new Dimension(300, 300));

            name.setBounds(0, 0, 200, 100);
            name.setFont(new Font("My Boli", Font.BOLD, 20));

            field.setPreferredSize(new Dimension(100, 25));
            field.setVisible(true);
            field.setFont(new Font("My Boli", Font.BOLD, 15));

            Show_D.setResizable(false);
            p.add(name);
            p.add(field);
            p.add(btn);
            Show_D.add(p);

            Show_D.setVisible(true);
            Show_D.setSize(300, 300);

            btn.addActionListener((ShowE) -> {
                int i = SearchBYName(field.getText().trim());
                Show_D.dispose();
                if (i >= 0) {
                    Data.remove(i);
                    new frame(false);
                    JFrame EXitFrame = new JFrame("Exit Frame");
                    JPanel TextPanel = new JPanel();
                    JPanel barPanel = new JPanel();
                    JLabel text = new JLabel("Loading...");
                    JProgressBar bar = new JProgressBar(0, 100);

                    TextPanel.setBackground(FrameColor);
                    TextPanel.setForeground(TextColor);

                    barPanel.setForeground(TextColor);
                    barPanel.setBackground(FrameColor);

                    text.setBackground(FrameColor);
                    text.setOpaque(true);
                    text.setForeground(TextColor);

                    // Set the text properties
                    text.setVerticalTextPosition(JLabel.CENTER);
                    text.setHorizontalTextPosition(JLabel.CENTER);
                    text.setHorizontalAlignment(SwingConstants.CENTER); // Ensure text is centered
                    text.setVerticalAlignment(SwingConstants.BOTTOM); // Ensure text is centered
                    text.setFont(new Font("My Boli", Font.BOLD, 20));
                    TextPanel.setLayout(new java.awt.BorderLayout()); // Use BorderLayout for easier centering
                    TextPanel.add(text, java.awt.BorderLayout.CENTER);
                    TextPanel.setPreferredSize(new Dimension(500, 150));

                    // Set the progress bar properties
                    bar.setPreferredSize(new Dimension(400, 50));
                    bar.setStringPainted(true);
                    bar.setBounds(35, 20, 400, 50);
                    // Use a BoxLayout for easy vertical stacking of components
                    barPanel.setLayout(null);
                    barPanel.setPreferredSize(new Dimension(500, 300));

                    barPanel.add(bar);

                    // Add panels to the frame
                    EXitFrame.setLayout(new java.awt.BorderLayout());
                    EXitFrame.add(TextPanel, java.awt.BorderLayout.NORTH);
                    EXitFrame.add(barPanel, java.awt.BorderLayout.CENTER);

                    // Set the frame properties
                    EXitFrame.setSize(500, 500);
                    EXitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    EXitFrame.setVisible(true);
                    EXitFrame.setResizable(false);

                    // Timer to update the progress bar
                    Timer timer = new Timer(50, new ActionListener() {
                        int value = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (value <= 100) {
                                value++;
                                bar.setValue(value);
                                bar.setString("Progress: " + value + "%");
                                if (value > 95) {
                                    bar.setString("Almost Done!");
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
                                EXitFrame.dispose();
                            }
                        }
                    });
                    timer.start();

                } else {
                    new frame(false);
                    JFrame f = new JFrame();
                    JLabel notFound = new JLabel("Habbit Not Found 404!");

                    notFound.setBackground(FrameColor);
                    notFound.setForeground(TextColor);
                    notFound.setOpaque(true);

                    f.setVisible(true);
                    f.setSize(400, 400);
                    f.setLayout(new java.awt.BorderLayout());
                    f.add(notFound, java.awt.BorderLayout.CENTER);
                    notFound.setHorizontalAlignment(SwingConstants.CENTER);
                    notFound.setVerticalAlignment(SwingConstants.CENTER);

                    notFound.setBounds(0, 0, 200, 100);
                    notFound.setFont(new Font("My Boli", Font.BOLD, 20));
                }
            });

        } else if (e.getSource() == CostmiseBtn) {
            // this.dispose();

            JFrame frm = new JFrame();

            JPanel pnl1 = new JPanel();
            JPanel pnl2 = new JPanel();
            JPanel pnl3 = new JPanel();
            JLabel frmColor = new JLabel("Frame Color :-");
            JButton frmColorDemo = new JButton("Color");

            JLabel btnColor = new JLabel("Button Color :- ");
            JButton btnColorDemo = new JButton("Color");

            JLabel txtColor = new JLabel("Text Color :- ");
            JButton TextColorDemo = new JButton("Color");

            JColorChooser FrameColorChoose = new JColorChooser();
            JColorChooser BTnColorChoose = new JColorChooser();
            JColorChooser TextColorChoose = new JColorChooser();

            frmColorDemo.setSize(30, 30);
            frmColorDemo.addActionListener((ActionEvent ev) -> {
                FrameColor = FrameColorChoose.showDialog(null, "Sample Text", null);
            });
            btnColorDemo.setSize(30, 30);
            btnColorDemo.addActionListener((ActionEvent ev) -> {
                BtnColor = BTnColorChoose.showDialog(null, "Sample Text", Color.WHITE);
            });
            TextColorDemo.addActionListener((ActionEvent ev) -> {
                TextColor = TextColorChoose.showDialog(null, "Sample Text", Color.WHITE);
            });

            pnl1.setBackground(FrameColor);
            pnl2.setBackground(FrameColor);
            pnl3.setBackground(FrameColor);

            pnl1.setForeground(TextColor);
            pnl2.setForeground(TextColor);
            pnl3.setForeground(TextColor);

            JButton SubBtn = new JButton("Submit");
            SubBtn.setBackground(BtnColor);
            SubBtn.addActionListener((ActionEvent ev) -> {
                try {
                    FileWriter filewriter = new FileWriter("FrameColor.txt");
                    filewriter.append(FrameColor.toString());
                    filewriter.append("\n");
                    filewriter.append(BtnColor.toString());
                    filewriter.append("\n");
                    filewriter.append(TextColor.toString());
                    filewriter.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frm.dispose();

                // FrameColor.getColor

                // BtnColor = BTnColorChoose.getColor();

                openNewWindow = true;
                if (openNewWindow) {
                    new frame(false);
                }
            });

            frm.setSize(300, 400);
            frm.setVisible(true);
            frm.setLayout(new FlowLayout());

            frmColor.setBackground(FrameColor);
            frmColor.setOpaque(true);
            frmColor.setForeground(TextColor);

            btnColor.setBackground(FrameColor);
            btnColor.setOpaque(true);
            btnColor.setForeground(TextColor);

            txtColor.setBackground(FrameColor);
            txtColor.setOpaque(true);
            txtColor.setForeground(TextColor);

            frmColorDemo.setBackground(BtnColor);
            frmColorDemo.setForeground(TextColor);

            btnColorDemo.setBackground(BtnColor);
            btnColorDemo.setForeground(TextColor);

            TextColorDemo.setBackground(BtnColor);
            TextColorDemo.setForeground(TextColor);

            pnl1.add(frmColor);
            pnl1.add(frmColorDemo);

            pnl2.add(btnColor);
            pnl2.add(btnColorDemo);

            pnl3.add(txtColor);
            pnl3.add(TextColorDemo);

            frm.add(pnl1);
            frm.add(pnl2);
            frm.add(pnl3);
            frm.add(SubBtn);
        }
    }

}
