import javafx.application.Application;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private final static MyGymManager myGymManager = new MyGymManager();

    private final static Date date = new Date();

    public static int count = 0;
    private static boolean decision;
    private static int memberAge = 0;
    private static final Scanner input = new Scanner(System.in);

    private static String membershipNumber;
    private static String name;
    private static String startMembershipDate;
    private static String memberType;
    private static String schoolName;
    private static int weight = 0;

    public static void main(String[] args) throws Exception {

        MyGymManager.loadJsonMemberList();

        System.out.println("\n*********************************************** Welcome to GYM Management System ***********************************************");

        do {        //start Menue
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::: Menu Options :::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.println("\t\t1. Add a new member ");
            System.out.println("\t\t2. Delete a member");
            System.out.println("\t\t3. Print the list of members");
            System.out.println("\t\t4. Sort the item");
            System.out.println("\t\t5. Write/Save in a file");
            System.out.println("\t\t6. Open a Graphical User Interface");
            System.out.println("\t\t7. Exit the Programme");
            System.out.print("Select the number you want(1 to 7) : ");
            String select = input.next();

            switch (select) {       //choise menu

                case "1":
                    System.out.println("\n-------------------------------------------------------- Add a new member --------------------------------------------------------");
                    addNewMember();
                    break;

                case "2":
                    System.out.println("\n-------------------------------------------------------- Delete a member --------------------------------------------------------");
                    deleteMember();
                    break;

                case "3":
                    System.out.println("\n-------------------------------------------------------- List of members --------------------------------------------------------");
                    myGymManager.printListOfMembers();
                    break;

                case "4":
                    myGymManager.sortItems();
                    break;

                case "5":
                    myGymManager.saveInFile();
                    break;
                case "6":
                    Application.launch(GUIMemberList.class);
                    System.exit(0);
                    break;

                case "7":
                    System.out.println("\n******************************************************** End the Programme ********************************************************");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Input");
            }
            endDecision();
        } while (decision);
    }

    public static void endDecision() {          // programme continue or exit
        System.out.print("\n\tE - Exit the Programme\n\tC - Continue the programme\nChoose the decision you want : ");
        String choose = input.next();
        choose = choose.toLowerCase();
        if (choose.equals("e")){
            decision = false;
        }else if (choose.equals("c")){
            decision = true;
        }else {
            System.out.println("I can't understand your choose");
            Main.endDecision();
        }
    }

    public static void addNewMember() {         // add member method

        if (count < 100){

            inputMembershipNumber();        // Call method to enter member ID

            inputName();        // Call method to enter member name

            inputStartDate();       // Call method to enter member register date

            inputType();            // Call method to enter member type

        }else {
            System.out.println("Storage full!");
        }
    }

    public static void inputMembershipNumber() {
        System.out.print("\nEnter the membership number : ");       // input the membership ID
        membershipNumber = input.next();

        boolean check = myGymManager.duplicateCheck(membershipNumber); // check the membership ID(duplicate or non duplicate)
        if (!check){
            Main.inputMembershipNumber();
        }
    }

    public static void inputName() {

        System.out.print("Enter the member's name : ");     // input the membership name
        name = input.next();
        boolean check = nameValidation(name);
        if (!check){
            System.out.println("please input strings!");
            Main.inputName();
        }

    }
    public static void inputStartDate() {
        System.out.print("Enter the date in the following format DD/MM/YYYY : ");       // input the member register date
        startMembershipDate = input.next();
        boolean startMembershipDateCheck = date.dateValidate(startMembershipDate);
        if (!startMembershipDateCheck){
            System.out.println("Invalid Date");
            Main.inputStartDate();
        }
    }
    public static void inputType() {
        System.out.println("\tD - Default Member\n\tS - Student Member\n\tO - Over 60 Member");         // input the member type
        System.out.print("Enter the type of membership (\"D\" or \"S\" or \"O\") : ");
        memberType = input.next();
        memberType = memberType.toLowerCase();
        if (memberType.equals("d") || memberType.equals("s") || memberType.equals("o")) {
            selectType();
        }else {
            System.out.println("Invalid Input!\n");
            Main.inputType();
        }
    }
    public static void selectType(){        // member type input check
        DefaultMember memberDetail = null;

        switch (memberType){

            case "d":
                memberDetail = new DefaultMember(name, membershipNumber, startMembershipDate);
                break;

            case "S":
            case "s":
                inputSchoolName();
                inputWeight();
                memberDetail = new StudentMember(name, membershipNumber, startMembershipDate, schoolName, weight);
                break;

            case "O":
            case "o":
                inputAge();
                memberDetail = new Over60Member(name, membershipNumber, startMembershipDate, memberAge);
                break;

            default:
                System.out.println("Invalid Input!");
                decision = false;

        }
        myGymManager.addNewMember(memberDetail);
        count++;
    }

    public static void inputSchoolName() {
        System.out.print("Enter the member's school name : ");      // input the member school name
        schoolName = input.next();
        boolean check = nameValidation(schoolName);
        if (!check){
            System.out.println("please input strings!");
            inputName();
        }
    }


    public static void inputWeight() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the member's weight (kg) : ");      // input the member weight

        while (!input.hasNextInt()) {
            System.out.println("Integer needed");
            System.out.print("Enter the member's weight (kg) : ");
            input.nextLine();

        }
        weight = input.nextInt();
        if(!checkWeight(weight)){
            inputWeight();
        }
    }

    public static void inputAge() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the age of the member : ");
        while (!input.hasNextInt()) {
            System.out.println("Please enter integer value !");
            System.out.print("Enter the age of the member : ");
            input.nextLine();
        }

        memberAge = input.nextInt();
        if(!checkAge(memberAge)){
            inputAge();
        }


    }
    public static boolean checkWeight(int memberWeight) {
        if (memberWeight <= 10){
            System.out.println("Please enter valid range(more than 10) !");
            return false;
        }
        return true;
    }


    public static boolean checkAge(int memberAge) {
        if (memberAge <= 60){
            System.out.println("Please enter valid range(more than 60) !");
            return false;
        }
        return true;
    }


    public static boolean nameValidation(String name) {

        return Pattern.matches("[A-Za-z]+",name);
    }

    public static void deleteMember() {     //delete member option
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the membership number : ");
        String membershipNumber = sc.next();
        boolean outPut = myGymManager.deleteMember(membershipNumber);
        if (outPut){
            count--;
        }
    }
}
