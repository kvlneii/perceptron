# Perceptron Iris Classification
This project implements a perceptron for classifying iris flowers into two categories: "Iris-setosa" and "Iris-virginica" based on their attributes.
The perceptron learns from a given training dataset using a specified learning rate and then evaluates its accuracy on a test dataset. 
Additionally, it provides a user interface for interactive classification of individual flower vectors.

## Usage
To use this program, follow these steps:
1. Clone this repository or download the source code files.
2. Compile the Java code. You can use the following command in your terminal or command prompt:

   ```bash
   javac Main.java
3. Run the compiled program:

   ```bash
   java Main

4. The program will train the perceptron on the provided training dataset and evaluate its accuracy on the test dataset. It will display the accuracy percentage and percentages for individual iris species.
5. After the initial classification, you can use the program's interactive interface to classify individual iris vectors. Enter attributes and learning rate as prompted, and the program will provide the predicted species.

## Example Input Format
When prompted for input in the terminal, you should enter a comma-separated list of attributes followed by the learning rate alpha. 
For example:
  ```plaintext
     5.1,3.5,1.4,0.2,0.01
  ```

Here, 5.1, 3.5, 1.4, 0.2 represents the attributes of the Iris flower, and 0.01 is the learning rate (alpha).

## Dataset
This project relies on two CSV datasets for training and testing. The datasets should adhere to the following format:
* Each row represents an iris flower.
* The last column contains the species label ("Iris-setosa" or "Iris-virginica").
* The preceding columns contain numerical attributes (features) of the iris flower.
