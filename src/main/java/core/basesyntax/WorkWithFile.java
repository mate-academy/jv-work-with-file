package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);
        StringBuilder returnedStringToFile = new StringBuilder();
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] stringsFromLine = value.split(",");
                if (stringsFromLine[0].equals("supply")) {
                    supply += Integer.parseInt(stringsFromLine[1]);
                } else {
                    buy += Integer.parseInt(stringsFromLine[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write from file");
        }

        returnedStringToFile.append("supply,").append(supply).append("\n")
                .append("buy,").append(buy).append("\n")
                .append("result,").append(supply - buy);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            bufferedWriter.write(returnedStringToFile.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file");
        }
    }
}
