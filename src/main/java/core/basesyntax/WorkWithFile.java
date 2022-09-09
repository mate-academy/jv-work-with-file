package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE = 0;
    public static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        String example = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder dateWithFile = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                dateWithFile.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            String[] lines = dateWithFile.toString().split(System.lineSeparator());
            for (String line: lines) {
                String[] arrayLine = line.split(",");
                if (arrayLine[OPERATION_TYPE].equals("supply")) {
                    supply += Integer.valueOf(arrayLine[AMOUNT]);
                }
                if (arrayLine[OPERATION_TYPE].equals("buy")) {
                    buy += Integer.valueOf(arrayLine[AMOUNT]);
                }
            }
            result = supply - buy;
            example = "supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fromFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(example);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file" + toFileName, e);
        }
    }
}
