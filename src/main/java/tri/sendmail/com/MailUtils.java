package tri.sendmail.com;


import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

/**
 * @ClassName MailUtils
 * @Description
 * @Author mzg
 * @Data 2023/5/17
 * @Version 1.0
 **/
public class MailUtils {
    /**
     * 发送邮件
     * @param emailDto email配置信息
     * @param filePath  本地附件地址
     */
    public static void sendMail(EmailDto emailDto, JSONArray filePath) {
        Transport transport = null;
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", emailDto.getServerName());  //邮箱发送
            prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
            prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码
            //1、创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //传入发件人的姓名和授权码
                    return new PasswordAuthentication(emailDto.getUserName(), emailDto.getPassword());
                }
            });
            //2、通过session获取transport对象
            transport = session.getTransport();
            //3、通过transport对象邮箱用户名和授权码连接邮箱服务器
            transport.connect(emailDto.getServerName(), emailDto.getUserName(), emailDto.getPassword());
            //4、创建邮件,传入session对象
            MimeMessage mimeMessage = getMimeMessage(session, emailDto, filePath);
            //5、发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 组装发送信息
     * @param session session
     * @param emailDto 邮件配置信息
     * @param filesPath 本地地址
     * @return MimeMessage
     * @throws Exception  Exception
     */
    private static MimeMessage getMimeMessage(Session session, EmailDto emailDto, JSONArray filesPath) throws Exception {
        //消息的固定信息
        MimeMessage mimeMessage = new MimeMessage(session);
        //发件人
        mimeMessage.setFrom(new InternetAddress(emailDto.getEmailAddress()));
        //收件人
        if (StringUtils.isNotBlank(emailDto.getTo())) {
            String[] tos = emailDto.getTo().split(",");
            for (String to : tos) {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
        }
        //抄送人
        if (StringUtils.isNotBlank(emailDto.getcC())) {
            String[] ccs = emailDto.getTo().split(",");
            for (String cc : ccs) {
                mimeMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            }
        }
        //邮件标题
        mimeMessage.setSubject(emailDto.getSubject());
        MimeMultipart multipart = new MimeMultipart();
        //附件
        for (Object pathObj : filesPath) {
            MimeBodyPart attachPart = new MimeBodyPart();
            File file = new File((String) pathObj);
            attachPart.setDataHandler(new DataHandler(new FileDataSource(file)));
            //避免中文乱码的处理
            attachPart.setFileName(MimeUtility.encodeWord(file.getName()));
            multipart.addBodyPart(attachPart);//附件
        }
        //正文
        MimeBodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(emailDto.getContent(), "text/html;charset=utf-8");
        multipart.addBodyPart(contentPart);
        multipart.setSubType("mixed"); //正文和附件都存在邮件中，所有类型设置为mixed
        //放到Message消息中
        mimeMessage.setContent(multipart);
        mimeMessage.saveChanges();//保存修改
        return mimeMessage;
    }
}
