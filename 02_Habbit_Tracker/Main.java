
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<system> Data = new ArrayList<system>();
    static int HabbitNumber = 0;

    static boolean Save_data() {
        try {

            FileWriter File = new FileWriter("HabbitFile.txt");
            for (int i = 0; i < Data.size(); i++) {
                File.append(Integer.valueOf(i).toString() + " ! " + Data.get(i).Habbit + " ! "
                        + Data.get(i).streak.toString() + " ! " + Data.get(i).longStreak.toString() + " ! "
                        + Data.get(i).date.toString() + "\n");
            }
            File.close();
        } catch (Exception exception) {
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Habbit Tracker-\n");
        if (new File("HabbitFile.txt").exists() == false) {
            try {
                new FileWriter("HabbitFile.txt").close();
            } catch (Exception e) {
            }
        }
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
        while (true) {
            for (int j = 0; j < Data.size(); j++) {
                System.out.print(j + ". " + Data.get(j).Habbit + " ");
                int progressPercentage = (int) ((Data.get(j).streak / (double) 30) * 100);
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
                System.out.println("Progress: [" + progressBar + "] " + progressPercentage + "%");
            }

            if (AppStart() == 3) {
                try {

                    FileWriter File = new FileWriter("HabbitFile.txt");
                    for (int i = 0; i < Data.size(); i++) {
                        File.append(Integer.valueOf(i).toString() + " ! " + Data.get(i).Habbit + " ! "
                                + Data.get(i).streak.toString() + " ! " + Data.get(i).longStreak.toString() + " ! "
                                + Data.get(i).date.toString() + "\n");
                    }
                    File.close();
                } catch (Exception e) {
                }
                break;
            }
        }

    }

    static void change(int i) {
        while (true) {
            System.out.println("Enter Your Commands:-");
            System.out.println("1. Habbit Name || 2. Important Label");
            int n = Integer.parseInt(sc.next());
            if (n == 1) {
                System.out.println("Enter Habbit Name");
                sc.nextLine();
                Data.get(i).Habbit = sc.nextLine();
            }

        }
    }

    // Loop Or Main Heart
    static int AppStart() {
        int action;
        System.out.println("\n\nEnter Your Commands: ");
        System.out.println("1. Add Habbit    || 2. Complete Habbit");
        System.out.println("3. Full Detail   || 4. Edit ");
        System.out.println("5. Delete Habbit || 6. GUI ");
        System.out.println("7. Exit          ||  ");
        try {
            action = Integer.parseInt(sc.next());
        } catch (Exception e) {

            System.out.print("\033[H\033[2J");
            System.out.flush();
            return 0;
        }
        switch (action) {
            case 1:

                Data.add(new system());
                Data.get(HabbitNumber).addHabit(Data.size() - 1);
                HabbitNumber += 1;
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            case 2:

                System.err.println("Enter Habbit No: ");
                try {
                    int n = Integer.parseInt(sc.next());
                    Data.get(n).CompleteHabbit();
                } catch (Exception e) {
                    System.out.println("Somting Went Wrong Try Again! ");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            case 7:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                return 3;

            case 3:
                System.err.println("Enter Habbit No: ");
                try {
                    int n = Integer.parseInt(sc.next());
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    Data.get(n).Show_Details();
                    Data.get(n).Show_Details_By_Frame();
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                } catch (Exception e) {
                    System.out.println("Somting Went Wrong Try Again! ");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
                sc.nextLine();
                sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;

            case 6:

                if (Save_data()) {
                    new frame(true);
                }
                sc.nextLine();
                sc.nextLine();
                break;
            
            case 5:
                    System.out.print("Enter Habbit No:- ");
                    int n = sc.nextInt();
                    if(n < Data.size() && !Data.isEmpty()){
                        Data.remove(n);
                        System.out.println("Habbit Deleted Successful!");
                    }
                    else{
                        System.out.println("Wrong Habbit No!");
                    }
                    sc.nextLine();
                    sc.nextLine();
                    break;
            default:
                System.out.println("Wrong Input!");
                sc.nextLine();
                sc.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }
        return 0;
    }
}
