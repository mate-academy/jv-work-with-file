package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String sumReport = getSumReportFromFile(dataFromFile);
        writeInFile(toFileName, sumReport);
    }

    private String readFromFile(String fromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fromFile))) {
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine()).append(",");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }

    private void writeInFile(String toFile, String sumReport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(sumReport);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getSumReportFromFile(String dataFromFile) {
        int supplySum = 0;
        int buySum = 0;
        String[] strings = dataFromFile.split(",");
        for (int index = 0; index < strings.length; index += 2) {
            String info = strings[index];
            int number = Integer.parseInt(strings[index + 1]);
            if (info.equals("supply")) {
                supplySum += number;
            } else {
                buySum += number;
            }
        }

        return "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum);
    }
}

