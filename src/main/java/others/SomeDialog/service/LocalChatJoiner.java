package others.SomeDialog.service;

import others.SomeDialog.connection.Connection;
import others.SomeDialog.model.User;
import others.SomeDialog.service.Util.ConsoleHelper;
import others.SomeDialog.message.Message;
import others.SomeDialog.message.enums.MessageType;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.LockSupport;


public class LocalChatJoiner extends AbstractChatJoiner implements Runnable {

    private final User currentUser;

    public LocalChatJoiner(User currentUser) {
        this.currentUser = currentUser;
        new Thread(this).start();
    }

    @Override
    public void run() {
        join();
    }

    @Override
    public void join() {

        SocketThread socketThread = new SocketThread();
        socketThread.start();
        try {
            socketThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }


        if (!isUserJoined()) {
            ConsoleHelper.writeMessage("failed to join");
        }

        while (isUserJoined() && !currentUser.getSpeechList().isEmpty()) {
            while (isUserJoined() && !isPermissionToSend()) {
                LockSupport.parkNanos(10000);
            }
            try {
                getConnection().send(new Message(MessageType.TEXT, currentUser.getSpeechList().pollFirst()));
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения. Соединение будет закрыто.");
                setUserJoined(false);
            }
            //happens before: вызовов этого сеттера будет после вызова этогож сеттера из Server.play
            setPermissionToSend(false);
        }

    }

    public class SocketThread extends Thread {
        @Override
        public void run() {
            try {
                setConnection(new Connection(new Socket("localhost", 4004)));
                handShake();
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка во время присоединения " + currentUser.getName());
            }
        }

        private void handShake() throws IOException, ClassNotFoundException {

            while (true) {
                Message message = getConnection().receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    String clientName = currentUser.getName();
                    getConnection().send(new Message(MessageType.USER_NAME, clientName));
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    setUserJoined(true);
                    break;
                }
            }
        }
    }

    @Override
    public void exit() {
        try {
            getConnection().send(new Message(MessageType.USER_REMOVED));
        } catch (IOException e) {
            setUserJoined(false);
            ConsoleHelper.writeMessage("Error, connection will be closed");
        }
        setUserJoined(false);
    }
}
