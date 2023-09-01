package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String readFromFileString = readFromFile(fromFileName);
        String[] makeReportArray = makeReport(readFromFileString);
        writeToFile(makeReportArray, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    private String[] makeReport(String input) {
        int supply = 0;
        int buy = 0;
        String[] inputArray = input.split(" ");
        for (int i = 0; i < inputArray.length; i++) {
            String[] splitWordAndDigit = inputArray[i].split(",");
            for (int j = 0; j < splitWordAndDigit.length; j++) {
                if (splitWordAndDigit[j].equals("supply")) {
                    j++;
                    supply += Integer.parseInt(splitWordAndDigit[j]);
                } else {
                    j++;
                    buy += Integer.parseInt(splitWordAndDigit[j]);
                }
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilderResult = new StringBuilder();
        stringBuilderResult.append("supply,").append(supply)
                .append(" buy,").append(buy).append(" result,").append(result);
        String resultString = stringBuilderResult.toString();
        String[] resultStringArray = resultString.split(" ");
        return resultStringArray;
    }

    private void writeToFile(String[] inputDate, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String word : inputDate) {
                bufferedWriter.write(word);
                bufferedWriter.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
