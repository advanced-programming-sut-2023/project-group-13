package controller;

import model.Enums.SignupMenuCommands;
import model.Player;
import org.junit.Test;


import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;

public class SignupMenuControllerTest{

    Matcher matcher;
    String command;
    @Test
    public void invalidUsernameShouldReturnError(){
        command = "create user -u @mir -p Aa3#fsaf -c Aa3#fsaf -e email@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("Invalid username format.", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void weakPasswordLengthIsBelowSixShouldReturnError(){
        command = "create user -u amir -p Aa3h@ -c Aa3# -e email@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("weak password: password length is below 6.", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void weakPasswordPatternIsWrongShouldReturnError(){
        command = "create user -u amir -p Aa3fsaf -c Aa3fsaf -e email@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("weak password: password pattern is wrong", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void passwordAndConfirmationAreNotTheSameShouldReturnError(){
        command = "create user -u amir -p Aa3#fsaf -c aa3#fsaf -e email@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("password and confirmation are not the same.", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void emailInvalidShouldReturnError(){
        command = "create user -u amir -p Aa3#fsaf -c Aa3#fsaf -e emailgmailcom -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("Invalid email format.", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void emailAlreadyUsedShouldReturnError(){
        command = "create user -u amir -p Aa3#fsaf -c Aa3#fsaf -e mahmoudiamirmahdi82@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find()) {
            Player player = new Player("username", "Am+ir5", "stranger", "mahmoudiamirmahdi82@gmail.com", "nickname", 1, "answer");
            Player.players.add(player);
            assertEquals("this email already used.", SignupMenuController.signup(matcher, command));
        }
    }
    @Test
    public void usernameAlreadyUsedShouldReturnError() {
        command = "create user -u username -p Aa3#fsaf -c Aa3#fsaf -e mahmoudi@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_USER)).find()) {
            Player player = new Player("username", "Am+ir5", "stranger", "mahmoudiamirmahdi82@gmail.com", "nickname", 1, "answer");
            Player.players.add(player);
            assertEquals("player with this username exist, please try something different.", SignupMenuController.signup(matcher, command));
        }
    }
    @Test
    public void usernameEmptyShouldReturnError(){
        command = "create user -u  -p Aa3#fsaf -c Aa3#fsaf -e mahmoudi@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("username field is empty.", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void nicknameEmptyShouldReturnError(){
        command = "create user -u username -p Aa3#fsaf -c Aa3#fsaf -e mahmoudi@gmail.com -n ";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("nickname field is empty.",SignupMenuController.signup(matcher, command));
    }
    @Test
    public void emailEmptyUsedShouldReturnError(){
        command = "create user -u username -p Aa3#fsaf -c Aa3#fsaf -e  -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("email field is empty.", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void passwordConfirmationEmptyShouldReturnError(){
        command = "create user -u username -p Aa3#fsaf -c  -e mahmoudi@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("passwordConfirmation field is empty.", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void passwordEmptyShouldReturnError(){
        command = "create user -u username -p  -c aa3#fsaf -e mahmoudi@gmail.com -n feri";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("password field is empty.", SignupMenuController.signup(matcher, command));
    }
    @Test
    public void sloganEmptyShouldReturnError(){
        command = "create user -u username -p Aa3#fsaf -c Aa3#fsaf -e mahmoudi@gmail.com -n feri -s";
        if ((matcher = SignupMenuCommands.getMatcher(command , SignupMenuCommands.CREATE_USER)).find())
            assertEquals("slogan field is empty.", SignupMenuController.signup(matcher, command));
    }

}