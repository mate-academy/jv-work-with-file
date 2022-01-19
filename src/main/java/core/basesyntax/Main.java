package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String filenameInput = "C:\\Users\\LinkClink\\Documents\\GitHub\\jv-work-with-file\\banana.csv";
        String filenameOut = "C:\\Users\\LinkClink\\Documents\\GitHub\\jv-work-with-file\\out.csv";
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(filenameInput, filenameOut);
    }
}
