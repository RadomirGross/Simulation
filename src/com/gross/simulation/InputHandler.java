package com.gross.simulation;

import java.util.Scanner;


    class InputHandler implements Runnable {
        private final boolean[] running;
        private final boolean[] pause;

        public InputHandler(boolean[] running, boolean[] pause) {
            this.running = running;
            this.pause = pause;
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (running[0]) {
                System.out.println("Введите команду (space для паузы, q для выхода): ");
                String input = scanner.nextLine();

                switch (input) {
                    case "q":
                        System.out.println("Завершение работы программы");
                        running[0] = false;
                    break;
                    case " ":
                        pause[0] = !pause[0];
                        if (pause[0]) {
                            System.out.println("Симуляция на паузе...");
                        } else {
                            System.out.println("Симуляция возобновлена...");
                        }

                }
            }
            scanner.close();
        }
}
