package pl.martyna.client.view;

/**
 * This class represents values of menu options
 * @author Martyna Drabinska
 * @version 1.0
 */
public enum MenuOption {

    /** change settings */
    SETTINGS("Settings: 0"),
    /** carry out a simulation */
    SIMULATE("Simulate: 1"),
    /** quit application */
    QUIT("Quit: 2"),
    /** display help */
    HELP("Help: 3");
    /** Description of each enum value */
     final  String message;

    MenuOption(String message){
        this.message = message;
   }

   public String getMessage(){
       return message;
   }
}
