package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final char COMMA = ',';
    private int countSupply = 0;
    private int countBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            List<String> content = Files.readAllLines(file.toPath());
            for (String a : content) {
                String[] temporaryArray = a.split(",");
                if (temporaryArray[0].equals(SUPPLY)) {
                    String number = temporaryArray[1].toString();
                    setCountSupply(getCountSupply() + Integer.parseInt(number));
                } else if (temporaryArray[0].equals(BUY)) {
                    String number = temporaryArray[1].toString();
                    setCountBuy(getCountBuy() + Integer.parseInt(number));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t exist file", e);
        }

        String supplyResult = SUPPLY + COMMA + getCountSupply();
        String buyResult = BUY + COMMA + getCountBuy();
        String finalResult = RESULT + COMMA + (getCountSupply() - getCountBuy());
        String[] results = {supplyResult, buyResult, finalResult};
        File fileTwo = new File(toFileName);

        try {
            fileTwo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileTwo, true));
            for (String result : results) {
                bufferedWriter.write(result + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Don`t write data to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can`t close BufferedWriter", e);
                }
            }
        }

        setCountSupply(0);
        setCountBuy(0);
    }

    public int getCountBuy() {
        return countBuy;
    }

    public void setCountBuy(int countBuy) {
        this.countBuy = countBuy;
    }

    public int getCountSupply() {
        return countSupply;
    }

    public void setCountSupply(int countSupply) {
        this.countSupply = countSupply;
    }
}
