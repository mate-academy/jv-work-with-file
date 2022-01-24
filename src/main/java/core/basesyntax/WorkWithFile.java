package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class WorkWithFile {
    private static String[] OPERATION_TYPE_NAMES = new String[]{"supply", "buy", "result"};

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private void writeToFile(String fileName, String dateToWrite) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(dateToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + e);
        }
    }

    private String readFromFile(String fromFileName) {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        int[] sumOfOperations = new int[2];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            while ((line = bufferedReader.readLine()) != null) {
                String[] currentLine = line.split(",");
                if (currentLine[0].equals(OPERATION_TYPE_NAMES[0])) {
                    sumOfOperations[0] += Integer.parseInt(currentLine[1]);
                } else if (currentLine[0].equals(OPERATION_TYPE_NAMES[1])) {
                    sumOfOperations[1] += Integer.parseInt(currentLine[1]);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + e);
        }
        for (int a = 0; a < sumOfOperations.length; a++) {
            stringBuilder.append(OPERATION_TYPE_NAMES[a]).append(',')
                    .append(sumOfOperations[a]).append(System.lineSeparator());
        }
        stringBuilder.append(OPERATION_TYPE_NAMES[2]).append(",")
                .append(sumOfOperations[0] - sumOfOperations[1])
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
