package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] nums = readFile(fromFileName);
        int supplySum = nums[INDEX_ZERO];
        int buySum = nums[INDEX_ONE];
        int discount = nums[INDEX_TWO];

        String message = createMessage(supplySum,buySum,discount);
        writeFile(toFileName, message);
    }

    private int[] readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            int supplySum = 0;
            int buySum = 0;

            while (value != null) {
                String[] words = value.split(",");
                String name = words[INDEX_ZERO];
                int num = Integer.parseInt(words[INDEX_ONE]);
                if (name.equals(SUPPLY)) {
                    supplySum += num;
                } else {
                    buySum += num;
                }
                value = bufferedReader.readLine();
            }
            int discount = supplySum - buySum;

            return new int[] { supplySum, buySum, discount };
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file",e);
        }
    }

    private void writeFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            Files.write(file.toPath(), content.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found",e);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong",e);
        }
    }

    private String createMessage(int supplySum, int buySum, int discount) {
        return SUPPLY + "," + supplySum + System.lineSeparator()
                + BUY + "," + buySum + System.lineSeparator()
                + RESULT + "," + discount;
    }

}
