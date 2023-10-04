package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String strSupply = "supply,";
    private static final String strBuy = "buy,";
    private static final String strResult = "result,";
    private static final String COMMA_SYMBOL = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        readCalculateFile(fromFileName, stringBuilder);
        writeFile(createFile(toFileName),stringBuilder);
    }

    private void createReport(int supply,int buy, StringBuilder stringBuilder) {
        int result = supply - buy;
        stringBuilder.append(strSupply).append(supply)
                .append(System.lineSeparator())
                .append(strBuy).append(buy).append(System.lineSeparator())
                .append(strResult).append(result);
    }

    private File createFile(String toFileName) {
        try {
            File createFile = new File(toFileName);
            createFile.createNewFile();
            return createFile;
        } catch (IOException e) {
            throw new RuntimeException("can't create file " + toFileName, e);
        }
    }

    private void writeFile(File file, StringBuilder stringBuilder) {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + file.getName(), e);
        }
    }

    private void readCalculateFile(String fromFileName, StringBuilder stringBuilder) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] splitFileLine = currentLine.split(COMMA_SYMBOL);
                String operation = splitFileLine[0];
                int operationNumber = Integer.parseInt(splitFileLine[1]);
                if (operation.equals("buy")) {
                    buy += operationNumber;
                } else {
                    supply += operationNumber;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("can't read file " + fromFileName, e);
        }
        createReport(supply, buy, stringBuilder);
    }
}
