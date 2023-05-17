package tri.sendmail.com;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @ClassName MyAuthenticator
 * @Description
 * @Author mzg
 * @Data 2023/5/17
 * @Version 1.0
 **/
public class MyAuthenticator extends Authenticator {
    String userName = null;

    String password = null;

    public MyAuthenticator() {
    }

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

}
