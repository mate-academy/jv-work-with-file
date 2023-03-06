package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String fromFile = readFromFileName(fromFileName);
        String toFile = getResult(fromFile);
        writeToFile(toFile,toFileName);
    }

    private String readFromFileName(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuilder.append(readLine).append(System.lineSeparator());
                readLine = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fromFileName,e);
        }
    }

    private String getResult(String dateFromFile) {
        int supply = 0;
        int buy = 0;

        String[] lines = dateFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] arrayLine = line.split(",");
            if (arrayLine[OPERATION_INDEX].equals(SUPPLY)) {
                supply += Integer.valueOf(arrayLine[VALUE_INDEX]);
            }
            if (arrayLine[OPERATION_INDEX].equals(BUY)) {
                buy += Integer.valueOf(arrayLine[VALUE_INDEX]);
            }
        }
       int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String toFile, String fromFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fromFile))) {
            bufferedWriter.write(toFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file" + toFile, e);
        }
    }
}

