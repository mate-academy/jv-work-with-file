package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPTION = 0;
    private static final int VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        File file = new File(fromFileName);
        String[] fileContent = new String[(int) file.length()];
        if (file.length() != 0) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                int i = 0;
                while (line != null) {
                    fileContent[i] = line;
                    i++;
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't read the file", e);
            }
            for (String content : fileContent) {
                if (content != null) {
                    String[] currentLine = content.split(",");
                    if (currentLine[OPTION].equals("supply")) {
                        supply += Integer.parseInt(currentLine[VALUE]);
                    }
                    if (currentLine[OPTION].equals("buy")) {
                        buy += Integer.parseInt(currentLine[VALUE]);
                    }
                }
            }
        }
        String[] actualStatistic = new String[3];
        File resultFile = new File(toFileName);
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(resultFile, false))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }
}
