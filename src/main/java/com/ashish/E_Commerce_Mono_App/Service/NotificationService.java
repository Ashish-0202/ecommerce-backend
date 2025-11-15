package com.ashish.E_Commerce_Mono_App.Service;

import com.ashish.E_Commerce_Mono_App.Entity.order_items;
import com.ashish.E_Commerce_Mono_App.Entity.orders;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void orderPlacedNotification(orders order) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");

        helper.setTo(order.getUser().getEmail());
        helper.setSubject("Order Confirmation - #" + order.getOrder_id());
        helper.setText(buildOrderPlacedEmail(order),true);

        System.out.println("Sending Order confirmation email for order ID: #"+ order.getOrder_id());
        javaMailSender.send(message);
    }

    private String buildItemsTable(List<order_items> items) {
        StringBuilder sb = new StringBuilder();

        for (order_items item : items) {
            sb.append("""
            <tr>
                <td>%s</td>
                <td>%d</td>
                <td>₹%.2f</td>
            </tr>
        """.formatted(item.getProduct().getProduct_name(), item.getQuantity(), item.getPrice()));
        }

        return sb.toString();
    }


    public String buildOrderPlacedEmail(orders order) {
        String template = """
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <style>
            body { font-family: Arial, sans-serif; background-color: #f8f9fa; padding: 20px; }
            .email-container { max-width: 600px; background: #fff; margin: auto; padding: 20px;
                               border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
            .header { text-align: center; padding-bottom: 20px; }
            .header h2 { color: #28a745; margin: 0; }
            .order-details { margin: 20px 0; }
            .order-details table { width: 100%%; border-collapse: collapse; }
            .order-details th, .order-details td {
                border: 1px solid #ddd; padding: 10px; text-align: left;
            }
            .order-details th { background-color: #f1f1f1; }
            .footer { text-align: center; margin-top: 30px; font-size: 13px; color: #555; }
        </style>
    </head>
    <body>
        <div class="email-container">
            <div class="header">
                <h2>Thank you for your order, {username}!</h2>
                <p>Your order <b>#{orderId}</b> has been placed successfully 🎉</p>
            </div>

            <div class="order-details">
                <h3>Order Summary</h3>
                <table>
                    <tr>
                        <th>Item</th>
                        <th>Qty</th>
                        <th>Price</th>
                    </tr>
                    {itemsTable}
                    <tr>
                        <td><b>Total</b></td>
                        <td></td>
                        <td><b>₹{total}</b></td>
                    </tr>
                </table>
            </div>

            <p>If you have any questions, feel free to reply to this email.</p>

            <div class="footer">
                <p>© 2025 TheCampingTales Shop. All rights reserved.</p>
            </div>
        </div>
    </body>
    </html>
    """;

        String itemsTable = buildItemsTable(order.getOrderItemsList());

        return template.replace("{username}", order.getUser().getUsername())
                .replace("{orderId}", String.valueOf(order.getOrder_id()))
                .replace("{itemsTable}", itemsTable)
                .replace("{total}", String.format("%.2f", order.getTotal_amount()));

    }

}
