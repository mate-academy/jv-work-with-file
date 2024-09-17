package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ONE = 1;
    private static final int ZERO = 0;

    public String getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[ZERO].equals("supply")) {
                    supply += Integer.parseInt(split[ONE]);
                } else if (split[ZERO].equals("buy")) {
                    buy += Integer.parseInt(split[ONE]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        String resultString = ("supply," + supply + "\r\n"
                + "buy," + buy + "\r\n"
                + "result," + (supply - buy));

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(resultString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
        return resultString;
    }
}
