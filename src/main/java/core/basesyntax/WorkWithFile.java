package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public static final String SUPPLY_IDENTIFIER = "supply";
    public static final String BUY_IDENTIFIER = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileData = readFromFile(fromFileName);
        String report = createReport(fileData);
        writeToFile(toFileName, report);
    }

    public List<String> readFromFile(String fileName) {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringList.add(value);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        return stringList;
    }

    public String createReport(List<String> fileData) {
        String[] strArr;
        int supply = 0;
        int buy = 0;
        int result;
        for (String value : fileData) {
            strArr = value.split("\\W+");
            for (String stringValue : strArr) {
                if (stringValue.equals(SUPPLY_IDENTIFIER)) {
                    supply += Integer.parseInt(strArr[strArr.length - 1]);
                    break;
                } else if (stringValue.equals(BUY_IDENTIFIER)) {
                    buy += Integer.parseInt(strArr[strArr.length - 1]);
                    break;
                }
            }
        }
        result = supply - buy;
        return "supply," + supply + System.lineSeparator() + "buy," + buy
                + System.lineSeparator() + "result," + result;
    }

    public void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }
}
