package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        LineRecord[] summaryReport = new LineRecord[2];
        adaptCsvData(fromFileName, summaryReport);
        updateReportLine(toFileName, summaryReport);
    }

    private void adaptCsvData(String fromFileName, LineRecord[] reportList) {
        int countOfLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    String name = values[0].trim();
                    int amount = Integer.parseInt(values[1].trim());
                    int index = -1;

                    for (int i = 0; i < countOfLines; i++) {
                        if (reportList[i].getLineOperatorTypeName().equals(name)) {
                            index = i;
                            break;
                        }
                    }

                    if (index != -1) {
                        reportList[index].addAmount(amount);
                    } else {
                        reportList[countOfLines] = new LineRecord(name, amount);
                        countOfLines += 1;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateReportLine(String outputFileName, LineRecord[] summaryReport) {
        try (BufferedWriter summary = new BufferedWriter(new FileWriter(outputFileName))) {
            LineRecord supply = summaryReport[0].getLineOperatorTypeName().equals("supply")
                    ? summaryReport[0] : summaryReport[1];
            LineRecord buy = summaryReport[0].getLineOperatorTypeName().equals("buy")
                    ? summaryReport[0] : summaryReport[1];
            int result = supply.getLineAmount() - buy.getLineAmount();

            summary.write(supply.getConcatenatedLine());
            summary.newLine();
            summary.write(buy.getConcatenatedLine());
            summary.newLine();
            summary.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
