package PG.util;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {

static String fromEmail="abc@gmail.com";
static String password="**** **** **** ****";

public static Session getSession(){

Properties props=new Properties();

props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
props.put("mail.smtp.auth","true");
props.put("mail.smtp.starttls.enable","true");

Session session=Session.getInstance(props,new Authenticator(){

protected PasswordAuthentication getPasswordAuthentication(){

return new PasswordAuthentication(fromEmail,password);

}

});

return session;

}

public static void sendConfirmEmail(String toEmail,String name,int roomId){

try{

Session session=getSession();

Message message=new MimeMessage(session);

message.setFrom(new InternetAddress(fromEmail));

message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(toEmail));

message.setSubject("Room Booking Confirmed");

message.setText(
"Hello "+name+
"\n\nYour room booking has been confirmed."+
"\nRoom Number: "+roomId+
"\n\nThank you for booking with us."
);

Transport.send(message);

}catch(Exception e){
e.printStackTrace();
}

}

public static void sendRejectEmail(String toEmail,String name,int roomId){

try{

Session session=getSession();

Message message=new MimeMessage(session);

message.setFrom(new InternetAddress(fromEmail));

message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(toEmail));

message.setSubject("Room Booking Rejected");

message.setText(
"Hello "+name+
"\n\nYour room booking request has been rejected."+
"\nRoom Number: "+roomId+
"\n\nPlease contact admin for more information."
);

Transport.send(message);

}catch(Exception e){
e.printStackTrace();
}

}

}