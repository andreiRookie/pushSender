import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()

        .putData("action", "LIKE")
        .putData("content", """{
            "userId": 1,
            "userName": "Andrei",
            "postId": 2,
            "postAuthor": "Netology"
            }""".trimIndent())
        .setToken(token)
        .build()

    val newPostMessage = Message.builder()

        .putData("action", "NEW_POST")
        .putData("content", """{
            "postId": 2,
            "postAuthor": "Netology",
            "postContent": "Продумайте, в каком формате и какими бы данными вы реализовали уведомление о новом посте.
            Для этого вам необходимо модифицировать:
            Push-Sender
            Ваше приложение
            Кроме того, ознакомьтесь с разделом документации, описывающей возможность \"раскрытия\" текста уведомления.
            Реализуйте показ уведомлений о новых постах в формате:
            <имя пользователя> опубликовал новый пост:
            Текст поста... (на несколько строк)
            В качестве результата пришлите ссылку на ваши GitHub-проекты (Android-приложение и Push-Sender)
            в личном кабинете студента на сайте netology.ru.
            Важно: ни в коем случае не закидывайте в GitHub репозиторий учётные данные (файл google-services.json).
            Если вы всё-таки сделали это, то воспользуйтесь соответствующей утилитой для их удаления.
            Для того, чтобы сохранить работоспособность GitHub Actions воспользуйтесь инструкцией ниже.
            Инструкция для безопасного добавления google-services.json
            Вместо добавления файла в открытый доступ, можно добавить его в специальную секцию Secrets у репозитория. Она доступна только соавторам репозитория и не появится публично."
            }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
    FirebaseMessaging.getInstance().send(newPostMessage)




}