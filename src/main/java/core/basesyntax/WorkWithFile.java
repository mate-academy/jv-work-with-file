package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String dataFromFile = readFromFile(fromFileName);
            String sumReport = getSumReportFromFile(dataFromFile);
            writeInFile(toFileName, sumReport);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFromFile(String fromFile) throws IOException {
        Scanner scanner = new Scanner(new File(fromFile));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine()).append(",");
        }
        scanner.close();

        return stringBuilder.toString();
    }

    private void writeInFile(String toFile, String sumReport) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));
        writer.write(sumReport);
        writer.close();
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

