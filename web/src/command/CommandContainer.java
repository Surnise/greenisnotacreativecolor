package command;

import command.abs.ApprovePrescriptionCommand;
import command.abs.AskDoctorCommand;
import command.abs.DenyPrescriptionCommand;
import command.abs.ListUserCommand;
import command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private Map<String, ICommand> commands = new HashMap<>();
    private static final CommandContainer INSTANCE = new CommandContainer(); //FIX IT SOMEWHEN

    CommandContainer(){
        commands.put("adddrug", new AddDrugCommand());
        commands.put("addtocard", new AddToCardCommand());
        commands.put("approveorder", new ApproveOrderCommand());
//        commands.put("approveprescription", new ApprovePrescriptionCommand());
//        commands.put("askdoctor", new AskDoctorCommand());
        commands.put("changelocale", new ChangeLocaleCommand());
        commands.put("deleteuser", new DeleteUserCommand());
        commands.put("denyorder", new DenyOrderCommand());
//        commands.put("denyprescription", new DenyPrescriptionCommand());
        commands.put("listorder", new ListOrderCommand());
        commands.put("listuser", new ListUserCommand());
        commands.put("login", new LogInCommand());
        commands.put("logout", new LogOutCommand());
//        commands.put("makeorder", new MakeOrderCommand());
        commands.put("removedrug", new RemoveDrugCommand());
        commands.put("seekdrugs", new SeekDrugsCommand());
        commands.put("signup", new SignUpCommand());
        commands.put("homepage", new ToHomePageCommand());
        commands.put("profile", new ToProfileCommand());
        commands.put("updatedrug", new UpdateDrugCommand());
        commands.put("updateuser", new UpdateUserCommand());
    }

    public static CommandContainer getInstance(){
        return INSTANCE;
    }

    public ICommand getCommand(String name){
        return commands.get(name);
    }
}
