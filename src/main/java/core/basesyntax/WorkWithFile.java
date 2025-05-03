package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY = "supply,";
    public static final String BUY = "buy,";
    public static final String RESULT = "result,";
    public static final String NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> listFromFile = readFile(fromFileName);
        String report = calculateReport(listFromFile);
        writeToFile(report,toFileName);
    }

    private List<String> readFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private String calculateReport(List<String> listOfInputFile) {
        int sumSupply = 0;
        int sumBuy = 0;
        StringBuilder calculationResult = new StringBuilder();
        for (String element :listOfInputFile) {
            if (element.contains(SUPPLY)) {
                sumSupply = sumSupply + Integer.parseInt(element.substring(SUPPLY.length()));
            } else {
                sumBuy = sumBuy + Integer.parseInt(element.substring(BUY.length()));
            }
        }
        int result = sumSupply - sumBuy;
        calculationResult.append(SUPPLY).append(sumSupply).append(NEW_LINE).append(BUY)
                .append(sumBuy).append(NEW_LINE).append(RESULT).append(result);
        return calculationResult.toString();
    }

    private void writeToFile(String text, String toFileName) {
        try {
            Files.write(new File(toFileName).toPath(),text.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cant write file" + toFileName,e);
        }
    }
}
