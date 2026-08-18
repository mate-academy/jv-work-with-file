package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int OPERATION_POSITION = 0;
    private static final int COUNT_POSITION = 1;
    
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        String [] fileContent = readFromFile(fromFileName).split(System.lineSeparator());
        
        for (String row : fileContent) {
            int operationCount = getOperationCount(row);
            
            if (getOperation(row).equals(OPERATION_BUY)) {
                buyCount += operationCount;
            } else {
                supplyCount += operationCount;
            }
        }
        
        writeIntoFile(toFileName, supplyCount, buyCount);
    }
    
    private static String readFromFile(String inputFileName) {
        File inputFile = new File(inputFileName);
        StringBuilder stringBuilder = new StringBuilder();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String fileLine = bufferedReader.readLine();
            
            while (fileLine != null) {
                stringBuilder.append(fileLine).append(System.lineSeparator());
                fileLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File wasn't found or couldn't be opened", e);
        }
        
        return stringBuilder.toString();
    }
    
    private void writeIntoFile(String outputFileName, int supplyCount, int buyCount) {
        File outputFile = new File(outputFileName);
        
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));) {
            String report = createReport(supplyCount, buyCount);
            
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File wasn't found or couldn't be opened", e);
        }
    }
    
    private String createReport(int supplyCount, int buyCount) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OPERATION_SUPPLY)
                .append(CSV_SEPARATOR)
                .append(supplyCount)
                .append(System.lineSeparator())
                .append(OPERATION_BUY)
                .append(CSV_SEPARATOR)
                .append(buyCount)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(CSV_SEPARATOR)
                .append(supplyCount - buyCount);
        
        return stringBuilder.toString();
    }
    
    private String getOperation(String line) {
        String[] operationAndCount = line.split(CSV_SEPARATOR);
        return operationAndCount[OPERATION_POSITION].equals(OPERATION_BUY)
                ? OPERATION_BUY
                : OPERATION_SUPPLY;
    }
    
    private int getOperationCount(String line) {
        String[] operationAndCount = line.split(CSV_SEPARATOR);
        
        return Integer.parseInt(operationAndCount[COUNT_POSITION]);
    }
}
