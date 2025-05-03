package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        int supply = 0;
        int buy = 0;

        try (BufferedReader readBuffer = new BufferedReader(new FileReader(fromFileName))) {
            String fruitString = readBuffer.readLine();
            while (fruitString != null) {
                String fruitDo = fruitString.substring(0, fruitString.indexOf(","));
                int fruitQuantity = Integer.parseInt(
                        fruitString.substring(fruitString.indexOf(",") + 1));
                if (fruitDo.equals("supply")) {
                    supply += fruitQuantity;
                }
                if (fruitDo.equals("buy")) {
                    buy += fruitQuantity;
                }
                fruitString = readBuffer.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Fruit quantity is not an integer ", e);
        }
        String report = createReport(supply, buy);
        writeReportToFile(toFileName, report);
    }

    private String createReport(int supplyCount, int buyCount) {
        return "supply," + supplyCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator()
                + "result," + (supplyCount - buyCount) + System.lineSeparator();
    }

    private void writeReportToFile(String fileName, String report) {
        try (BufferedWriter result = new BufferedWriter(new FileWriter(fileName))) {
            result.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
