import java.time.LocalDate;
import java.util.*;

public class Expense {
    static Scanner sc = new Scanner(System.in);
    Integer id = 0;
    Integer ammount = 0;
    String Catagory = new String();
    String Discription = new String();
    String dat = new String();

    // that is for when file data load into arraylist
    public void setvalue(int id, int ammount, String Catagory, String Discription, String dat) {
        this.id = id;
        this.ammount = ammount;
        this.Catagory = Catagory;
        this.Discription = Discription;
        this.dat = dat;
    }

    // Update Values
    void updateExpense() {
        int n = 0;
        while (true) {
            System.out.println("Enter Commands For Changes: ");
            System.out.println("1.Ammount     || 2.Catagory");
            System.out.println("3.Discription || 4.Date");
            System.out.println("5.Exit        || ");
            try {
                n = Integer.parseInt(sc.next());
            } catch (Exception e) {
            }
            if (n == 1) {

                System.out.print("Enter Expense Ammount: ");
                try {
                    ammount = Integer.parseInt(sc.next());
                } catch (Exception e) {
                    System.out.println("Wrong Input Try Again!");
                }
                sc.nextLine();
            } else if (n == 2) {

                System.out.print("Enter Expense Catagory: ");
                Catagory = sc.nextLine().toLowerCase();
            } else if (n == 3) {

                System.out.print("Enter Expense Discription: ");
                Discription = sc.nextLine().toLowerCase();
            } else if (n == 4) {

                String update = "";
                Integer upd = 0;
                System.out.println("Enter update Date Of Exense: ");
                try {
                    upd = Integer.parseInt(sc.next());
                } catch (Exception e) {
                    System.out.println("Wrong Input Try Again!");
                    continue;
                }
                if (upd <= 9) {
                    update += "0" + upd.toString() + " - ";
                } else {

                    update += upd.toString() + " - ";
                }
                System.out.println("Enter update Month of Expense: ");
                update += sc.next().toUpperCase() + " - ";
                System.out.println("Enter update Year of Expense: ");
                try {
                    upd = Integer.parseInt(sc.next());
                } catch (Exception e) {
                    System.out.println("Wrong Input Try Again!");
                    continue;
                }
                update += upd.toString();
                dat = update;
            } else if (n == 5) {
                return;
            } else {
                System.out.println("Wrong Input");
            }
        }
    }

    public boolean setvalue(int id) {
        // that is for when user choose to insert data
        System.out.print("Enter Expense Ammount: ");
        try {
            ammount = Integer.parseInt(sc.next());

        } catch (Exception e) {
            System.out.println("Wrong Input Try Again!");
            return false;
        }
        sc.nextLine();
        System.out.print("Enter Expense Catagory: ");
        Catagory = sc.nextLine().toLowerCase();
        System.out.print("Enter Expense Discription: ");
        Discription = sc.nextLine().toLowerCase();
        LocalDate currentDate = LocalDate.parse(java.time.LocalDate.now().toString());// d1 is Date class obj witch
                                                                                      // return current date
        Integer date = currentDate.getDayOfMonth();
        Integer year = currentDate.getYear();
        dat = date.toString() + " - " + currentDate.getMonth() + " - " + year.toString();
        this.id = id;
        return true;
    }

    public void getvalue() {
        System.out.println("Id:- " + id);
        System.out.println("Ammount:- " + ammount);
        System.out.println("Catagory:- " + Catagory);
        System.out.println("Discription:- " + Discription);
        System.out.println("Date:- " + dat);
    }
}
