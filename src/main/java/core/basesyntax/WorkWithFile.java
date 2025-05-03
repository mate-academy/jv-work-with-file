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
    private static final int SUPPLY_IN_ARRAY = 0;
    private static final int BUY_IN_ARRAY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        int[] report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
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
        }
        return fileContent;
    }

    private int[] createReport(String[] fileContent) {
        int buy = 0;
        int supply = 0;
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
        return new int[]{supply, buy};
    }

    private void writeReportToFile(int[] report, String toFileName) {
        File resultFile = new File(toFileName);
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(resultFile, false))) {
            bufferedWriter.write("supply," + report[SUPPLY_IN_ARRAY]);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + report[BUY_IN_ARRAY]);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (report[SUPPLY_IN_ARRAY] - report[BUY_IN_ARRAY]));
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }
}
