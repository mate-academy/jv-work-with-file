package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder saveDataToStringFromExistFile = new StringBuilder();

        try {
            BufferedReader readerFromExistFile = new BufferedReader(new FileReader(fromFileName));
            String value = readerFromExistFile.readLine();

            while (value != null) {
                saveDataToStringFromExistFile.append(value).append(" ");
                value = readerFromExistFile.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("File is not found ",e);
        }

        createReport(calculateValue(saveDataToStringFromExistFile), toFileName);

    }

    public int[] calculateValue(StringBuilder stringBuilderValues) {

        String[] splitSaveDatas = stringBuilderValues.toString().split("\\s+");
        int supply = 0;
        int buy = 0;
        int result;

        for (String splitSaveData : splitSaveDatas) {
            String[] splitValue = splitSaveData.split(",");
            String value = splitValue[0];
            int valueCount = Integer.parseInt(splitValue[1]);

            if (value.equals("supply")) {
                supply += valueCount;
            } else {
                buy += valueCount;
            }
        }
        result = supply - buy;

        return new int[]{supply, buy, result};
    }

    public void createReport(int[] result, String toFileName) {
        File file = new File(toFileName);
        String[] nameValues = {"supply", "buy", "result"};
        StringBuilder resultValuesString = new StringBuilder();

        for (int i = 0; i < nameValues.length; i++) {
            resultValuesString
                    .append(nameValues[i])
                    .append(",")
                    .append(result[i])
                    .append(System.lineSeparator());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(resultValuesString.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
