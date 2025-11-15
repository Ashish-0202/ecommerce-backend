package com.ashish.E_Commerce_Mono_App.Service;

import com.ashish.E_Commerce_Mono_App.Entity.orders;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StatusUpdateNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(mimeMessage);
    }

    public void sendStatusUpdateNotification(orders order) throws MessagingException {
        String subject = "";
        String body = "";
        log.info("Status of order: {}",order.getStatus());
        switch(order.getStatus()) {
            case shipped :
                subject = "Your Order #" + order.getOrder_id() + " Has Been Shipped!";
                body= getShippedEmailBody(order);
                break;

            case out_for_delivery:
                subject = "Your Order #" + order.getOrder_id() + " Is Out for Delivery!";
                body = getOutForDeliveryEmailBody(order);
                break;

            case delivered:
                subject = "Your Order #" + order.getOrder_id() + "has been delivered!";
                body = getDeliveredEmailBody(order);
                break;
        }
        //
        log.info("Sending status update email to: {}",order.getUser().getEmail());
        log.info("Subject : {}",subject);
        sendEmail(order.getUser().getEmail(),subject,body);
    }
    private String getShippedEmailBody(orders order) {
        return """
                <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                        <h2 style="color: #2c3e50;">Your Order Has Been Shipped!</h2>
                        <p>Hello %s,</p>
                        <p>Good news! Your order <strong>#%d</strong> has been shipped and is on its way.</p>
                        <p><b>Estimated Delivery:</b> %s</p>
                        <p>You can track your order using the tracking ID once it’s updated in your account.</p>
                        <p>Thank you for shopping with us!<br>— E-Commerce Team</p>
                    </body>
                </html>
                """.formatted(order.getUser().getUsername(), order.getOrder_id(), "14th Nov 2025");
    }

    private String getOutForDeliveryEmailBody(orders order) {
        return """
                <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                        <h2 style="color: #16a085;">Your Order Is Out for Delivery!</h2>
                        <p>Hello %s,</p>
                        <p>Your order <strong>#%d</strong> is out for delivery and will reach you today.</p>
                        <p>Please ensure someone is available to receive the package.</p>
                        <p>Thank you for choosing us!<br>— E-Commerce Team</p>
                    </body>
                </html>
                """.formatted(order.getUser().getUsername(), order.getOrder_id());
    }

    private String getDeliveredEmailBody(orders order) {
        return """
            <html>
                <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                    <h2 style="color: #27ae60;">Your Order Has Been Delivered!</h2>
                    <p>Hello %s,</p>
                    <p>We’re happy to let you know that your order <strong>#%d</strong> has been successfully delivered.</p>
                    <p>We hope you enjoy your purchase! ❤️</p>
                    <p>If you have any questions or feedback, feel free to reach out to our support team.</p>
                    <p>Thank you for shopping with us!<br><b>— E-Commerce Team</b></p>
                    <hr style="border:none; border-top:1px solid #eee;">
                    <small style="color:#888;">This is an automated message. Please do not reply.</small>
                </body>
            </html>
            """.formatted(order.getUser().getUsername(), order.getOrder_id());
    }

}
