package model.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {

        CHANGE_USERNAME("^profile change -u (?<username>(\"[^\"]*\")|(\\S*))$"),
        CHANGE_NICKNAME("^profile change -n (?<nickname>(\"[^\"]*\")|(\\S*))$"),
        CHANGE_PASSWORD("(?=.* -o (?<oldPassword>(\"[^\"]*\")|(\\S*)))(?=.* -n (?<newPassword>(\"[^\"]*\")|(\\S*)))" +
                "^profile change password( -[on] ((\"[^\"]*\")|(\\S*))){2}$"),
        CHANGE_EMAIL("^profile change -e (?<email>(\"[^\"]*\")|(\\S*))$"),
        CHANGE_SLOGAN("^profile change slogan -s (?<slogan>(\"[^\"]*\")|(\\S*))$"),
        REMOVE_SLOGAN("^profile remove slogan$"),
        SHOW_HIGH_SCORE("^profile display highscore$"),
        SHOW_RANK("^profile display rank$"),
        SHOW_SLOGAN("^profile display slogan$"),
        SHOW_PROFILE("^profile display$"),
        ;
        String regex;

        public String getRegex() {
                return regex;
        }

        ProfileMenuCommands(String regex) {
                this.regex = regex;
        }
        public static Matcher getMatcher(String command, ProfileMenuCommands Regex) {
                return  Pattern.compile(Regex.regex).matcher(command);
        }
}