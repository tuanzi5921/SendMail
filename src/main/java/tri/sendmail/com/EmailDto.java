package tri.sendmail.com;

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

    /**
      * @Author mzg
      * @Description
      * @Date 2023/5/16
      * @Param
      * @return
      **/
    @Override
    public String toString() {
        // 原方法使用的是JSON格式字符串，在此临时修改返回值类型
        return this.toString();
    }
}
