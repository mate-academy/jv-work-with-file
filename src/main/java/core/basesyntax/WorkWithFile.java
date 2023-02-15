package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
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
        File inputFile = new File(fromFileName);
        List<String> listOfInputFile;
        try {
            listOfInputFile = Files.readAllLines(inputFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return listOfInputFile;
    }

    private String calculateReport(List<String> listOfInputFile) {
        int sumSupply = 0;
        int sumBuy = 0;
        StringBuilder calculationResult = new StringBuilder();
        calculationResult.delete(0,calculationResult.length());
        for (int i = 0; i < listOfInputFile.size();i++) {
            if (listOfInputFile.get(i).contains(SUPPLY)) {
                sumSupply = sumSupply + Integer.parseInt(listOfInputFile.get(i).substring(7));
            } else {
                sumBuy = sumBuy + Integer.parseInt(listOfInputFile.get(i).substring(4));
            }
        }
        int result = sumSupply - sumBuy;
        calculationResult.append(SUPPLY).append(sumSupply).append(NEW_LINE).append(BUY)
                .append(sumBuy).append(NEW_LINE).append(RESULT).append(result);
        String calculationResultToString = calculationResult.toString();
        return calculationResultToString;
    }

    private void writeToFile(String calculationResultToString, String toFileName) {
        File outputFile = new File(toFileName);
        outputFile.delete();
        try {
            outputFile.createNewFile();
            Files.write(outputFile.toPath(),calculationResultToString.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Cant create and write file");
        }
    }
}
