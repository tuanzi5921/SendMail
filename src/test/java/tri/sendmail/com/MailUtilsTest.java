package tri.sendmail.com;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

/**
 * @ClassName MailUtilsTest
 * @Description
 * @Author mzg
 * @Data 2023/5/17
 * @Version 1.0
 **/
public class MailUtilsTest {

    @Test
    public void sendMailTest(){
        MailUtils mailUtils = new MailUtils();
        EmailDto emailDto = new EmailDto();
        String content = "Hello:\n" +
                "这是一封测试邮件\n" +
                "谢谢！";
        emailDto.setServerName("smtp.263.net");
        emailDto.setPort("25");
        emailDto.setUserName("mzg@tri-ibiotech.com");
        emailDto.setPassword("m1850163421");
        emailDto.setSenderName("sendName");
        emailDto.setEmailAddress("mzg@tri-ibiotech.com");
        emailDto.setSubject("测试邮件发送功能");
        emailDto.setTo("1850163421@qq.com");
        emailDto.setcC("");
        emailDto.setContent(content);
        JSONArray filePath = new JSONArray();
        filePath.add("C:\\Users\\mzg\\Desktop\\excel\\pdf1.pdf");
        MailUtils.sendMail(emailDto,filePath);
    }

}
