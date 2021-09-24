package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {

    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readingFile = readingFile(fromFileName);
        writeReport(readingFile, toFileName);

    }

    private String readingFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String fullLine = bufferedReader.readLine();
            while (fullLine != null) {
                stringBuilder.append(fullLine).append(System.lineSeparator());
                fullLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
        String[] dividedData = stringBuilder.toString().split("\n");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dividedData.length; i++) {
            String [] elements = dividedData[i].trim().split(COMMA);
            if (elements[0].equals("supply")) {
                supply += Integer.parseInt(elements[1]);
            } else if (elements[0].equals("buy")) {
                buy += Integer.parseInt(elements[1]);
            } else {
                throw new RuntimeException("Incorrect input data");
            }
        }
        int result = supply - buy;
        String finalRow = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
        return finalRow;
    }

    private void writeReport(String readingFile, String toFileName) {

        try {
            Files.write(Paths.get(toFileName), readingFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
