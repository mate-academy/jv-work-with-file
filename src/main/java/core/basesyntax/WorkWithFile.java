package core.basesyntax;

import static java.lang.Integer.parseInt;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesArray = MyFileReader.readLines(fromFileName);
        String[][] data = MyArrayWorker.splitStringLinesInOneDimensionalOnTwoSrtings(
                linesArray, ",");
        int totalSupply = 0;
        int totalBuy = 0;
        for (String[] lineData: data) {
            switch (lineData[0]) {
                case "supply":
                    totalSupply += parseInt(lineData[1]);
                    break;
                case "buy":
                    totalBuy += parseInt(lineData[1]);
                    break;
                default:
                    System.out.println("Can't recognize operation type");
            }
        }
        int result = totalSupply - totalBuy;
        String[] report = new String[3];
        report[0] = "supply," + totalSupply;
        report[1] = "buy," + totalBuy;
        report[2] = "result," + result;
        MyFileWriter.writeDataToFile(report, toFileName);
    }
}
