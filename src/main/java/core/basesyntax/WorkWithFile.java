package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int LINE_NAME = 0;
    private static final int LINE_VALUE = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        List<String> linetFromFile;
        try {
            linetFromFile =  Files.readAllLines(fileFrom.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file");
        }

        int summSuplie = 0;
        int summBye = 0;
        for (String line : linetFromFile) {
            String[] lineArray = line.split(SEPARATOR);
            if (lineArray[LINE_NAME].equals("supply")) {
                summSuplie += Integer.parseInt(lineArray[LINE_VALUE]);
            }
            summBye += Integer.parseInt(lineArray[LINE_VALUE]);
        }
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(summSuplie).
                append(System.lineSeparator()).
                append(BUY).append(SEPARATOR).append(summBye).
                append(System.lineSeparator()).
                append(RESULT).append(SEPARATOR).append(summSuplie - summBye);
        report.toString();

        File fileTo = new File(toFileName);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data");
        }



    }
}