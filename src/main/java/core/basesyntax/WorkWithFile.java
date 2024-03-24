package core.basesyntax;

import core.basesyntax.exceptions.FileNotCreateException;
import core.basesyntax.exceptions.FileNotExistsException;
import core.basesyntax.exceptions.FileNotOpenException;
import core.basesyntax.exceptions.FileNotWriteException;
import core.basesyntax.models.Operation;
import core.basesyntax.models.OperationDataResult;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPEN_FILE_ERROR_MESSAGE = "Can't open the file: ";
    private static final String FILE_NOT_EXISTS_ERROR_MESSAGE = "Can't open the file: ";
    private static final String FILE_NOT_CREATE_ERROR_MESSAGE = "Can't create the file: ";
    private static final String FILE_NOT_WRITE_ERROR_MESSAGE = "Can't write data to the file: ";
    private static final String DATA_FIELD_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        if (fileFrom.exists()) {
            Operation[] operationData = getOperationDataFromFile(fileFrom);
            writeOperationDataToFile(toFileName, operationData);
        } else {
            throw new FileNotExistsException(FILE_NOT_EXISTS_ERROR_MESSAGE + fromFileName);
        }
    }

    private Operation[] getOperationDataFromFile(File fileFrom) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            StringBuilder stringOperation = new StringBuilder();
            String stringValue = bufferedReader.readLine();
            while (stringValue != null) {
                stringOperation.append(stringValue).append(System.lineSeparator());
                stringValue = bufferedReader.readLine();
            }
            return getOperationArray(stringOperation.toString());
        } catch (IOException e) {
            throw new FileNotOpenException(OPEN_FILE_ERROR_MESSAGE + fileFrom.getName(), e);
        }
    }

    private Operation[] getOperationArray(String operationData) {
        String[] operationDataArray = operationData.split(System.lineSeparator());
        Operation[] operationArray = new Operation[operationDataArray.length];
        for (int i = 0; i < operationDataArray.length; i++) {
            operationArray[i] = new Operation(operationDataArray[i].split(DATA_FIELD_SEPARATOR));
        }
        return operationArray;
    }

    private void writeOperationDataToFile(String toFileName, Operation[] operationData) {
        File fileTO = new File(toFileName);
        createFile(fileTO);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTO))) {
            bufferedWriter.write(getResultData(operationData).toString());
        } catch (IOException e) {
            throw new FileNotWriteException(FILE_NOT_WRITE_ERROR_MESSAGE + toFileName, e);
        }
    }

    private void createFile(File fileName) {
        try {
            fileName.createNewFile();
        } catch (IOException e) {
            throw new FileNotCreateException(FILE_NOT_CREATE_ERROR_MESSAGE + fileName.getName(), e);
        }
    }

    private OperationDataResult getResultData(Operation[] operationData) {
        return new OperationDataResult(operationData);
    }
}
