package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private String dataFromFile;
    private String dataToFile;

    public void getStatistic(String fromFileName, String toFileName) {
        getDataFromFile(fromFileName);
        convertInputDataInOutputData();
        setDataToFile(toFileName);
    }

    private void getDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file" + e);
        } catch (IOException e) {
            throw new RuntimeException("Error in file reading" + e);
        }
        dataFromFile = stringBuilder.toString();
    }

    private void convertInputDataInOutputData() {
        int supply = 0;
        int buy = 0;
        String[] dataFromFileInArr = dataFromFile.split("\r\n");
        String switchCase;
        for (String s : dataFromFileInArr) {
            switchCase = s.substring(0, s.indexOf(','));
            int extractedAmount = Integer.parseInt(s.replaceFirst("(\\w+),", ""));
            switch (switchCase) {
                case "supply":
                    supply += extractedAmount;
                    break;
                case "buy":
                    buy += extractedAmount;
                    break;
                default:
                    throw new RuntimeException("Invalid data in .csv file");
            }
        }
        parseOutputData(supply, buy);
    }

    private void parseOutputData(int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA_SEPARATOR);
        stringBuilder.append(supply);
        stringBuilder.append("\r\nbuy,");
        stringBuilder.append(buy);
        stringBuilder.append("\r\nresult,");
        stringBuilder.append(supply - buy);
        stringBuilder.append("\r\n");
        dataToFile = stringBuilder.toString();
    }

    private void setDataToFile(String toFileName) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(dataToFile);
        } catch (IOException e) {
            throw new RuntimeException("Exception in file writing" + e);
        }
    }
}
