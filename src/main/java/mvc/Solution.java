package mvc;

import mvc.controller.Controller;
import mvc.model.FakeModel;
import mvc.model.MainModel;
import mvc.model.Model;
import mvc.view.EditUserView;
import mvc.view.UsersView;


public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();
        Controller controller = new Controller();

        usersView.setController(controller);
        editUserView.setController(controller);

        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);

        usersView.fireEventShowAllUsers();

        usersView.fireEventOpenUserEditForm(126);
        editUserView.fireEventUserDeleted(124);
        editUserView.fireEventUserDeleted(130); // no User with such id
        editUserView.fireEventUserChanged("Петров", 125, 2);

        usersView.fireEventShowDeletedUsers();

    }
}