import io.github.wechaty.*
import io.github.wechaty.schemas.ScanStatus
import io.github.wechaty.user.ContactSelf
import io.github.wechaty.user.Message
import io.github.wechaty.utils.QrcodeUtils

fun main() {
    val token = "your_token";

    val bot = Wechaty.instance(token)
    bot.onScan(object : ScanListener {
        override fun handler(qrcode: String?, statusScanStatus: ScanStatus, data: String?) {
            println(qrcode?.let { QrcodeUtils.getQr(it) });
            println("Online Image: https://wechaty.github.io/qrcode/" + qrcode);
        }
    })

    bot.onLogin(object : LoginListener {
        override fun handler(self: ContactSelf) {
            println(self.name() + "登陆了")
        }
    })

    bot.onMessage(object : MessageListener {
        override fun handler(message: Message) {
            val text = message.text()
            val from = message.from()
            if (from != null) {
                println("收到来自" + from.name() + "的消息:" + text)
                from.say("[得意][得意]")
            }

        }
    })

    bot.start(true)
}


