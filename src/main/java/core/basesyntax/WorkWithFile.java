package core.basesyntax;

import java.io.File;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String SEPARATOR = System.lineSeparator();
    private static final int WORD_INDEX = 0;
    private static final int SUPPLY_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final int ARRAY_SIZE = 3;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        ReadFile readFile = new ReadFile();
        File file = new File(fromFileName);
        String string = readFile.readFileContent(file);
        int[] arr = calculateSum(string);
        String result = getResultString(arr);
        WriteFile writeFile = new WriteFile();
        writeFile.writeToFile(result,toFileName);
    }

    public int[] calculateSum(String content) {
        int supplySum = 0;
        int buySum = 0;
        int[] countedSumArray = new int[ARRAY_SIZE];
        String[] arrayOfContent = content.split(SEPARATOR);
        for (int i = 0; i < arrayOfContent.length; i++) {
            String[] elements = arrayOfContent[i].split(COMMA);
            if (SUPPLY.equals(elements[WORD_INDEX])) {
                supplySum += Integer.parseInt(elements[NUMBER_INDEX]);
            } else if (BUY.equals(elements[WORD_INDEX])) {
                buySum += Integer.parseInt((elements[NUMBER_INDEX]));
            }
        }
        countedSumArray[SUPPLY_INDEX] = supplySum;
        countedSumArray[BUY_INDEX] = buySum;
        countedSumArray[RESULT_INDEX] = supplySum - buySum;
        return countedSumArray;
    }

    public String getResultString(int[] countedSumArray) {
        String[] words = new String[countedSumArray.length];
        words[SUPPLY_INDEX] = SUPPLY;
        words[BUY_INDEX] = BUY;
        words[RESULT_INDEX] = RESULT;
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < countedSumArray.length; i++) {
            stringBuilder.append(words[index]).append(COMMA)
                    .append(countedSumArray[i]).append(SEPARATOR);
            index++;
        }
        return stringBuilder.toString();
    }
}
