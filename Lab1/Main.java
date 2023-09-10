import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("Lab1: " + problem1(10));
        System.out.println("Lab2: " + problem2());
        System.out.println("Lab3: " + problem3());
        System.out.println("Lab4: " + Arrays.toString(problem4()));
        problem5 problem = new problem5();
        System.out.println("Lab5: " + problem.check(problem.digits));
    }
    public static int problem1(int num) {
        int i = 0; //число шагов в последовательности
        while (num != 1) {
            if (num % 2 == 1) {
                num = 3 * num + 1;
            } else {
                num = num / 2;
            }
            i++;
        }
        return i;
    }

    public static int problem2() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите кол-во элементов в последовательности:");
        int count = in.nextInt();
        int i = 1;
        int sum = 0;
        System.out.println("Введите элементы через Enter:");
        while (i <= count) {
            if (i % 2 == 0) {
                sum -= in.nextInt();
            } else {
                sum += in.nextInt();
            }
            i++;
        }
        return sum;
    }

    public static int problem3() {
        Scanner in = new Scanner(System.in);
        Map<String, Integer> coordinates = new HashMap<>(); //наши координаты
        coordinates.put("x", 0);
        coordinates.put("y", 0);
        Map<String, Integer> coordinatesOfTreasure = new HashMap<>(); //координаты клада
        System.out.println("Введите координаты клада");
        coordinatesOfTreasure.put("x", in.nextInt());
        coordinatesOfTreasure.put("y", in.nextInt());
        int steps = 0;
        String instruction = ""; //инструкция,в нее будет приходить направление, либо "стоп"
        int minimalInstructions = 0; //счетчик минимальных шагов
        boolean flag = true; //флаг для отслеживания, нужно ли увеличивать счетчик минимальных шагов
        if (coordinatesOfTreasure.get("x").equals(0) && coordinatesOfTreasure.get("y").equals(0)) {
            return 0; //если клад в (0,0), то вывести 0 шагов
        }
        while (true) {
            if (check(coordinates, coordinatesOfTreasure, instruction, steps)) {
                flag = false;
            } else if (coordinates.get("x").equals(coordinatesOfTreasure.get("x")) && coordinates.get("y").equals(coordinatesOfTreasure.get("y"))) { //проверка были ли мы на месте клада
                flag = false;
            }
            if (flag) {
                minimalInstructions++;
            }

            System.out.println("Введите инструкцию или 'стоп' для остановки блока инструкций");
            instruction = new Scanner(System.in).nextLine();
            if (instruction.equals("стоп")) {
                break;
            }
            System.out.println("Введите количество шагов");
            steps = new Scanner(System.in).nextInt();

            switch (instruction) {
                case "север" -> coordinates.put("y", coordinates.get("y") + steps);
                case "восток" -> coordinates.put("x", coordinates.get("x") + steps);
                case "юг" -> coordinates.put("y", coordinates.get("y") - steps);
                case "запад" -> coordinates.put("x", coordinates.get("x") - steps);
            }

        }
        return minimalInstructions;
    }

    public static boolean check(Map<String, Integer> coordinates, Map<String, Integer> coordinatesOfTreasure, String instruction, Integer steps) { //проверка не прошли ли мы мимо клада
        boolean output = false;
        switch (instruction) {
            case "север" -> {
                if (coordinates.get("x").equals(coordinatesOfTreasure.get("x")) && coordinates.get("y") + steps >= coordinatesOfTreasure.get("y")) {
                    output = true;
                }
            }
            case "восток" -> {
                if (coordinates.get("y").equals(coordinatesOfTreasure.get("y")) && coordinates.get("x") + steps >= coordinatesOfTreasure.get("x")) {
                    output = true;
                }
            }
            case "юг" -> {
                if (coordinates.get("x").equals(coordinatesOfTreasure.get("x")) && coordinates.get("y") - steps <= coordinatesOfTreasure.get("y")) {
                    output = true;
                }
            }
            case "запад" -> {
                if (coordinates.get("y").equals(coordinatesOfTreasure.get("y")) && coordinates.get("x") - steps <= coordinatesOfTreasure.get("x")) {
                    output = true;
                }
            }
        }
        return output;
    }

    public static int[] problem4() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество дорог:");
        ArrayList<ArrayList<Integer>> arrOfTunnelsOfRoads = new ArrayList<ArrayList<Integer>>(); //двумерный массив, столбцы - дороги, строки - туннели
        int countOfRoads = in.nextInt();
        for (int i = 0; i < countOfRoads; i++) { //заполняем дороги
            System.out.printf("Введите количество туннелей, встречающихся на %d дороге\n", i + 1);
            int countOfTunnels = in.nextInt();
            ArrayList<Integer> list = new ArrayList<Integer>();
            arrOfTunnelsOfRoads.add(list);
            for (int j = 0; j < countOfTunnels; j++) { //заполняем туннели
                System.out.printf("Введите высоту %d туннеля\n", j + 1);
                list.add(in.nextInt());
            }
        }
        ArrayList<Integer> arrOfMaxTunnelsForEachRoad = new ArrayList<Integer>(); //массив максимальной высоты туннеля каждой дороги
        for (int i = 0; i < arrOfTunnelsOfRoads.size(); i++) {
            arrOfMaxTunnelsForEachRoad.add(Collections.min(arrOfTunnelsOfRoads.get(i))); //проходимся по "дорогам", записываем минимальную высоту туннеля
        }
        int[] output = new int[2];
        output[0] = arrOfMaxTunnelsForEachRoad.indexOf(Collections.max(arrOfMaxTunnelsForEachRoad)) + 1; //получаем индекс максимальной высоты из всех минимальных, добавляем единицу, чтобы получить дорогу
        output[1] = Collections.max(arrOfMaxTunnelsForEachRoad); //сама эта максимальная высота
        return output;
    }


}

class problem5 {
    String[] digits = inputNumber().split("");
    String inputNumber() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите число");
        String num = in.nextLine();
        return num;
    }

    String check(String[] digits) {
        int sum = 0;
        int mul = 1;
        for (int i = 0; i < digits.length; i++) {
            sum += Integer.parseInt(digits[i]);
            mul *= Integer.parseInt(digits[i]);
        }
        if (sum % 2 == 0 && mul % 2 == 0) {
            return "Является";
        }
        return "Не является";
    }
}