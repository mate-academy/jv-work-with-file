package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileData = readFile(fromFileName).split(NEW_LINE);
        String[] resultData = prepareStingToWrite(fileData);
        clearFile(toFileName);
        writeData(resultData, toFileName);
    }

    private String readFile(String fromFileName) {
        String content = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content += line + NEW_LINE;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }
        return content;
    }

    private String[] prepareStingToWrite(String[] notGroupedData) {
        int supplySum = 0;
        int buySum = 0;
        int addition = 0;
        for (int i = 0; i < notGroupedData.length; i++) {
            String[] splitedArray = notGroupedData[i].split(COMMA);
            addition = Integer.parseInt(splitedArray[1]);
            if (splitedArray[0].equals(SUPPLY)) {
                supplySum += addition;
            }
            if (splitedArray[0].equals(BUY)) {
                buySum += addition;
            }
        }
        return new String[]{SUPPLY + COMMA + supplySum,
                BUY + COMMA + buySum,
                RESULT + COMMA + (supplySum - buySum)};
    }

    private void clearFile(String fileName) {
        File file = new File(fileName);
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a new file: " + fileName, e);
        }
    }

    private void writeData(String[] report, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String data : report) {
                try {
                    bufferedWriter.write(data);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException("The data hasn't been  written to file: "
                            + fileName, e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("The data hasn't been  written to file: "
                    + fileName, e);
        }
    }
}
