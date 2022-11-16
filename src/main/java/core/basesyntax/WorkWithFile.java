package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    /* Is it this class have a next logic? One of the class fields must be File entity. Because in work situation
    in eighteen's line of code we need to pass File entity in constructor. After that we are going to call writeToFile()
    method. WorkWithFile class is kind of utility class.
    */
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] splitInformationFromFile = readFromFile(fromFileName);
        String report = createReport(splitInformationFromFile);
        WorkWithFile file = new WorkWithFile();
        file.writeToFile(toFileName, report);
    }

    private String[] readFromFile(String input) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return builder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + input, e);
        }
    }

    private String createReport(String[] array) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String arrayInfo : array) {
            String[] arraySplit = arrayInfo.split(",");
            if (arraySplit[OPERATION_TYPE_INDEX].equals("supply")) {
                supply += Integer.parseInt(arraySplit[AMOUNT_INDEX]);
            } else if (arraySplit[OPERATION_TYPE_INDEX].equals("buy")) {
                buy += Integer.parseInt(arraySplit[AMOUNT_INDEX]);
            }
        }

        return stringBuilder
                .append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy).toString();
    }

    private void writeToFile(String fileName, String information) {
        File file = new File(fileName);
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            bf.write(information);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + file, e);
        }
    }
}
