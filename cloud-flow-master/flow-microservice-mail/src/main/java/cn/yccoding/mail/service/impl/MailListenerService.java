package cn.yccoding.mail.service.impl;

import cn.yccoding.common.contants.MqConstant;
import cn.yccoding.common.contants.ResultCodeEnum;
import cn.yccoding.common.exception.CustomException;
import cn.yccoding.common.form.UserForm;
import cn.yccoding.mail.form.MailForm;
import cn.yccoding.mail.form.sendgrid.MailInfoForm;
import cn.yccoding.mail.form.sendgrid.ReceiveUser;
import cn.yccoding.mail.service.MailService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @Author YC
 * @create 2020/4/5 邮件监听服务
 */
@Service
@Slf4j
public class MailListenerService {

    @Autowired
    private MailService mailService;

    @Autowired
    private CaptchaService captchaService;

    @RabbitListener(queues = MqConstant.MAIL_REGISTER_QUEUE)
    public void sendRegisterMail(Message message, Channel channel, UserForm form) throws IOException {
        log.info("为用户发送注册信息:[{}]", form.getUsername());

        try {
            MailForm mailForm = new MailForm();
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("username", form.getUsername());
            userMap.put("password", form.getPassword());
            mailForm.setTo(Arrays.asList(form.getEmail())).setSubject("注册通知").setTemplateName("register")
                .setContextVar(userMap);
            mailService.sendTemplateMail(mailForm);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("邮件发送成功");
        } catch (IOException e) {
            log.error("邮件发送失败", e.getMessage());
            // 回复消息处理失败，并重新入队
            // channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            throw new CustomException(ResultCodeEnum.MAIL_SEND_FAILED);
        }
    }

    @RabbitListener(queues = MqConstant.MAIL_SEND_GRID_QUEUE)
    public void sendCaptchaMail(Message message, Channel channel, UserForm form) throws IOException {
        // 创建收件人信息
        ReceiveUser receiveUser = new ReceiveUser();
        MailInfoForm mailInfoForm = new MailInfoForm(form.getEmail(), form.getEmail());
        List<MailInfoForm> toUsers = new ArrayList<>();
        toUsers.add(mailInfoForm);
        receiveUser.setToUser(toUsers);

        MailInfoForm toUserForm = receiveUser.getToUser().get(0);
        log.info("为单个用户发送验证码信息:[{}]", toUserForm.getName());

        try {
            captchaService.sendCaptcha(receiveUser);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("邮件发送完成");
        } catch (IOException e) {
            log.error("邮件发送失败", e.getMessage());
            // 回复消息处理失败，并重新入队
            // channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            throw new CustomException(ResultCodeEnum.MAIL_SEND_FAILED);
        }
    }
}
