import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        System.out.println("Lab1: " + lab1("abccabcdefa") + "\n");

        double[] arr21 = {2.1, 1, 7, 4, 9.75};
        double[] arr22 = {5, 2.05, 2, 8.5, 3.9};
        System.out.println("Lab2: " + Arrays.toString(lab2(arr21, arr22)) + "\n");

        int[] arr31 = {-1, 2, 5, 3, -5, -3, 12, -1};
        System.out.println("Lab3: " + lab3(arr31) + "\n");

        double[][] arr41 = {{1, 2}, {3, 4}, {5, 6}};
        double[][] outputArr41 = lab4(arr41);
        System.out.println("Lab4:");
        for (double[] rows: arr41) {
            System.out.println(Arrays.toString(rows));
        }
        System.out.println(" ");
        for (double[] rows: outputArr41) {
            System.out.println(Arrays.toString(rows));
        }
        System.out.println(" ");

        int[] arr51 = {-1, 12, 4, 5, -6, -3, 2};
        System.out.println("Lab5: " + Arrays.toString(lab5(arr51, 1)) + "\n");

        int[][] arr61 = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println("Lab6: " + lab6(arr61) + "\n");

        int[][] arr71 = {{11, 2, 19}, {45, 67, 22}, {32, 6, 85}};
        System.out.println("Lab7: " + Arrays.toString(lab7(arr71)) + "\n");

        double[][] arr81 = {{1, 2, 3}, {4, 5, 6}};

        double[][] outputArr81 = lab8(arr81);
        System.out.println("Lab8:");
        for (double[] rows: arr81) {
            System.out.println(Arrays.toString(rows));
        }
        System.out.println(" ");
        for (double[] rows: outputArr81) {
            System.out.println(Arrays.toString(rows));
        }
    }
    public static String lab1(String str) {
        String maxLine = ""; //маскимальная строка
        String currentLine = ""; //текущая строка
        char[] chars = new char[str.length()]; //массив символов
        boolean flag = false; //флаг для указания, что встретился повторный символ
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == str.charAt(i)) { //проверка, что встретился символ из массива символов
                    flag = true;
                }
            }
            if (flag) {
                chars = new char[str.length()];
                currentLine = "";
                flag = false;
            }
            chars[i] = str.charAt(i);
            currentLine += str.charAt(i);
            if (maxLine.length() < currentLine.length()) {
                maxLine = currentLine;
            }
        }
        return maxLine;
    }

    public static double[] lab2(double[] arr1, double[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        double[] outputArr = new double[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            outputArr[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            outputArr[arr1.length + i] = arr2[i];
        }
        Arrays.sort(outputArr);
        return outputArr;
    }

    public static int lab3(int[] arr) {
        int maxSum = 0;
        int currentSum = 0;
        int[] arrTmp = new int[arr.length + 1]; //костыльный массив, чтобы выходить за пределы arr путем добавления лишнего элемента
        for (int i = 0; i < arr.length; i++) {
            arrTmp[i] = arr[i];
        }
        arrTmp[arrTmp.length - 1] = 0; //лишний элемент
        for (int i = 0; i < arr.length; i++) {
            if (arrTmp[i] + arrTmp[i+1] > currentSum) { //это условие нужно, чтобы текущая сумма не принимала значение максимального элемента в массиве
                currentSum = arrTmp[i] + arrTmp[i+1];
            }

            for (int j = i; j < arr.length; j++) {
                if (arrTmp[j] + arrTmp[j+1] > currentSum) {
                    currentSum += arrTmp[j+1];
                } else {
                    break;
                }
                System.out.println("currentSumAfter " + currentSum + "\n");

            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            currentSum = 0;
        }
        return maxSum;
    }

    public static double[][] lab4(double[][] arr) {
        double[][] arrTransform = new double[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arrTransform[j][arr.length - 1 - i] = arr[i][j];
            }
        }
        return arrTransform;
    }

    public static int[] lab5(int[] arr, int target) {
        int[] outputArr = new int[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    outputArr[0] = arr[i];
                    outputArr[1] = arr[j];
                    return outputArr;
                }
            }
        }
        return null;
    }

    public static int lab6(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }

    public static int[] lab7(int[][] arr) {
        int[] arrOfMaxElementOfEachRow = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            IntSummaryStatistics stats = Arrays.stream(arr[i]).summaryStatistics(); //получение статистики по массиву
            arrOfMaxElementOfEachRow[i] = stats.getMax();
        }
        return arrOfMaxElementOfEachRow;
    }

    public static double[][] lab8(double[][] arr) {
        double[][] arrTransform = new double[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arrTransform[arr[0].length - 1 - j][i] = arr[i][j];
            }
        }
        return arrTransform;
    }
}