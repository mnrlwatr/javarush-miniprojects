package others.SomeDialog.model;

import others.SomeDialog.LocalChatJoinable;
import others.SomeDialog.service.AbstractChatJoiner;
import others.SomeDialog.service.LocalChatJoiner;
import others.SomeDialog.service.UserNameChecker;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class User implements LocalChatJoinable {
    private final String name;
    private final LinkedList<String> speechList;
    private AbstractChatJoiner chatJoiner;

    public User(String name) {
        // UserNameChecker.addNewUser() возвращает true если не существует юзера с таким именем.
        if (UserNameChecker.addNewUser(name)) {
            this.name = name;
        } else {
            throw new RuntimeException("User с именем " + name + " уже существует");
        }
        speechList = new LinkedList<>();
    }

    @Override
    public void joinToLocalChat() {
        // тут можно сделать FactoryMethod (Pattern)
        chatJoiner = new LocalChatJoiner(this);
    }


    /**
     * @throws NullPointerException если speech==null
     */
    public void addSpeech(String... speech) {
        speechList.addAll(List.of(speech));
        //todo case when no arguments: sitcomActorObj.addSpeech();
    }

    public void clearSpeechList() {
        speechList.clear();
    }

    @Override
    public void exitFromLocalChat() {
        chatJoiner.exit();
    }

}
