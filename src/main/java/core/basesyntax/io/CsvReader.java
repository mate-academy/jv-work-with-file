package core.basesyntax.io;

import core.basesyntax.io.exception.CsvException;
import core.basesyntax.model.Operation;
import core.basesyntax.model.OperationType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReader {
    private static final String DELIMITER = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public List<Operation> readOperations(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(this::csvStringToOperation)
                    .toList();
        } catch (IOException e) {
            throw new CsvException("Cannot read file = [" + fileName + "]", e);
        }
    }

    private Operation csvStringToOperation(String string) {
        String[] split = string.split(DELIMITER);
        String operationTypeString = split[OPERATION_TYPE_INDEX].toUpperCase();
        OperationType operationType = OperationType.valueOf(operationTypeString);
        int amount = Integer.parseInt(split[AMOUNT_INDEX]);
        return new Operation(operationType, amount);
    }
}
