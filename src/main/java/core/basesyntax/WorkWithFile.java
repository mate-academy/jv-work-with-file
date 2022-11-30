package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(new File(toFileName), makeReport(readLinesFromFile(fromFileName)));
    }

    private StringBuilder readLinesFromFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file ", e);
        }
        return stringBuilder;
    }

    private String[] makeReport(StringBuilder linesFromFile) {
        int supply = 0;
        int buy = 0;
        String[] resultString = linesFromFile.toString().split("\\W+");
        for (int i = 0; i < resultString.length; i++) {
            if (i % 2 == 0) {
                if (resultString[i].equals("supply")) {
                    supply += Integer.parseInt(resultString[i + 1]);
                } else {
                    buy += Integer.parseInt(resultString[i + 1]);
                }
            }
        }
        String[] result = {String.valueOf(supply), String.valueOf(buy),
                String.valueOf(supply - buy)};
        return result;
    }

    private void writeToFile(File toFile, String[] result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + result[0] + System.lineSeparator() + "buy," + result[1]
                    + System.lineSeparator() + "result," + result[2]);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
