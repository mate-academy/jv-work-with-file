package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result,";
    public static final String NEW_LINE = System.lineSeparator();
    public static final int ONE = 1;
    public static final String regex = "[\\[\\] ,]";
    private int result;
    private int sumSupply;
    private int sumBuy;
    private String[] arrayOfList;
    private StringBuilder calculationResult = new StringBuilder();

    private String [] readFile(String fromFileName) {
        File inputFile = new File(fromFileName);
        try {
            List<String> listOfInputFile = Files.readAllLines(inputFile.toPath());
            arrayOfList = listOfInputFile.toString().split(regex);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return arrayOfList;
    }

    private String calculationResult(String[] arrayOfList) {
        sumSupply = 0;
        sumBuy = 0;
        calculationResult.delete(0,calculationResult.length());
        for (int i = 0; i < arrayOfList.length;i++) {
            switch (arrayOfList[i]) {
                case SUPPLY:
                    sumSupply = sumSupply + Integer.parseInt(arrayOfList[i + 1]);
                    break;
                case BUY:
                    sumBuy = sumBuy + Integer.parseInt(arrayOfList[i + 1]);
                    break;
                default:
            }
        }
        result = sumSupply - sumBuy;
        calculationResult.append(SUPPLY).append(COMMA).append(sumSupply).append(NEW_LINE)
                        .append(BUY).append(COMMA).append(sumBuy).append(NEW_LINE)
                        .append(RESULT).append(result);
        return calculationResult.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        File outputFile = new File(toFileName);
        outputFile.delete();
        try {
            outputFile.createNewFile();
            Files.write(outputFile.toPath(),calculationResult(readFile(fromFileName)).getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Cant create and write file");
        }
    }
}
