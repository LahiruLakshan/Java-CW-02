public class StudentMember extends DefaultMember {

    private String schoolName;
    private int weight;

    public StudentMember(String name, String membershipNumber, String startMembershipDate, String schoolName, int weight) {
        super(name, membershipNumber, startMembershipDate);
        setSchoolName(schoolName);
        setWeight(weight);

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if(weight >= 10){
            this.weight = weight;
        }
        else {
            System.out.println("Invalid age range!");

        }
        this.weight = weight;
    }

    public String getSchoolName(){
        return schoolName;
    }

    public void setSchoolName(String schoolName){
        boolean check = nameValidation(schoolName);
        if (!check){
            System.out.println("please input strings!");
            Main.inputName();
        }
        this.schoolName = schoolName;
    }


}
