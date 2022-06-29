package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION_INDEX = 0;
    private static final int MONEY_INDEX = 1;
    private static final String ACTION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] resultOfReadFile = readFile(fromFileName).split(" ");
        int actionBuy = 0;
        int actionSupply = 0;
        for (String lineFromFile : resultOfReadFile) {
            String[] splitLineFromFile = lineFromFile.split(",");
            // if ACTION is more we need use switch case?
            if (splitLineFromFile[ACTION_INDEX].equals(ACTION_BUY)) {
                actionBuy += Integer.parseInt(splitLineFromFile[MONEY_INDEX]);
            } else {
                actionSupply += Integer.parseInt(splitLineFromFile[MONEY_INDEX]);
            }
        }
        int result = actionSupply - actionBuy;
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("supply,")
                .append(actionSupply).append(System.lineSeparator())
                .append("buy,").append(actionBuy).append(System.lineSeparator())
                .append("result,").append(result);
        writeFile(resultStringBuilder.toString(), toFileName);
    }

    public String readFile(String nameFile) {
        File inputFile = new File(nameFile);
        StringBuilder buildInputData;
        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFile))) {
            buildInputData = new StringBuilder();
            String lineOfInputData = inputReader.readLine();
            while (lineOfInputData != null) {
                buildInputData.append(lineOfInputData).append(" ");
                lineOfInputData = inputReader.readLine();
            }
        } catch (IOException er) {
            throw new RuntimeException("Can't read or write  file",er);
        }
        return buildInputData.toString();
    }

    public void writeFile(String dataToFile, String nameFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(nameFile, true))) {
            try {
                bufferedWriter.write(dataToFile);
            } catch (IOException e) {
                throw new RuntimeException("Can't created file", e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
