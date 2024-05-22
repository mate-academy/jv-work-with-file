package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] resultFile = readFromFile(fromFileName);
        String report = createReport(resultFile);
        writeToFile(toFileName, report);
    }

    public String[] readFromFile(String fromFileName) {
        String[] dateFromLine;
        String str;
        int result;
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((str = bufferedReader.readLine()) != null) {
                if (str.contains("supply")) {
                    dateFromLine = str.split(",");
                    int numbers = Integer.parseInt(dateFromLine[1]);
                    supply += numbers;
                }
                if (str.contains("buy")) {
                    String[] values = str.split(",");
                    int money = Integer.parseInt(values[1]);
                    buy += money;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file ", e);
        }
        result = supply - buy;
        String[] resultFile = new String[3];
        resultFile[0] = "" + supply;
        resultFile[1] = "" + buy;
        resultFile[2] = "" + result;
        return resultFile;
    }

    public String createReport(String[] result) {
        String report = "supply," + result[0] + System.lineSeparator()
                + "buy," + result[1] + System.lineSeparator()
                + "result," + result[2];
        return report;
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file", e);
        }
    }
}
/*
try to not do everything in single method, lets use such pattern:
постарайтесь не делать все одним методом, давайте воспользуемся таким шаблоном:
String data = readFromFile(file);
String report = createReport(data);
writeToFile(file, report)
 */
