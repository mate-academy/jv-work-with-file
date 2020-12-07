package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    private static final String SPLIT_BY = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supple = 0;
        int buy = 0;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] rowInfo = line.split(SPLIT_BY);
                if (rowInfo[0].equals("buy")) {
                    buy += Integer.parseInt(rowInfo[1]);
                } else {
                    supple += Integer.parseInt(rowInfo[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("File was not found");
        }
        writeFile(toFileName, createReport(supple,buy));
    }

    private String createReport(int supple, int buy) {
        List<List<String>> rows = Arrays.asList(
                Arrays.asList("supply", "" + supple),
                Arrays.asList("buy", "" + buy),
                Arrays.asList("result", "" + (supple - buy))
        );
        StringBuilder report = new StringBuilder();
        for (List<String> rowData : rows) {
            report.append(String.join(",",rowData));
            report.append(System.lineSeparator());
        }
        return report.toString();
    }

    private void writeFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            System.out.println("File was not found");
        }
    }
}
