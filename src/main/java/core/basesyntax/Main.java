package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("C:\\Users\\User\\IdeaProjects\\jv-work-with-file\\src"
                + "\\main\\java\\core\\basesyntax\\teststart.txt", "C:\\Users\\User\\IdeaProjects\\"
                + "jv-work-with-file\\src\\main\\java\\core\\basesyntax\\testfinish.txt");
    }
}
