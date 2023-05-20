package model.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {

        CHANGE_USERNAME("^change -u (?<username>(\"[^\"]*\")|(\\S*))$"),
        CHANGE_NICKNAME("^change -n (?<nickname>(\"[^\"]*\")|(\\S*))$"),
        CHANGE_PASSWORD("(?=.* -o (?<oldPassword>(\"[^\"]*\")|(\\S*)))(?=.* -n (?<newPassword>(\"[^\"]*\")|(\\S*)))" +
                "^change password( -[on] ((\"[^\"]*\")|(\\S*))){2}$"),
        CHANGE_EMAIL("^change -e (?<email>(\"[^\"]*\")|(\\S*))$"),
        CHANGE_SLOGAN("^change slogan -s (?<slogan>(\"[^\"]*\")|(\\S*))$"),
        REMOVE_SLOGAN("^remove slogan$"),
        SHOW_HIGH_SCORE("^display highscore$"),
        SHOW_RANK("^display rank$"),
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