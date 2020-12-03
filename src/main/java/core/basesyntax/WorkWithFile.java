package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    private static final String SPLIT_BY = ",";
    private int supple = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] rowInfo = line.split(SPLIT_BY);
                readFromRow(rowInfo);
            }
        } catch (IOException e) {
            System.out.println("File was not found");
        }
        writeFile(toFileName);
    }

    private void readFromRow(String[] row) {
        if (row[0].equals("buy")) {
            buy += Integer.parseInt(row[1]);
        } else {
            supple += Integer.parseInt(row[1]);
        }
    }

    private void writeFile(String toFileName) {
        List<List<String>> rows = Arrays.asList(
                Arrays.asList("supply", "" + supple),
                Arrays.asList("buy", "" + buy),
                Arrays.asList("result", "" + (supple - buy))
        );
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            for (List<String> rowData : rows) {
                fileWriter.append(String.join(",", rowData));
                fileWriter.append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("File exist");
        }
    }
}
