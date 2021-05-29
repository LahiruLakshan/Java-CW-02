public class Over60Member extends DefaultMember {
    private int memberAge;

    public Over60Member(String membershipNumber, String name, String startMembershipDate, int memberAge) {
        super(membershipNumber, name, startMembershipDate);
        setMemberAge(memberAge);
    }



    public int getMemberAge(){
        return memberAge;
    }

    public void setMemberAge(int memberAge){
        if(memberAge >= 60){
            this.memberAge = memberAge;
        }
        else {
            System.out.println("Invalid age range!");

        }
    }
}
