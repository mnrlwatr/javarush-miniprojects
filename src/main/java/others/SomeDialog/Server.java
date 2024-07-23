package others.SomeDialog;

import others.SomeDialog.service.Util.ConsoleHelper;
import others.SomeDialog.connection.Connection;
import others.SomeDialog.message.Message;
import others.SomeDialog.message.enums.MessageType;
import others.SomeDialog.model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class Server {

    private static final Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    private static final Semaphore semaphore = new Semaphore(1);
    private static int consoleOutTime;
    private static volatile LinkedList<User> participantsQueue;

    private static class Handler extends Thread {

        private final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = handShake(connection);
                informJoinedUser(userName);
                mainLoop(connection, userName);
                informLeftUser(userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Error!");
            }

            if (userName != null) {
                connectionMap.remove(userName);
            }

        }

        /**
         * @param connection
         * @return Имя нового участника
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private String handShake(Connection connection) throws IOException, ClassNotFoundException {
            String clientName;
            // Если какая-то проверка не прошла, заново запросить имя пользователя
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    clientName = message.getData();
                    if (!clientName.isEmpty() && !connectionMap.containsKey(clientName)) {
                        connectionMap.put(clientName, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }

            return clientName;
        }

        /**
         * Цикл обработки сообщений
         *
         * @param connection
         * @param userName
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private void mainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String messageText = userName + ": " + message.getData();
                    timedConsoleOut(messageText);
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    break;
                }
            }

        }

        private void informJoinedUser(String userName) {
            ConsoleHelper.writeMessage(userName + " joined");
        }

        private void informLeftUser(String userName) {
            ConsoleHelper.writeMessage(userName + "  left");
        }
    }

    private static void timedConsoleOut(String speech) {
        try {
            TimeUnit.SECONDS.sleep(consoleOutTime);
            ConsoleHelper.writeMessage(speech);
            semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }


    /**
     * Участники по очереди будут писать своё сообщение с интервалом time
     * Одно сообщение за один очередь в participantsQueue
     *
     * @param participantsQueue Участники будут писать в порядке в котором их добавили в participantsQueue
     * @param time              Время в секундах
     */
    public static void configure(LinkedList<User> participantsQueue, int time) {
        consoleOutTime = time;
        Server.participantsQueue = participantsQueue;
    }

    public static void play() {
        // отдельным тредом сделано для того чтоб можно было в любой момент прервать диалог из main();
        new Thread(() -> play(participantsQueue)).start();
    }


    public static void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(4004)) {
            while (true) {
                serverSocket.setSoTimeout(3000);
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException ignored) {
        }
    }

    private static void play(LinkedList<User> participantsQueue) {
        for (User user : participantsQueue) {
            // если какой-то участник выйдет до прихода его очереди, то программа зависнет в semaphore.acquire
            // проверяем не вышел ли участник до semaphore.acquire
            if (user.getChatJoiner().isUserJoined()) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                user.getChatJoiner().setPermissionToSend(true);
            }

        }
    }

}
