package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        int[] values = getValuesAndResult(readFile(fromFileName));
        writeToFile(toFileName, values);

    }

    private String readFile(String nameOfTheFile) {

        try {
            String text = Files.readString(Path.of(nameOfTheFile));
            return text;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + nameOfTheFile, e);
        }
    }

    private int[] getValuesAndResult(String text) {

        String[] lines = text.split("\n");

        int buyValues = 0;
        int supplyValues = 0;
        int result;

        for (String string : lines) {

            String[] typeAndValues = string.split(",");

            typeAndValues[0].trim();

            if (typeAndValues[0].equals("buy")) {

                buyValues += Integer.parseInt(typeAndValues[1].trim());

            }

            if (typeAndValues[0].equals("supply")) {

                supplyValues += Integer.parseInt(typeAndValues[1].trim());

            }

        }
        result = supplyValues - buyValues;
        return new int[] { supplyValues, buyValues, result };

    }

    private void writeToFile(String fileName, int[] values) {

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("supply," + values[0] + System.lineSeparator());
            writer.write("buy," + values[1] + System.lineSeparator());
            writer.write("result," + values[2] + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create the file.", e);
        }
    }

}
