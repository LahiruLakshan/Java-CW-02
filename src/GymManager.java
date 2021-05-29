public interface GymManager {

    void addNewMember(DefaultMember memberDetail);
    boolean deleteMember(String memberShipNumber);
    void printListOfMembers();
    void sortItems();
    void saveInFile();
    boolean duplicateCheck(String membershipNumber);

}
