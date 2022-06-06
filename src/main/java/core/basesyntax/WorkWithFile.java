package core.basesyntax;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        try (CSVReader reader = new CSVReader(new FileReader(fromFileName));
                CSVWriter writer = new CSVWriter(new FileWriter(toFileName),
                        ',',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.NO_ESCAPE_CHARACTER,
                        System.lineSeparator())) {

            List<String[]> strings = reader.readAll();

            int totalBuy = 0;
            int totalSupply = 0;

            for (String[] data : strings) {
                switch (data[0]) {
                    case "buy":
                        totalBuy += Integer.parseInt(data[1]);
                        break;
                    case "supply":
                        totalSupply += Integer.parseInt(data[1]);
                        break;
                    default:
                        throw new RuntimeException("invalid data");
                }
            }
            int result = totalSupply - totalBuy;
            extractedSupply(writer, "supply", totalSupply);
            extractedBuy(writer, "buy", totalBuy);
            extractedResult(writer, "result", result);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found" + fromFileName + e);
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Something wrong while reading csv" + fromFileName + e);
        }

    }

    private void extractedResult(CSVWriter writer, String result, int result1) {
        writer.writeNext(new String[]{result, String.valueOf(result1)}, false);
    }

    private void extractedBuy(CSVWriter writer, String buy, int totalBuy) {
        writer.writeNext(new String[]{buy, String.valueOf(totalBuy)}, false);
    }

    private void extractedSupply(CSVWriter writer, String supply, int totalSupply) {
        writer.writeNext(new String[]{supply, String.valueOf(totalSupply)}, false);
    }
}
