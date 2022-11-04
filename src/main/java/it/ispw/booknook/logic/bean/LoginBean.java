package it.ispw.booknook.logic.bean;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginBean {

    private String username;
    private String email;
    private String password;

    public LoginBean() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginBean(String email, String password) {
        this.email = email;
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (checkEmail(email)) {
            this.email = email;
        }
        else {
            this.email = null;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (checkPassword(password)) {
            this.password = password;
        }
        else {
            this.password = null;
        }
    }

    private boolean checkEmail(String email) { //controllo sintattico email
        EmailValidator validator = EmailValidator.getInstance();
        return (email.isEmpty() || !validator.isValid(email));
    }

    private boolean checkPassword(String password) {  //controllo sintattico password
        		/*(                   # inizio
		(?=.*\d)              #   deve contenere almeno un numero da 0 a 9
		(?=.*[a-z])           #   deve contenere almeno un carattere minuscolo
		(?=.*[A-Z])           #   deve contenere almeno un carattere maiuscolo
		(?=.*[._-])          #   deve contenere almeno un carattere speciale tra questi "!?._-"
		.                     #   deve superare TUTTI i test precedenti
		{6,20}                #   la lunghezza deve essere minimo di 8 caratteri e massimo di 20
		)                     # fine
         */

        //controllo sintattico della password
        String stringPattern = "((?=.*\\d)(?=.*[!?._-]).{8,20})";
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
