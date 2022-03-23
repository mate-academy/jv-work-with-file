package core.basesyntax;

import java.io.*;


public class WorkWithFile {
    public static void main(String[] args) {  //delete main method
        WorkWithFile example = new WorkWithFile();
        example.getStatistic("apple.csv", "example");
    }

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            String [] array = stringBuilder.toString().split(" ");
            int supply = 0;
            int buy = 0;
            for (String position:array) {
                int amount = Integer.parseInt(position.substring(position.indexOf(',') + 1));
                if(position.charAt(0) == 's'){
                    supply += amount;
                } else {
                    buy += amount;
                }
            }
            int res = supply - buy;
            String supplyString = "supply," + supply;
            String buyString = "buy," + buy;
            String result = "result," + res;

            String[] reportArray = {supplyString, buyString, result};
            for (String reportLine:reportArray) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
                bufferedWriter.write(reportLine + "\n");
                bufferedWriter.close();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException("File can`t be read", e);
        } catch (IOException e) {
            throw new RuntimeException("Line can`t be read", e);
        }
    }
}
