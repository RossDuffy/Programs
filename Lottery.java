import java.io.*;
import java.util.*;
public class Lottery {
    public static void main(String [] args)throws IOException{
        String date = /*"2018-01-10";*/  args [0];
        String type = /*"0";*/  args [1];
        String [] dateSplit = date.split("-");

        int day = Integer.parseInt(dateSplit[2]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[0]);

        boolean validDate = false;
        boolean isFuture = false;
        //System.out.println(date + " " + type);

        String pattern = "[0-2]";
        String datePat = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
        String record;
        String countPat = "";
        String nums = "";
        String tally = "";
        int tal = 0;

        String [] countPatSplit;
        String [] numSplit;

        GregorianCalendar cal = new GregorianCalendar();
        int toDay = cal.get(Calendar.DATE);
        int toMon = (cal.get(Calendar.MONTH)+1);
        int toYear = cal.get(Calendar.YEAR);
        //System.out.println(toDay +"-"+ toMon +"-"+ toYear);

/*-------------------------------------------------------------------------------------------*/



        if(date.matches(datePat))
            validDate = true;
            else
                System.out.println("Invalid date format");

        if(validDate){
            if(year > toYear) {
                isFuture = true;
            }else if(month > toMon && year == toYear) {
                isFuture = true;
            }else if(day > toDay && month == toMon && year == toYear) {
                isFuture = true;
            }

/*-------------------------------------------------------------------------------------------*/

            if(!(isFuture)){
                if(!(type.matches(pattern)))
                    System.out.println("Invalid draw type.");
                else {
                    File in = new File("Draws.txt");
                    Scanner inScan = new Scanner(in);

                    System.out.println("Analysis of the following draws:");

                    for (int i = 0; inScan.hasNextLine(); i++) {
                        record = inScan.nextLine();

                        String [] drawDate = record.split(",");
                        String [] drawDate2 = Arrays.copyOfRange(drawDate, 0, drawDate.length -1);
                        String [] drawDateSplit = drawDate[0].split("-");

                        int drawDay = Integer.parseInt(drawDateSplit[2]);
                        int drawMon = Integer.parseInt(drawDateSplit[1]);
                        int drawYear = Integer.parseInt(drawDateSplit[0]);

                        boolean isBefore = false;

                        if(year > drawYear) {
                            isBefore = true;
                        }else if(month > drawMon && year == drawYear) {
                            isBefore = true;
                        }else if(day > drawDay && month == drawMon && year == drawYear) {
                            isBefore = true;
                        }else if(day == drawDay && month == drawMon && year == drawYear){
                            isBefore = true;
                        }

                            if (record.endsWith(type) && (isBefore)) {
                                for(int j = 1, k = 2; j < (drawDate2.length)-1;){
                                    int temp1, temp2;
                                    String temp3;
                                    temp1 = Integer.parseInt(drawDate2[j]);
                                    temp2 = Integer.parseInt(drawDate2[k]);
                                    if(temp1 > temp2){
                                        temp3 = drawDate2[j];
                                        drawDate2[j] = drawDate2[k];
                                        drawDate2[k] = temp3;
                                        j = 1;
                                        k = 2;
                                    }else {
                                        j++;
                                        k++;
                                    }
                                }

                                for(int l = 0; l < drawDate2.length; l++) {
                                    System.out.print(drawDate2[l] + " ");
                                }

                                System.out.print("\n");
/*-----------------------------------------------------------------------------------------*/
                                for(int l = 1; l < drawDate2.length; l++){
                                        if(!(nums.contains(drawDate2[l])))
                                            countPat = countPat + drawDate2[l] + " ";
                                    nums = nums + drawDate2[l] + " ";
                                }

                                numSplit = nums.split(" ");
                                tally = "";
                                for(int l = 0; l < 50; l++){
                                    for(int m = 1; m < numSplit.length; m++) {
                                        int comp = Integer.parseInt(numSplit[m]);
                                        if (comp == l) {
                                            tal++;
                                            //System.out.println(tal);
                                        }
                                    }
                                    if(tal != 0){
                                        tally = tally + Integer.toString(tal) + " ";
                                        tal = 0;
                                    }
                                }

                                countPatSplit = countPat.split(" ");
                                for(int j = 1, k = 2; j < (countPatSplit.length)-1;){
                                    int temp1, temp2;
                                    String temp3;
                                    temp1 = Integer.parseInt(countPatSplit[j]);
                                    temp2 = Integer.parseInt(countPatSplit[k]);
                                    if(temp1 > temp2){
                                        temp3 = countPatSplit[j];
                                        countPatSplit[j] = countPatSplit[k];
                                        countPatSplit[k] = temp3;
                                        j = 1;
                                        k = 2;
                                    }else {
                                        j++;
                                        k++;
                                    }
                                }

                                countPat = "";
                                for(int j = 0; j < countPatSplit.length; j++)
                                    countPat = countPat + countPatSplit[j] + " ";
                        }
                    }

                    System.out.println("\nNumbers:\n" + countPat + "\nFrequency:\n" + tally);
                }
            }
        }else{
            System.out.println("Date supplied is in the future.");
        }
    }
}
