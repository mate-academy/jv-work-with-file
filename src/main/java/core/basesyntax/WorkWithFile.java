package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't create BufferedReader", e);
        }
        String dataString = builder.toString();
        String[] dataStrings = dataString.split("\r\n");
        String[] forSolution = new String[2];
        int supply = 0;
        int buy = 0;
        for (int a = 0; a < dataStrings.length; a++) {
            forSolution = dataStrings[a].split(",");
            int b = Integer.valueOf(forSolution[1]);
            if (forSolution[0].equals("supply")) {
                supply += b;
            } else if (forSolution[0].equals("buy")) {
                buy += b;
            }
        }
        int result = supply - buy;
        File fileForWrite = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForWrite))) {
            bufferedWriter.write("supply," + supply + "\r\n" + "buy," + buy + "\r\n"
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("can't create BufferedWriter", e);
        }
    }
}
