package mvc.view;

import mvc.controller.Controller;
import mvc.model.ModelData;


public class EditUserView implements View {
    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        System.out.println("User to be edited:");
        System.out.println("\t" + modelData.getActiveUser());
        System.out.println("===================================================");
    }

    public void printDeletedUser(ModelData modelData) {
        if (modelData.getDeletedUser().getName().endsWith(" (deleted)")) {
            System.out.println("completed successfully "+modelData.getDeletedUser());
        } else {
            System.out.println(modelData.getDeletedUser());
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventUserDeleted(long id){
        controller.onUserDelete(id);
    }

    public void fireEventUserChanged(String name, long id, int level){
        controller.onUserChange(name, id, level);
    }

}
