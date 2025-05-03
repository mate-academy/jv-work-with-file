package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("The file cannot be opened for reading. File: "
                    + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private String createReport(String data) {
        String[] rows = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String row : rows) {
            String[] operation = row.split(",");
            if (operation[0].equals("supply")) {
                supply += Integer.parseInt(operation[1]);
            } else if (operation[0].equals("buy")) {
                buy += Integer.parseInt(operation[1]);
            }
        }
        int result = supply - buy;
        String report = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
        return report;
    }

    private void writeFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("The file cannot be opened for writing. File: "
                    + toFileName, e);
        }
    }
}
