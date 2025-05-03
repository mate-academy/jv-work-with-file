package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final int INDEX_OF_OPERATION = 0;
    public static final int INDEX_OF_SUM_OPERATION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeDate(report(readFile(fromFileName).replaceAll("[\\[|\\]]","")), toFileName);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> stringList;
        try {
            stringList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("You got IOExeption", e);
        }
        return stringList.toString();
    }

    private String report(String date) {
        int buy = 0;
        int suplly = 0;
        
        String[] splittedLinesBySpace = date.split(" ");

        for (String line : splittedLinesBySpace) {
            String[] splittedLinesByComma = line.split(",");

            if (splittedLinesByComma[INDEX_OF_OPERATION].equals("buy")) {
                buy += Integer.parseInt(splittedLinesByComma[INDEX_OF_SUM_OPERATION]);
            } else {
                suplly += Integer.parseInt(splittedLinesByComma[INDEX_OF_SUM_OPERATION]);
            }
        }
        return "supply," + suplly + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (suplly - buy);
    }

    private void writeDate(String report, String toFileName) {
        File file = new File(toFileName);
        try {
            Files.writeString(file.toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("You got IOExeption", e);
        }
    }

}
