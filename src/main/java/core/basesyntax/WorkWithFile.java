package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final String WORD_SEPARATOR = " ";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private StringBuilder uniqueOperation = new StringBuilder();
    private StringBuilder uniqueAmount = new StringBuilder();
    private StringBuilder allOperation = new StringBuilder();
    private StringBuilder allAmount = new StringBuilder();
    private StringBuilder resultReport = new StringBuilder();
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String data = bufferedReader.readLine();
            while (data != null) {
                String[] splittedData = data.split(COMMA_SEPARATOR);
                addUniqueOperation(splittedData[OPERATION_TYPE_INDEX]);
                allOperation.append(splittedData[OPERATION_TYPE_INDEX]).append(WORD_SEPARATOR);
                allAmount.append(splittedData[AMOUNT_INDEX]).append(WORD_SEPARATOR);
                data = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.", e);
        }
        System.out.println(allOperation);
        System.out.println(allAmount);
        System.out.println(uniqueOperation);
        sumAmountByOperation();
        System.out.println(uniqueAmount);
        createReport();
        System.out.println(resultReport);
    }

    private void addUniqueOperation(String splittedDatum) {
        String[] splitUnique = uniqueOperation.toString().split(WORD_SEPARATOR);
        if (!uniqueOperation.toString().isEmpty()) {
            int i = 0;
            for (String value : splitUnique) {
                if (value.equals(splittedDatum)) {
                    break;
                }
                i++;
            }
            if (i == splitUnique.length) uniqueOperation.append(splittedDatum).append(WORD_SEPARATOR);
        } else {
            uniqueOperation.append(splittedDatum).append(WORD_SEPARATOR);
        }
    }

    private void sumAmountByOperation() {
        String[] uniqueOp = uniqueOperation.toString().split(WORD_SEPARATOR);
        String[] allOp = allOperation.toString().split(WORD_SEPARATOR);
        String[] allAm = allAmount.toString().split(WORD_SEPARATOR);

        for (String value : uniqueOp) {
            int i = 0;
            int result = 0;
            for (String value2 : allOp) {
                if (value.equals(value2)) {
                    result = result + Integer.parseInt(allAm[i]);
                }
                i++;
            }
            uniqueAmount.append(result).append(WORD_SEPARATOR);
        }
    }

    private void createReport() {
        String[] splitOp = uniqueOperation.toString().split(WORD_SEPARATOR);
        String[] splitAm = uniqueAmount.toString().split(WORD_SEPARATOR);
        int result = Integer.parseInt(splitAm[0]) - Integer.parseInt(splitAm[1]);
        resultReport.append(splitOp[0]).append(COMMA_SEPARATOR)
                .append(splitAm[0]).append(System.lineSeparator())
                .append(splitOp[1]).append(COMMA_SEPARATOR)
                .append(splitAm[1]).append(System.lineSeparator())
                .append("result").append(COMMA_SEPARATOR)
                .append(result);
    }
}
