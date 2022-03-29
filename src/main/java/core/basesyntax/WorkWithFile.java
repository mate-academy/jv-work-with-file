package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private StringBuilder builder = new StringBuilder();

    private String[] getReport(StringBuilder builder) {
        String[] splitLine = builder.toString().split(System.lineSeparator());
        int[] result = new int[] {0, 0};
        for (String str : splitLine) {
            String[] amounts = str.split(",");
            if (amounts[0].equals("supply")) {
                result[0] += Integer.parseInt(amounts[1]);
            } else {
                result[1] += Integer.parseInt(amounts[1]);
            }
        }
        String[] operationType = new String[] {"supply,", "buy,", "result,"};
        String[] spreadsheet = new String[3];
        spreadsheet [0] = operationType[0] + result[0];
        spreadsheet [1] = System.lineSeparator() + operationType[1] + result[1];
        spreadsheet [2] = System.lineSeparator() + operationType[2] + (result[0] - result[1]);
        return spreadsheet;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            bufferedReader.close();
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(toFileName, true))) {
                for (String finishSpreadsheet : getReport(builder)) {
                    bufferedWriter.write(finishSpreadsheet);
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't write file" + toFileName, e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

    }
}
