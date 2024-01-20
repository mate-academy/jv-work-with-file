package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SEPARATOR = " ";
    public static final String DELIMITER = ",";
    public static final int NAME = 0;
    public static final int VALUE = 1;
    public static final int SUPPLY = 0;
    public static final int BUY = 1;
    public static final int RESULT = 2;
    public static final int LINES_IN_REPORT = 3;
    public static final String[] NAME_VALUES = {"supply", "buy", "result"};

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName,report);
    }

    private String readFile(String filePath) {
        StringBuilder saveDataToStringFromExistFile = new StringBuilder();

        try {
            BufferedReader readerFromExistFile = new BufferedReader(new FileReader(filePath));
            String value = readerFromExistFile.readLine();

            while (value != null) {
                saveDataToStringFromExistFile.append(value).append(SEPARATOR);
                value = readerFromExistFile.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("File is not found ",e);
        }
        return saveDataToStringFromExistFile.toString();
    }

    public String generateReport(String data) {
        String[] splitSaveDatas = data.split("\\s+");
        int [] resultData = new int[LINES_IN_REPORT];

        for (String splitSaveData : splitSaveDatas) {
            String[] splitValue = splitSaveData.split(DELIMITER);
            String value = splitValue[NAME];
            int valueCount = Integer.parseInt(splitValue[VALUE]);

            if (value.equals("supply")) {
                resultData[SUPPLY] += valueCount;
            } else {
                resultData[BUY] += valueCount;
            }
        }
        resultData[RESULT] = resultData[SUPPLY] - resultData[BUY];
        StringBuilder resultValuesString = new StringBuilder();

        for (int i = 0; i < NAME_VALUES.length; i++) {
            resultValuesString
                    .append(NAME_VALUES[i])
                    .append(DELIMITER)
                    .append(resultData[i])
                    .append(System.lineSeparator());
        }
        return resultValuesString.toString();
    }

    public void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
