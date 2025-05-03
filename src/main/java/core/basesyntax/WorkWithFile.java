package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buyReport = 0;
        int supplyReport = 0;
        String fileLineContent;
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            while ((fileLineContent = br.readLine()) != null) {
                str.append(fileLineContent).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] rows = str.toString().split(System.lineSeparator());
        for (String row : rows) {
            String[] record = row.split(",");
            if (record[0].equals("supply")) {
                supplyReport = supplyReport + Integer.parseInt(record[1]);
            } else if (record[0].equals("buy")) {
                buyReport = buyReport + Integer.parseInt(record[1]);
            }
        }
        File reportFile = new File(toFileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile))) {
            bw.write("supply," + supplyReport + System.lineSeparator()
                            + "buy," + buyReport + System.lineSeparator()
                            + "result," + (supplyReport - buyReport));
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }
}
