import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String trainFile = "data/iris_train.csv";
        String testFile = "data/iris_test.csv";
        double alpha = 0.01;
        Perceptron perceptron = new Perceptron(alpha, trainFile, testFile);
        perceptron.classify();

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nEnter the attributes and alpha :");
            String str = scanner.nextLine();
            String[] attributes = str.split(",");
            perceptron.newTest(attributes);
        }
    }
}
