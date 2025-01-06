import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    int hiddenNumber;
    boolean playerGuessedRight = false;
    int playerAttemptNumber;

    public void startGame() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        int playerVote = 0;


        System.out.println("Для начало игры вы должны выбрать сложность.");
        Thread.sleep(1000);
        System.out.println("В игре есть 3 сложности: Легкая (загаданное число от 1 до 5), " +
                "Средняя (загаданное число от 1 до 20) и Сложная (загаданное число от 1 до 100).");
        Thread.sleep(1000);
        System.out.print("Выберите сложность (легкая = 1, средняя = 2, сложная = 3): ");
        int difficult;
        while (true) {
            System.out.print("Выберите сложность (легкая = 1, средняя = 2, сложная = 3): ");
            try {
                difficult = scan.nextInt();
                if (difficult >= 1 && difficult <= 3) {
                    break;
                } else {
                    System.out.println("Ошибка: выберите число от 1 до 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите только число от 1 до 3.");
                scan.next();
            }
        }

        switch (difficult) {
            case 1:
                hiddenNumber = random.nextInt(5) + 1;
                System.out.println("Вы выбрали 'Легкую' сложность. Выбирайте числа от 1 до 5.");
                break;

            case 2:
                hiddenNumber = random.nextInt(20) + 1;
                System.out.println("Вы выбрали 'Среднюю' сложность. Выбирайте числа от 1 до 20.");
                break;

            case 3:
                System.out.println("Вы выбрали 'Тяжелую' сложность. Выбирайте числа от 1 до 100.");
                hiddenNumber = random.nextInt(100) + 1;
                break;
        }

        Thread.sleep(1000);
        System.out.print("Цифра загадана. Попробуйте угадать число: ");


        while (!playerGuessedRight) {
            try {
                playerVote = scan.nextInt();

                if (playerVote == hiddenNumber) {
                    System.out.println("Вы угадали число!");
                    playerGuessedRight = true;
                    gameEnd();
                }

                if (checkPlayerVoteToExceedingLimit(difficult, playerVote)) {
                    System.out.print("Вы превысили лимит загаданного числа. Выберите другое: ");
                    playerVote = scan.nextInt();
                }

                if (playerVote != hiddenNumber) {
                    System.out.print("Вы не угадали число. Попытайтесь еще раз: ");
                    playerVote = scan.nextInt();
                    playerAttemptNumber++;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите корректное число.");
                scan.next();
            }
        }
    }

    public void gameEnd() {
        if (playerAttemptNumber == 1) {
            System.out.println("\nОго! Вы угадали число с первой попытки! Вы очень удачливый если конечно играли не на легкой сложности.");
        } else {
            System.out.println("\nПоздравляю с победой в игре! Вы попробовали угадать число " + playerAttemptNumber + " раз");
        }
    }

    public boolean checkPlayerVoteToExceedingLimit(int difficult, int playerVote) {
        if (difficult == 1) {
            return playerVote > 5;
        }
        if (difficult == 2) {
            return playerVote > 20;
        } else {
            return playerVote > 100;
        }
    }

}
