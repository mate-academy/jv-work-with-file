package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_NAME = 0;
    private static final int OPERATION_AMOUNT = 1;
    private static final String REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String infoAboutBuyOperation = getSumOfOperation(readTheFile(fromFileName), Operation.BUY);
        String infoAboutSupplyOperation = getSumOfOperation(readTheFile(fromFileName)
                , Operation.SUPPLY);
        String infoAboutResult = getResult(infoAboutBuyOperation, infoAboutSupplyOperation);
        writeStatistic(infoAboutBuyOperation, infoAboutSupplyOperation
                , infoAboutResult, toFileName);
    }

    private String [] readTheFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] readingData = new String[strings.size()];
        for (int i = 0; i < readingData.length; i++) {
            readingData[i] = strings.get(i);
        }
        return readingData;
    }

    private String getSumOfOperation(String [] readingFiles, Enum operationName) {
        int operationSum = 0;
        for (String files : readingFiles) {
            String[] split = files.split(REGEX);
            if (split[OPERATION_NAME].equals(String.valueOf(operationName).toLowerCase())) {
                operationSum += Integer.parseInt(split[OPERATION_AMOUNT]);
            }
        }
        return String.valueOf(operationName).toLowerCase() + REGEX + operationSum;
    }

    private String getResult(String infoAboutBuyOperation, String infoAboutSupplyOperation) {
        String[] splittedBuyOperation = infoAboutBuyOperation.split(REGEX);
        String[] splittedSupplyOperation = infoAboutSupplyOperation.split(REGEX);
        int resultAmount = Integer.parseInt(splittedSupplyOperation[OPERATION_AMOUNT])
                - Integer.parseInt(splittedBuyOperation[OPERATION_AMOUNT]);
        return String.valueOf(Operation.RESULT).toLowerCase() + REGEX + resultAmount;
    }

    private void writeStatistic(String infoAboutBuyOperation, String infoAboutSupplyOperation
            , String infoAboutResult, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,true))) {
            bufferedWriter.write(infoAboutSupplyOperation);
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(infoAboutBuyOperation);
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(infoAboutResult);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
