package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName,fromFileName);
    }

    private String[] readFromFile(String fromFileName) {
        final String coma = ",";
        final String space = " ";
        File fileFrom = new File(fromFileName);
        WorkWithFile workWithFile = new WorkWithFile();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            StringBuilder sbRead = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                sbRead.append(value).append(space);
                value = reader.readLine();
            }
            String[] lines = sbRead.toString().split(space);
            int quantity = 0;

            for (String line : lines) {
                int signPosition = line.indexOf(coma);
                quantity = Integer.parseInt(line.substring(signPosition + 1));
                Field field = WorkWithFile.class.getDeclaredField(line.substring(0,signPosition));
                int currentQuantity = (int) field.get(workWithFile);
                currentQuantity += quantity;
                field.set(workWithFile, currentQuantity);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        workWithFile.result = workWithFile.supply - workWithFile.buy;
        String[] report = new String[3];
        int counter = 0;
        for (Field f : workWithFile.getClass().getDeclaredFields()) {
            try {
                StringBuilder sbWrite = new StringBuilder();
                report[counter] = sbWrite
                        .append(f.getName())
                        .append(coma)
                        .append(f.get(workWithFile)).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        return report;
    }

    private void writeToFile(String toFileName, String fromFileName) {
        File fileTo = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo))) {
            for (String reportUnit : readFromFile(fromFileName)) {
                writer.write(reportUnit);
                writer.write(System.lineSeparator());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
