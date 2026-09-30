import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    void displayQuestion() {
        System.out.println("\n" + question);

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Player> leaderboard = new ArrayList<>();

    static ArrayList<Question> questions = new ArrayList<>();

    // Add quiz questions
    static void loadQuestions() {

        questions.add(new Question(
                "Which language is used to develop this project?",
                new String[]{"Python", "Java", "C", "HTML"},
                2
        ));

        questions.add(new Question(
                "Which keyword is used to create a class in Java?",
                new String[]{"class", "Class", "create", "newclass"},
                1
        ));

        questions.add(new Question(
                "Which method is the starting point of a Java program?",
                new String[]{"start()", "run()", "main()", "execute()"},
                3
        ));

        questions.add(new Question(
                "Which collection allows duplicate elements in Java?",
                new String[]{"ArrayList", "HashSet", "TreeSet", "Map"},
                1
        ));

        questions.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"implements", "extends", "inherits", "super"},
                2
        ));
    }

    // Start quiz
    static void startQuiz() {

        System.out.println("\n===== START QUIZ =====");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        int score = 0;

        for (Question question : questions) {

            question.displayQuestion();

            int answer;

            while (true) {
                System.out.print("Enter your answer (1-4): ");

                try {
                    answer = Integer.parseInt(scanner.nextLine());

                    if (answer >= 1 && answer <= 4) {
                        break;
                    }

                    System.out.println("Please enter a number between 1 and 4.");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            if (answer == question.correctAnswer) {
                System.out.println("✓ Correct!");
                score++;
            } else {
                System.out.println("✗ Wrong!");
                System.out.println(
                        "Correct answer: " +
                        question.options[question.correctAnswer - 1]
                );
            }
        }

        System.out.println("\n===== QUIZ COMPLETED =====");
        System.out.println("Player: " + name);
        System.out.println("Score : " + score + "/" + questions.size());

        leaderboard.add(new Player(name, score));

        System.out.println("Your score has been added to the leaderboard.");
    }

    // Display leaderboard
    static void showLeaderboard() {

        System.out.println("\n========== LEADERBOARD ==========");

        if (leaderboard.isEmpty()) {
            System.out.println("No players have completed the quiz yet.");
            return;
        }

        // Sort players by score in descending order
        Collections.sort(
                leaderboard,
                Comparator.comparingInt((Player p) -> p.score).reversed()
        );

        System.out.println("---------------------------------");
        System.out.printf("%-10s %-20s %-10s%n",
                "Rank", "Player", "Score");
        System.out.println("---------------------------------");

        for (int i = 0; i < leaderboard.size(); i++) {

            Player player = leaderboard.get(i);

            System.out.printf(
                    "%-10d %-20s %-10d%n",
                    i + 1,
                    player.name,
                    player.score
            );
        }

        System.out.println("---------------------------------");
    }

    // Main menu
    public static void main(String[] args) {

        loadQuestions();

        while (true) {

            System.out.println("\n======================================");
            System.out.println("     QUIZ PLATFORM WITH LEADERBOARD");
            System.out.println("======================================");
            System.out.println("1. Start Quiz");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    startQuiz();
                    break;

                case 2:
                    showLeaderboard();
                    break;

                case 3:
                    System.out.println(
                            "Thank you for using Quiz Platform!"
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1, 2, or 3."
                    );
            }
        }
    }
}
