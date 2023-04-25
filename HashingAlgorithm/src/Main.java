// Hash приймає в себе message будь-якої довжини і повертає на вихід фіксовану довжину бітів
// MD5 - 128 bits
// SHA1 - 160 bits
// SHA2 - 256 bits

// Способи застосування:
// 1. Зберігання паролів і логінів в базі даних.Тобто коли юзер надсилає серверу password i username як plaintext,
// то ми на стороні сервера їх хешуємо і перевіряємо з даними в БД, якщо вони однакові то пароль і логін правильні.
// Якщо БД хакнуть, то зловмисники отримують лише захешовані значення.
// 2. Перевірка того, чи файл дійшов неушкодженим. Перед відправкою файла ми його хешуємо і цей хеш відправляємо
// разом з файлом як checksum. Коли юзер завантажив файл він його хешує і перевіряє з checksum, якщо вни однакові,
// то файл прийшов неушкодженим
// 3. Linked list in Blockchains. Тобто кожен блок буде посилатися на милулий через його хеш, бо кожен блок має два хеши,
// перший його власний і другий - це хеш попереднього блоку
public class Main {
    public static void main(String[] args) {
        MD5 md5 = new MD5();
        System.out.println(md5.digest("Hello world!")); // 86fb269d190d2c85f6e0468ceca42a20
        System.out.println(md5.digest("Hello world")); // 3e25960a79dbc69b674cd4ec67a72c62

        SHA256 sha256 = new SHA256();
        System.out.println(sha256.digest("Hello world!")); // c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a
        System.out.println(sha256.digest("Hello world")); // 64ec88ca00b268e5ba1a35678a1b5316d212f4f366b2477232534a8aeca37f3c
    }
}