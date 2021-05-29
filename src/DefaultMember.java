import java.util.regex.Pattern;

public class DefaultMember implements Comparable<DefaultMember> {

    private String membershipNumber;
    private String name;
    private String startMembershipDate;
    Date date = new Date();
    MyGymManager myGymManager = new MyGymManager();

    public DefaultMember(String name, String membershipNumber, String startMembershipDate){
        super();
        setMembershipNumber(membershipNumber);
        setName(name);
        setStartMembershipDate(startMembershipDate);

    }

    public String getMembershipNumber(){
        return membershipNumber;
    }   // get membership no

    public void setMembershipNumber(String membershipNumber){
        boolean check = myGymManager.duplicateCheck(membershipNumber);
        if (!check){
            Main.inputMembershipNumber();
        }
        this.membershipNumber = membershipNumber;
    }

    public String getName(){
        return name;
    }// get member name

    public void setName(String name){       // set member name
        boolean check = nameValidation(name);
        if (!check){
            System.out.println("please input strings!");
            Main.inputName();
        }
        this.name = name;
    }

    public String getStartMembershipDate(){
        return startMembershipDate;
    }

    public void setStartMembershipDate(String startMembershipDate){
        boolean startMembershipDateCheck = date.dateValidate(startMembershipDate);
        if (!startMembershipDateCheck){
            System.out.println("Invalid Date");
            Main.inputStartDate();
        }
        this.startMembershipDate = startMembershipDate;
    }

    @Override
    public int compareTo(DefaultMember o) {
        return this.name.compareTo(o.getName());
    }
    public static boolean nameValidation(String name) {

        return Pattern.matches("[A-Za-z]+",name);
    }
}
