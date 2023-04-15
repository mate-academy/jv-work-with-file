package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    private File fileForRead;
    private File fileForWrite;

    void getStatistic(String fromFileName, String toFileName) {
        fileForRead = new File(fromFileName);
        fileForWrite = new File(toFileName);
        String dataString = readFile(fileForRead);
        String stringDataToFile = generateReport(dataString);
        writeToFile(fileForWrite, stringDataToFile);
    }

    private String readFile(File file) {
        StringBuilder stringBuilderForFilesData = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(fileForRead);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int valueResultMethod = bufferedReader.read();
            while (valueResultMethod != -1) {
                stringBuilderForFilesData.append((char) valueResultMethod);
                valueResultMethod = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + file.getName(), e);
        }
        return stringBuilderForFilesData.toString();
    }

    private String generateReport(String data) {
        int variableSupply = 0;
        int variableBuy = 0;
        int variableResult;
        String[] dataStrings = data.split(System.lineSeparator());
        String[] forWriteSolution;
        for (int a = 0; a < dataStrings.length; a++) {
            forWriteSolution = dataStrings[a].split(",");
            int b = Integer.valueOf(forWriteSolution[1]);
            if (forWriteSolution[0].equals(SUPPLY)) {
                variableSupply += b;
            } else if (forWriteSolution[0].equals(BUY)) {
                variableBuy += b;
            }
        }
        variableResult = variableSupply - variableBuy;
        return SUPPLY + "," + variableSupply + System.lineSeparator() + BUY
                + "," + variableBuy + System.lineSeparator() + RESULT + "," + variableResult;
    }

    private void writeToFile(File file, String dataForWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForWrite))) {
            bufferedWriter.write(dataForWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + file.getName(), e);
        }
    }
}
