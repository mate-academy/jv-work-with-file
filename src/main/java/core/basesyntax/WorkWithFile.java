package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
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
                if (position.charAt(0) == 's') {
                    supply += amount;
                } else {
                    buy += amount;
                }
            }
            int res = supply - buy;
            String supplyString = "supply," + supply + System.lineSeparator();
            String buyString = "buy," + buy + System.lineSeparator();
            String result = "result," + res;
            String[] reportArray = {supplyString, buyString, result};
            for (String reportLine:reportArray) {
                bufferedWriter.write(reportLine);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File can`t be read", e);
        } catch (IOException e) {
            throw new RuntimeException("Line can`t be read", e);
        }
    }
}
