package others.SomeDialog;

import others.SomeDialog.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        //todo сделать отдельный класс который будет добавлять,изменять и отдавать тексты из файлов
        // у каждого участника будет свой отдельный файл текстов.

        User Chandler = new User("Chandler");
        Chandler.addSpeech("Hey.",
                "And this from the cry-for-help department. Are you wearing makeup?",
                "That`s so funny, `cause i am thinking you look more like Joey Tribbiani, man slash woman.",
                "Do you know which one you are gonna be?",
                "Good luck, man. I hope you get it.");

        User Joey = new User("Joey");
        Joey.addSpeech("Hey, hey.",
                "Yes, I am. As of today, i am officially Joey Tribbiani, actor slash model.",
                "You know those posters for the City Free Clinic?",
                "No, but I hear lyme disease is open, so... (crosses fingers)",
                "Thanks.");

        User Monica = new User("Monica");
        Monica.addSpeech("oh, wow, so you`re gonna be one of those \"healthy, healthy, healthy guys\"?");

        User Phoebe = new User("Phoebe");
        Phoebe.addSpeech("Hey.", "What were you modeling for?", "You know, the asthma guy was really cute.");

        User Ross = new User("Ross");

        User Rachel = new User("Rachel");

        List<LocalChatJoinable> participants = new ArrayList<>();
        Collections.addAll(participants, Chandler, Joey, Monica, Phoebe, Ross, Rachel);

        for (LocalChatJoinable participant : participants) {
            participant.joinToLocalChat();
        }


        LinkedList<User> participantsQueue = new LinkedList<>();
        participantsQueue.add(Joey);
        participantsQueue.add(Chandler);
        participantsQueue.add(Phoebe);
        participantsQueue.add(Chandler);
        participantsQueue.add(Joey);
        participantsQueue.add(Chandler);
        participantsQueue.add(Phoebe);
        participantsQueue.add(Joey);
        participantsQueue.add(Monica);
        participantsQueue.add(Phoebe);
        participantsQueue.add(Chandler);
        participantsQueue.add(Joey);
        participantsQueue.add(Chandler);
        participantsQueue.add(Joey);

        Server.configure(participantsQueue, 2);
        Server.runServer();

        Server.play();


        // если убрать sleep(35) то users.forEach(LocalChatJoinable::exit) сразу прервёт диалог
        // можете поставить sleep(20) и посмотреть на результат (диалог прервётся, участники не успеют сказать всю речь)
        try {
            TimeUnit.SECONDS.sleep(34);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        participants.forEach(LocalChatJoinable::exitFromLocalChat);

    }
}
