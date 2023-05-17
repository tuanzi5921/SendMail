package tri.sendmail.com;

import com.alibaba.fastjson.*;

/**
 * @ClassName EmailDto
 * @Description
 * @Author mzg
 * @Data 2023/5/16
 * @Version 1.0
 **/
public class EmailDto {
    private String serverName;
    private String port;
    private String userName;
    private String password;
    private String senderName;
    private String emailAddress;
    private String subject;
    private String to;
    private String cC;
    private String content;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getcC() {
        return cC;
    }

    public void setcC(String cC) {
        this.cC = cC;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
      * @Author mzg
      * @Description
      * @Date 2023/5/16
      * @Param
      * @return
      **/
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
