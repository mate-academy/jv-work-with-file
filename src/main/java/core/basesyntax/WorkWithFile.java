package core.basesyntax;

import core.basesyntax.exceptions.FileNotExistsException;
import core.basesyntax.exceptions.FileNotOpenedException;
import core.basesyntax.exceptions.FileNotWrittenException;
import core.basesyntax.models.Operation;
import core.basesyntax.models.OperationDataResult;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String FILE_NOT_OPENED_ERROR_MESSAGE = "Can't open the file: ";
    private static final String FILE_DOES_NOT_EXIST_ERROR_MESSAGE = "The file does not exist: ";
    private static final String FILE_NOT_WRITTEN_ERROR_MESSAGE = "Can't write data to the file: ";
    private static final String DATA_FIELD_SEPARATOR_IN_THE_FILE = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        Operation[] operationData = getOperationData(dataFromFile);
        String reportData = getResultData(operationData);
        writeFile(toFileName, reportData);
    }

    private String readFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        checkExistsFile(fileFrom);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            StringBuilder stringOperation = new StringBuilder();
            String stringValue = bufferedReader.readLine();
            while (stringValue != null) {
                stringOperation.append(stringValue).append(System.lineSeparator());
                stringValue = bufferedReader.readLine();
            }
            return stringOperation.toString();
        } catch (IOException e) {
            throw new FileNotOpenedException(FILE_NOT_OPENED_ERROR_MESSAGE + fileFrom.getName(), e);
        }
    }

    private void checkExistsFile(File fileFrom) {
        if (!fileFrom.exists()) {
            throw new FileNotExistsException(FILE_DOES_NOT_EXIST_ERROR_MESSAGE
                    + fileFrom.getName());
        }
    }

    private Operation[] getOperationData(String operationData) {
        String[] operationDataArray = operationData.split(System.lineSeparator());
        Operation[] operationArray = new Operation[operationDataArray.length];
        for (int i = 0; i < operationDataArray.length; i++) {
            operationArray[i] = new Operation(operationDataArray[i]
                    .split(DATA_FIELD_SEPARATOR_IN_THE_FILE));
        }
        return operationArray;
    }

    private String getResultData(Operation[] operationData) {
        return new OperationDataResult(operationData, DATA_FIELD_SEPARATOR_IN_THE_FILE).toString();
    }

    private void writeFile(String toFileName, String reportData) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportData);
        } catch (IOException e) {
            throw new FileNotWrittenException(FILE_NOT_WRITTEN_ERROR_MESSAGE + toFileName, e);
        }
    }
}
