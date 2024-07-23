package others.SomeDialog.service;

import others.SomeDialog.connection.Connection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractChatJoiner {

    private volatile boolean userJoined;
    private volatile boolean permissionToSend = false;
    private volatile Connection connection;

    public abstract void join();

    public abstract void exit();

}
