package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE_POSITION = 0;
    public static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String example = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder dateWithFile = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                dateWithFile.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            example = getExample(dateWithFile.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fromFileName, e);
        }
        getWriterWithFile(example, toFileName);
    }

    public void getWriterWithFile(String example, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(example);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file" + toFileName, e);
        }
    }

    public String getExample(String date) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        String[] lines = date.split(System.lineSeparator());
        for (String line: lines) {
            String[] arrayLine = line.split(",");
            if (arrayLine[OPERATION_TYPE_POSITION].equals("supply")) {
                supply += Integer.valueOf(arrayLine[AMOUNT_POSITION]);
            }
            if (arrayLine[OPERATION_TYPE_POSITION].equals("buy")) {
                buy += Integer.valueOf(arrayLine[AMOUNT_POSITION]);
            }
        }
        result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }
}
