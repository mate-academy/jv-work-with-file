package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int []totals = {0,0};
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line, totals);
            }
            writeReport(writer, totals);
        } catch (IOException e) {
            handleException(e, fromFileName);
        }
    }

    private void processLine(String line, int [] totals) {
        String []values = line.split(",");
        if (values.length == 2) {
            String operationType = values[0].trim();
            int amount = Integer.parseInt(values[1].trim());
            if ("supply".equals(operationType)) {
                totals[0] += amount;
            } else if ("buy".equals(operationType)) {
                totals[1] += amount;
            }
        }
    }

    private void writeReport(BufferedWriter writer, int []totals) throws IOException {
        writer.write("supply" + "," + totals[0]);
        writer.write(System.lineSeparator());
        writer.write("buy" + "," + totals[1]);
        writer.write(System.lineSeparator());
        writer.write("result" + "," + (totals[0] - totals[1]));
    }

    private void handleException(IOException e, String fileName) {
        throw new RuntimeException("Can't read/write data to/from file " + fileName, e);
    }
}
