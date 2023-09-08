import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Perceptron {
    private double alpha, theta;
    private double[] weights;
    private ArrayList<Iris> trainSet, testSet;

    // Constructor to initialize Perceptron with learning rate and data file paths
    public Perceptron(double alpha, String trainFile, String testFile) {
        this.alpha = alpha;
        this.trainSet = loadSet(trainFile);
        this.testSet = loadSet(testFile);
        setThetaAndWeights();
    }

    // Initialize theta and weights with random values
    public void setThetaAndWeights () {
        theta = (Math.random()*2) - 1;
        weights = new double[trainSet.get(0).getIrisAttributes().length];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random()*2) - 1;
        }
    }

    // Load data from a CSV file and create an ArrayList of Iris objects
    private ArrayList<Iris> loadSet(String fileName) {
        ArrayList<Iris> dataSet = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] vector = new double[values.length - 1];

                for (int i = 0; i < values.length - 1; i++) {
                    vector[i] = Double.parseDouble(values[i]);
                }
                Iris iris = new Iris(vector, values[values.length - 1]);
                dataSet.add(iris);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    // Perform training and testing
    public void classify() {
        train();
        test();
    }

    // Train the perceptron weights and theta
    public void train() {
        for (int i = 0; i < trainSet.size(); i++) {
            int summaryError = 0;
            for (Iris iris : trainSet) {
                int d = getDBySpecies(iris.getName());
                int y = net(iris);
                processInputs(weights, iris.getIrisAttributes(), d, y, alpha);
                theta -= (d - y) * alpha;
                summaryError += (d - y);
            }
            if (summaryError == 0) break;
        }

    }

    // Test the perceptron's accuracy
    public void test() {
        int correct = 0, correctSetosa = 0, correctVirginica = 0;
        int all = testSet.size();

        for (Iris iris : testSet) {
            int prediction = net(iris);

            String species = iris.getName();

            if (getSpeciesByD(prediction).equals(species)){
                correct ++;
                if (prediction == 1){
                    correctSetosa++;
                } else {
                    correctVirginica++;
                }
            } else {
                System.out.println("Incorrect! " + iris + " expected " + iris.getName() + " but " + getSpeciesByD(prediction) + " prediction given");
            }
        }

        // Display accuracy results
        System.out.println("Accuracy: " + correct + " of " + all + " were predicted correctly");
        System.out.println(correct * 100 / all  + " %");
        System.out.println("Accuracy for Iris-setosa: " + correctSetosa * 100 / 15 + " %");
        System.out.println("Accuracy for Iris-virginica: " + correctVirginica * 100 / 15  + " %");
    }

    // Update weights based on inputs, desired output (d), and predicted output (y)
    public void processInputs(double[] weights, double[] inputs, int d, int y, double alpha) {
        for ( int i = 0; i < weights.length; i++) {
            weights[i] += (d - y) * alpha * inputs[i];
        }
    }

    // Calculate the weighted sum and return the predicted output (0 or 1)
    public int net(Iris iris) {
        double net = 0;
        for (int i = 0; i < iris.getIrisAttributes().length; i++){
            net += iris.getIrisAttributes()[i]*weights[i];
        }

        return (net >= theta ? 1 : 0);
    }

    public int getDBySpecies(String species) {
        return species.equals("Iris-setosa") ? 1 : 0;
    }

    public String getSpeciesByD(int d) {
        return d == 1 ? "Iris-setosa" : "Iris-virginica";
    }

    // Perform classification with user-defined attributes
    public void newTest(String[] attributes) {
        double[] vector = new double[attributes.length - 1];
        for (int i = 0; i < vector.length; i++){
            vector[i] = Double.parseDouble(attributes[i]);
        }
        alpha = Double.parseDouble(attributes[attributes.length - 1]);

        Iris tmp = new Iris(vector, null);
        int prediction = net(tmp);
        String species = getSpeciesByD(prediction);
        System.out.println("Prediction for " + tmp + " => " + species);
    }
}