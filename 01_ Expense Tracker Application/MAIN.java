
import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MAIN {

    // Short expense by date , ammount , catagory
    static void sortwise(int total_expense) {
        System.out.println("Enter Type of Sort: ");
        System.out.println("1 Date Wise     || 2. Ammount Wise ");
        System.out.println("3.Category Wise || ");
        int n = sc.nextInt();
        Map<String, ArrayList<Expense>> mp = new HashMap<String, ArrayList<Expense>>();
        if (n == 1) {
            for (int i = 0; i < total_expense; i++) {
                String[] a = arr.get(i).dat.split("-");
                String s = new String();
                for (String lp : a) {
                    s += lp.trim();
                }
                ArrayList<Expense> in = new ArrayList<>();
                if (mp.get(arr.get(i).dat) == null) {
                    in.add(arr.get(i));
                    mp.put(arr.get(i).dat.toString(), in);
                } else {
                    in = mp.get(arr.get(i).dat);
                    in.add(arr.get(i));
                    mp.put(arr.get(i).dat.toString(), in);
                }
            }
        } else if (n == 2) {
            for (int i = 0; i < total_expense; i++) {
                ArrayList<Expense> in = new ArrayList<>();
                if (mp.get(arr.get(i).ammount.toString()) == null) {
                    in.add(arr.get(i));
                    mp.put(arr.get(i).ammount.toString(), in);
                } else {
                    in = mp.get(arr.get(i).ammount.toString());
                    in.add(arr.get(i));
                    mp.put(arr.get(i).ammount.toString(), in);
                }
            }
        } else if (n == 3) {
            for (int i = 0; i < total_expense; i++) {
                ArrayList<Expense> in = new ArrayList<>();
                if (mp.get(arr.get(i).Catagory) == null) {
                    in.add(arr.get(i));
                    mp.put(arr.get(i).Catagory.toString(), in);
                } else {
                    in = mp.get(arr.get(i).Catagory);
                    in.add(arr.get(i));
                    mp.put(arr.get(i).Catagory.toString(), in);
                }
            }

        } else {
            System.out.println("Wrong Input");
        }
        for (Map.Entry<String, ArrayList<Expense>> en : mp.entrySet()) {
            ArrayList<Expense> lp = mp.get(en.getKey());
            for (int k = 0; k < lp.size(); k++) {
                System.out.println("\n-----------------------------");
                lp.get(k).getvalue();
                System.out.println("\n-----------------------------");
            }
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Expense> arr = new ArrayList<Expense>();

    // Addition Feature -----------------------
    static void view_monthly_expense(int total_expense, String Month) {
        int year = Year.now().getValue();
        try {

            for (int i = 0; i < total_expense; i++) {
                String[] str = arr.get(i).dat.split("-");
                if (str[1].trim().equalsIgnoreCase(Month) == true && Integer.parseInt(str[2].trim()) == year) {
                    System.out.println("-----------------------------\n");
                    arr.get(i).getvalue();
                    System.out.println("-----------------------------\n");
                }
            }
        } catch (Exception e) {
            System.out.println("i function");
        }

    }

    // Addition Feature Over -----------------------
    // ------------------------------- option 4 sub option 2 Total Expensive
    // ---------------
    static void catagory_wide_expenseive(int total_expense, String str) {
        int p = 0;
        for (int i = 0; i < total_expense; i++) {
            if (arr.get(i).Catagory.equalsIgnoreCase(str) == true) {
                System.out.println("------------------\n");
                arr.get(i).getvalue();
                System.out.println("------------------\n");
                p = 1;
            }
        }
        if (p == 0) {
            System.out.println("No Expensive Found !");
        }
    }

    // ------------------------------- option 4 sub option 2 Total Expensive
    // ---------------
    static void show_total_exp(int total_expense) {
        Map<String, Integer> map = new HashMap<>();// Map for Store set of all Differnce Catory
        for (int i = 0; i < total_expense; i++) {
            if (map.containsKey(arr.get(i).Catagory) == true) {
                map.put(arr.get(i).Catagory, arr.get(i).ammount + map.get(arr.get(i).Catagory));
            } else {
                map.put(arr.get(i).Catagory, arr.get(i).ammount);
            }
        }
        long total = 0;
        System.out.println("----------------------\nCatagory  " + "  Ammount");
        for (Map.Entry<String, Integer> me : map.entrySet()) {
            System.out.print(me.getKey() + "    :    ");
            System.out.println(me.getValue());
            total += me.getValue();
        }
        System.out.println("----------------------\nTotal Ammount :- " + total + "\n");
    }

    // -------------------------------- Commands --------------
    static void project_heart(int total_expense, long proid) {
        int loop = 1;
        int input;

        while (loop == 1) {

            System.out.println("Enter Your Commands:- ");
            System.out.println("1. Add Expense    || 2. View All Expenses");
            System.out.println("3. Delete Expense || 4. View Total and Category-wise Summary");
            System.out.println("5. Save and Exit  || 6. Additional Feature ");
            try {

                input = Integer.parseInt(sc.next());
            } catch (Exception e) {
                input = 8;
            }
            if (input == 1) {
                proid += 1;// Increse expense if before expense insert
                arr.add(new Expense());

                boolean b = arr.get(total_expense).setvalue(Math.toIntExact(proid)); // call The Function with id =
                                                                                     // proid
                if (b == false) {
                    arr.remove(total_expense);// if error accure they delete the error record
                    total_expense -= 1;
                }
                arr.get(total_expense);
                total_expense += 1; // after seccesful insert value index incerse to + 1

            } else if (input == 2) {
                try {

                    for (int i = 0; i <= total_expense; i++) {
                        if (i == 0) {
                            System.out.println("\n\n\n");
                            System.out.println("\n--------------------------------------------------------\n");
                        }
                        arr.get(i).getvalue();
                        System.out.println("\n--------------------------------------------------------\n");
                        if (i == total_expense) {
                            sc.nextLine();
                            System.out.println("\n\n\n");
                        }
                    }
                } catch (Exception e) {
                }
            } else if (input == 3) {
                // Delete The data
                System.out.println("Enter Expense Id For Delete:- ");
                int i = sc.nextInt();
                boolean com = false;
                for (int j = 0; j < total_expense; j++) {

                    if (i == arr.get(j).id) {
                        arr.remove(j);
                        System.out.println("\nExpense Deleted Success !\n");

                        total_expense -= 1;// decerese array list index by 1
                        com = false;
                        break;
                    } else {
                        com = true;
                    }

                }
                if (com == true) {
                    System.out.println("\"Expense not found\" if the ID isn't valid");

                }
            } else if (input == 4) {
                int op = 0;
                System.out.println("Enter Your Commands:- ");
                System.out.println("1. Total Catagory Expense || 2. Only Spacific Catagory Expense");
                op = sc.nextInt();
                if (op == 1) {
                    show_total_exp(total_expense);
                } else if (op == 2) {
                    sc.nextLine();
                    System.out.println("Enter Catagory To Find !");
                    String str = new String();
                    str = sc.nextLine();
                    catagory_wide_expenseive(total_expense, str);
                }

            } else if (input == 5) {
                // store array List data into file with ! mark with append mode
                try {
                    FileWriter file_Writer = new FileWriter("Expense_back.txt");
                    for (int i = 0; i < total_expense; i++) {
                        Integer j = i;
                        file_Writer.append(j.toString());
                        file_Writer.append(" ! ");
                        file_Writer.append(arr.get(i).id.toString());
                        file_Writer.append(" ! ");
                        file_Writer.append(arr.get(i).ammount.toString());
                        file_Writer.append(" ! ");
                        file_Writer.append(arr.get(i).Catagory.toLowerCase());
                        file_Writer.append(" ! ");
                        file_Writer.append(arr.get(i).Discription.toLowerCase());
                        file_Writer.append(" ! ");
                        file_Writer.append(arr.get(i).dat);
                        file_Writer.append("\n");
                    }
                    file_Writer.close();
                } catch (Exception e) {

                } finally {
                    loop = 0;
                }
            } else if (input == 6) {
                System.out.println("Enter Commands :- ");
                System.out.println("1. View Month Expense || 2.Update Expenses");
                System.out.println("3. Sort Expense       || ");
                int i = sc.nextInt();
                if (i == 1) {
                    sc.nextLine();
                    System.out.println("Enter Month: ");
                    String month = new String();
                    month = sc.nextLine();
                    view_monthly_expense(total_expense, month);
                } else if (i == 2) {
                    System.out.println("Enter Expense Id");
                    int num = sc.nextInt();
                    boolean b = false;
                    for (i = 0; i < arr.size(); i++) {
                        if (arr.get(i).id == num) {
                            arr.get(i).updateExpense();
                            System.out.println("Expense Update Succesfull !");
                            b = true;

                        }
                    }
                    if (b == false) {
                        System.out.println("Expense Update Not Succesfull !\nPlease TRY Again");
                    }
                } else if (i == 3) {

                    sortwise(total_expense);

                }
            }

            else {
                System.out.println("Wrong Input");
            }
        }

    }

    // ------------------------- load and save data
    public static void main(String[] args) {
        // if not file present it make it
        try {

            FileWriter fw = new FileWriter("Expense_back.txt", true);
            fw.close();
        } catch (Exception e) {

        }
        // if not file present it make it Over
        System.out.println("Welcome to  Expense Tracker Application ");
        int input = 0, loop = 1;

        int total_expense = 0; // it is index of arrayist
        long proid = total_expense; // it is id of expense
        // ----------------------- Load Previous Data
        try {
            File f = new File("Expense_back.txt");
            BufferedReader br = new BufferedReader(new FileReader("Expense_back.txt"));// RadFile

            if (f.length() > 0) {

                String s = new String();
                s = br.readLine();// Store File line by line content in string
                while (s != null) {

                    String[] ar = s.split("!");// Divide into String array if ! occurse
                    total_expense = Integer.parseInt(ar[0].trim());
                    arr.add(new Expense());
                    arr.get(total_expense).setvalue(Integer.parseInt(ar[1].trim()), Integer.parseInt(ar[2].trim()),
                            ar[3].trim(), ar[4].trim(), ar[5].trim()); // save file value in ArrayList
                    proid = Long.parseLong(ar[1].trim());
                    s = br.readLine();
                }
                total_expense += 1;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ----------------------- Load Previous Data Over
        project_heart(total_expense, proid);
    }
}
