package com.example.punyawork;
import java.security.Security;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMailSender extends javax.mail.Authenticator {
    private  String mailhost="smpt.gmail.com";
    private String useraccount;
    private Session session;
    private String password;

    static {
        Security.addProvider(new JSSEProvider());
    }
    public GMailSender(String useraccount, String password){
        this.useraccount=useraccount;
        this.password=password;
        Properties properties=new Properties();
        properties.setProperty("mail.transport.protocol","smpt");
        properties.setProperty("mail.host",mailhost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port",587);
        properties.setProperty("mail.smtp.quitwait", "false");
        properties.put("mail.smtp.ssl.trust","smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host","smtp.gmail.com" );
        session= Session.getDefaultInstance(properties,this);
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(useraccount, password);
    }
    public synchronized void sendMail(String subject, String body,
                                      String sender, String recipients) throws Exception {

        MimeMessage message=new MimeMessage(session);
        DataHandler handler=new DataHandler(new ByteArrayDataSource(body.getBytes(),"text/html"));
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);
        message.setDataHandler(handler);
        if(recipients.indexOf(',')>0){
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipients));
        }else{
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
        }
        Transport.send(message);


    }
}
