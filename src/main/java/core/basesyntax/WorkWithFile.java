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
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        int supplyCount = 0;
        int buyCount = 0;
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String fileLine = bufferedReader.readLine();
            
            while (fileLine != null) {
                int operationCount = getOperationCount(fileLine);
                
                if (getOperation(fileLine).equals(OPERATION_BUY)) {
                    buyCount += operationCount;
                } else {
                    supplyCount += operationCount;
                }
                fileLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File wasn't found or couldn't be opened", e);
        }
        
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));) {
            bufferedWriter.write(OPERATION_SUPPLY + CSV_SEPARATOR + supplyCount
                    + System.lineSeparator()
                    + OPERATION_BUY + CSV_SEPARATOR + buyCount + System.lineSeparator()
                    + RESULT + CSV_SEPARATOR + (supplyCount - buyCount)
            );
        } catch (IOException e) {
            throw new RuntimeException("File wasn't found or couldn't be opened", e);
        }
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
