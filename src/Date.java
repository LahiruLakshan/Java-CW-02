import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Date {


    private static Pattern datePattern;

    private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    public Date(){
        datePattern = Pattern.compile(DATE_PATTERN);
    }

    public boolean dateValidate(String startDate){

        Matcher dateMatcher = datePattern.matcher(startDate);

        if(dateMatcher.matches()){

            dateMatcher.reset();

            if(dateMatcher.find()){

                String startDay = dateMatcher.group(1);
                String startMonth = dateMatcher.group(2);
                int startYear = Integer.parseInt(dateMatcher.group(3));

                if (startDay.equals("31") &&
                        (startMonth.equals("4") || startMonth .equals("6") || startMonth.equals("9") ||
                                startMonth.equals("11") || startMonth.equals("04") || startMonth .equals("06") ||
                                startMonth.equals("09"))) {
                    return false;
                } else if (startMonth.equals("2") || startMonth.equals("02")) {
                    //leap year checker
                    if(startYear % 4==0){
                        if(startDay.equals("30") || startDay.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        if(startDay.equals("29")||startDay.equals("30")||startDay.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }else{
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
