package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        readStatistic(fromFile);
        writeResultToFile(toFile);
        resetFields();
    }

    private void readStatistic(File fromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                calcSupplyAndBuy(value);
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no such file: " + fromFile.getName(), e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFile.getName(), e);
        }
    }

    private void calcSupplyAndBuy(String value) {
        String[] values = value.split(",");
        switch (values[OPERATION_TYPE_INDEX]) {
            case "supply":
                supply += Integer.parseInt(values[AMMOUNT_INDEX]);
                break;
            case "buy":
                buy += Integer.parseInt(values[AMMOUNT_INDEX]);
                break;
            default:
        }
    }

    private void writeResultToFile(File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + toFile.getName(), e);
        }
    }

    private void resetFields() {
        supply = 0;
        buy = 0;
    }
}
