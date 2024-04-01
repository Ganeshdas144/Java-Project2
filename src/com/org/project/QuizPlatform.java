package com.org.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizPlatform {
    private List<User> users;
    private List<Quiz> quizzes;
    private Scanner scanner;

    public QuizPlatform() {
        this.users = new ArrayList<>();
        this.quizzes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Quiz Platform!");

        while (true) {
            System.out.println("\n1. Log in");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                showMainMenu(user);
                return;
            }
        }

        System.out.println("Invalid username or password. Please try again.");
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("Registration successful!");
    }

    private void showMainMenu(User user) {
        while (true) {
            System.out.println("\n1. Take Quiz");
            System.out.println("2. Create Quiz");
            System.out.println("3. Browse Quizzes");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    takeQuiz(user);
                    break;
                case 2:
                    createQuiz();
                    break;
                case 3:
                    browseQuizzes();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void takeQuiz(User user) {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Please create a quiz first.");
            return;
        }

        System.out.println("Available Quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }
        System.out.print("Select a quiz (enter quiz number): ");
        int quizIndex = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (quizIndex < 0 || quizIndex >= quizzes.size()) {
            System.out.println("Invalid quiz selection.");
            return;
        }

        Quiz selectedQuiz = quizzes.get(quizIndex);
        selectedQuiz.startQuiz(user);
    }

    private void createQuiz() {
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine();
        Quiz newQuiz = new Quiz(title, scanner);
        quizzes.add(newQuiz);
        System.out.println("Quiz created successfully!");

        while (true) {
            System.out.println("\n1. Add Multiple-Choice Question");
            System.out.println("2. Finish Quiz Creation");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addMultipleChoiceQuestion(newQuiz);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addMultipleChoiceQuestion(Quiz quiz) {
        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();
        System.out.print("Enter number of options: ");
        int numOptions = scanner.nextInt();
        scanner.nextLine(); 

        List<String> options = new ArrayList<>();
        for (int i = 0; i < numOptions; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            options.add(scanner.nextLine());
        }

        System.out.print("Enter correct option index (1-" + numOptions + "): ");
        int correctOptionIndex = scanner.nextInt() - 1;
        scanner.nextLine(); 

        Question question = new MultipleChoiceQuestion(questionText, options, correctOptionIndex);
        quiz.addQuestion(question);
        System.out.println("Question added to the quiz.");
    }


    private void browseQuizzes() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Please create a quiz first.");
            return;
        }

        System.out.println("Available Quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }
    }

    public static void main(String[] args) {
        QuizPlatform quizPlatform = new QuizPlatform();
        quizPlatform.start();
    }
}
       
