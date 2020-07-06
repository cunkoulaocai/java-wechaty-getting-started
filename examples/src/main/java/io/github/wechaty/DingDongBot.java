package io.github.wechaty;


import io.github.wechaty.schemas.ContactQueryFilter;
import io.github.wechaty.schemas.RoomMemberQueryFilter;
import io.github.wechaty.user.*;
import io.github.wechaty.user.manager.ContactManager;
import io.github.wechaty.user.manager.RoomManager;
import io.github.wechaty.utils.QrcodeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class DingDongBot {


    public static void main(String[] args) throws Exception {

        final String token = "your_token";

        if (StringUtils.isBlank(token)) {

            System.out.println("Error: WECHATY_PUPPET_HOSTIE_TOKEN is not found in the environment variables");
            System.out.println("You need a TOKEN to run the Java Wechaty. Please goto our README for details");
            System.out.println("https://github.com/wechaty/java-wechaty-getting-started/#wechaty_puppet_hostie_token");

            throw new Exception("need a token");
        }
        Wechaty bot = Wechaty.instance(token);

        bot.onScan((qrcode, statusScanStatus, data) -> {
                System.out.println(QrcodeUtils.getQr(qrcode));
                System.out.println("Online Image: https://wechaty.github.io/qrcode/" + qrcode);
            });

        bot.onMessage(message -> {
            System.out.println(message.text());

            Contact from = message.from();
            Room room = message.room();
            Contact to = message.to();
            System.out.println("to:" + to.name());
            String text = message.text();
            System.out.println(from.name());
            RoomManager roomManager = new RoomManager(to.getWechaty());


            if (StringUtils.equals(text, "#ding")) {
                if (room != null) {
                    room.say("dong");
                } else {
                    // 想对面发消息
                    from.say("一对一聊天");
                    to.say("你好");
                }
            }
        });
        bot.start(true);

    }


}
