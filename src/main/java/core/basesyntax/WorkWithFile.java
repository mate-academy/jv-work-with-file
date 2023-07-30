package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT_OPERATION = "result";
    private static final String IO_OPERATION_ERROR = "I/O operation interrupted";
    private static final String VALUES_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readFromFileResult = readFromFile(fromFileName);
        String fileContentProcessed = processFileReadResults(readFromFileResult);
        writeToFile(fileContentProcessed, toFileName);
    }

    private static String readFromFile(String fromFileName) {
        StringBuilder fileRead = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String fileReadLine = bufferedReader.readLine();
            while (fileReadLine != null) {
                fileRead.append(fileReadLine).append(System.lineSeparator());
                fileReadLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found.", e);
        } catch (IOException e) {
            throw new RuntimeException(IO_OPERATION_ERROR, e);
        }
        return String.valueOf(fileRead);
    }

    private static String processFileReadResults(String fileContent) {
        int supplyOperationSum = 0;
        int buyOperationSum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader(fileContent))) {
            final int operationAmountIndex = 1;
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(VALUES_SEPARATOR);
                String operationType = lineSplit[0];
                String operationAmountString = lineSplit[operationAmountIndex];
                int operationAmount = 0;
                operationAmount = Integer.parseInt(operationAmountString);
                switch (operationType) {
                    case BUY_OPERATION: {
                        buyOperationSum += operationAmount;
                        break;
                    }
                    case SUPPLY_OPERATION: {
                        supplyOperationSum += operationAmount;
                        break;
                    }
                    default: {
                        throw new RuntimeException("Wrong operation type.");
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Wrong operation amount format.", e);
        } catch (IOException e) {
            throw new RuntimeException(IO_OPERATION_ERROR, e);
        }
        return SUPPLY_OPERATION + VALUES_SEPARATOR + supplyOperationSum + System.lineSeparator()
                + BUY_OPERATION + VALUES_SEPARATOR + buyOperationSum + System.lineSeparator()
                + RESULT_OPERATION + VALUES_SEPARATOR + (supplyOperationSum - buyOperationSum);
    }

    private static void writeToFile(String fileContentProcessed, String toFile) {
        File resultFile = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
             BufferedReader bufferedReader = new BufferedReader(
                     new StringReader(fileContentProcessed))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                bufferedWriter.write(line);
                bufferedWriter.write(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("I/O operation interrupted", e);
        }
    }
}
