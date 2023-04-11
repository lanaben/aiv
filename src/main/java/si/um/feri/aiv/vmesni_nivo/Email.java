package si.um.feri.aiv.vmesni_nivo;

import java.io.Serializable;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;
import java.util.Properties;

import jakarta.mail.PasswordAuthentication;


@Named
@SessionScoped
public class Email implements Serializable {

    private static final long serialVersionUID = 1544680932114626710L;

   // @Resource(mappedName = "java:jboss/mail/Default")
    
    private Session mySession;

    private String to;

    private String from;

    private String subject;

    private String body;	
    
    public Email(String to, String from, String subject, String body) {
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.body = body;
	
	}

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

   
    public void send(){
    	/*
    	try {
        	Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:jboss/mail/Default");
        } catch (NamingException e) {
        	System.out.println(e);
        }
        */
    	try {
    		final String username = "lana.benedicic@gmail.com";
            final String password = "xascrgmarruosbsm";
    		
    		Properties props = new Properties();
    		props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            
    		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
    		
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        Address toAddress = new InternetAddress(to);
	        message.addRecipient(Message.RecipientType.TO, toAddress);
	        message.setSubject(subject);
	        message.setContent(body, "text/plain");
	        Transport.send(message);
	        
    	}catch (Exception e) {
            System.out.println(e);
        }
    }
}