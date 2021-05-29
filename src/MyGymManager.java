import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyGymManager implements GymManager {

    public static List<DefaultMember> memberDetailList = new ArrayList<DefaultMember>();

    @Override
    public void addNewMember(DefaultMember memberDetail) {

        if (memberDetailList.size() < 100) {
            memberDetailList.add(memberDetail);
            System.out.println("\nNo of occupied slots : " + memberDetailList.size());
            System.out.println("No of free slots : " + (100 - memberDetailList.size()));

        } else {
            System.out.println("No free slots are available for new members");
        }
    }

    @Override
    public boolean deleteMember(String memberShipNumber) {
        boolean check = false;
        for (DefaultMember memberDetail : memberDetailList) {
            if (memberDetail.getMembershipNumber().equals(memberShipNumber)) {
                check = true;
                memberDetailList.remove(memberDetail);
                System.out.println("Member with the membership Number " + memberShipNumber + " Successfully removed");

                if (memberDetail instanceof StudentMember) {
                    System.out.println("Member type is : StudentMember");
                } else if (memberDetail instanceof Over60Member) {
                    System.out.println("Member type is : Over60Member");
                } else {
                    System.out.println("Member type is : DefaultMember");
                }

                System.out.println("No of occupied slots : " + memberDetailList.size());
                System.out.println("No of free slots : " + (100 - memberDetailList.size()));
                break;
            }

        }
        if (!check) {
            System.out.println("Not found");
        }

        return check;
    }

    @Override
    public void printListOfMembers() {

        int memberCount = 0;
        for (DefaultMember memberDetail : memberDetailList) {
            memberCount++;
            System.out.print("\n(" + memberCount + "). ");
            System.out.print("Membership ID : " + memberDetail.getMembershipNumber() + "\t\t");
            if (memberDetail instanceof StudentMember) {
                System.out.print("Member type is : StudentMember");
            } else if (memberDetail instanceof Over60Member) {
                System.out.print("Member type is : Over60Member");
            } else {
                System.out.print("Member type is : DefaultMember");
            }
            System.out.print("\t\tName is : " + memberDetail.getName() + "\t");
            System.out.print("Membership start date is : " + memberDetail.getStartMembershipDate());
        }
        if (memberDetailList.size() == 0) {
            System.out.println("Empty list");

        }
    }

    @Override
    public void sortItems() {
        Collections.sort(memberDetailList);
        printListOfMembers();
    }

    @Override
    public void saveInFile() {
        try {
            File dataFile = new File("MembersDetails.txt");
            FileWriter dataWriter = new FileWriter(dataFile, false);
            dataWriter.write(":::::::::::::::::::::::::::::::::::::::::::::::::::::::: List of members ::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n\n");
            int memberCount = 0;
            for (DefaultMember memberDetail : memberDetailList) {
                memberCount++;
                dataWriter.write("(" + memberCount + "). ");
                dataWriter.write("Membership ID : " + memberDetail.getMembershipNumber() + "\t\t");
                if (memberDetail instanceof StudentMember) {
                    dataWriter.write("Member type is : StudentMember");
                } else if (memberDetail instanceof Over60Member) {
                    dataWriter.write("Member type is : Over60Member");
                } else {
                    dataWriter.write("Member type is : DefaultMember");
                }
                dataWriter.write("\t\tName is : " + memberDetail.getName() + "\t");
                dataWriter.write("Membership start date is : " + memberDetail.getStartMembershipDate() + "\n");
            }
            System.out.println("Member details saved to file successfully!");
            dataWriter.close();
            mainJson();
        } catch (Exception e) {
            System.out.println("No Data!");
        }
    }

    private void mainJson() throws IOException {
        File jsonFile = new File("jsonFile.json");
        FileWriter jsonWriter = new FileWriter(jsonFile);
        JSONArray jsonMembersList = new JSONArray();

        for (DefaultMember memberDetail : memberDetailList) {
            JSONObject jsonMember = new JSONObject();

            jsonMember.put("membershipID", memberDetail.getMembershipNumber());
            jsonMember.put("memberName", memberDetail.getName());
            jsonMember.put("membershipStartDate", memberDetail.getStartMembershipDate());
            jsonMember.put("memberType", "Default Member");

            if (memberDetail instanceof StudentMember) {
                jsonMember.put("memberSchool", ((StudentMember) memberDetail).getSchoolName());
                jsonMember.put("memberWeight", ((StudentMember) memberDetail).getWeight());
                jsonMember.put("memberType", "Student Member");

            } else if (memberDetail instanceof Over60Member) {
                jsonMember.put("memberAge", ((Over60Member) memberDetail).getMemberAge());
                jsonMember.put("memberType", "Over60 Member");
            }
            jsonMembersList.put(jsonMember);
        }

        jsonWriter.write(String.valueOf(jsonMembersList));
        jsonWriter.flush();
        jsonWriter.close();


    }

    public static void loadJsonMemberList() throws Exception {

        InputStream input = new FileInputStream("jsonFile.json");
        JSONTokener tokenID = new JSONTokener(input);
        JSONArray jsonMembersList = new JSONArray(tokenID);

        for (int i = 0; i < jsonMembersList.length(); i++) {

            Main.count = i + 1;
            JSONObject member = jsonMembersList.getJSONObject(i);
            String memberType = member.getString("memberType");
            String memberName = member.getString("memberName");
            String memberID = member.getString("membershipID");
            String membershipDate = member.getString("membershipStartDate");


            switch (memberType) {
                case "Default Member":
                    memberDetailList.add(new DefaultMember(memberName, memberID, membershipDate));
                    break;
                case "Student Member":
                    String memberSchoolName = member.getString("memberSchool");
                    int memberWeight = member.getInt("memberWeight");
                    memberDetailList.add(new StudentMember(memberName, memberID, membershipDate, memberSchoolName, memberWeight));
                    break;
                case "Over60 Member":
                    int memberAge = member.getInt("memberAge");
                    memberDetailList.add(new Over60Member(memberName, memberID, membershipDate, memberAge));
                    break;
            }
        }
    }


    @Override
    public boolean duplicateCheck(String membershipNumber) {
        boolean check = true;
        for (DefaultMember memberDetail : memberDetailList) {
            if (memberDetail.getMembershipNumber().equals(membershipNumber)) {
                System.out.println("duplicate id");
                check = false;
            }
        }
        return check;
    }
}


