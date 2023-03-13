public class Main {
    public static void main(String[] args) {
        String englishText = "My name is Volkov Oleksandr from Kyiv, Ukraine. I am working as a software engineer at the moment";
        String spanishText = "Mi nombre es Sangeeta. Tengo 11 años. Vivo en Nueva Delhi, India con mi padre, madre, dos hermanos y tres hermanas. Mis abuelos también viven con nosotros. En la India, la familia es muy importante. Es comn tener abuelos, tias, tios y/o primos viviendo en la misma casa. Mis tías, tíos y primos también viven cerca. Nos vemos a menudo. Mi hermano mayor es programador de computadoras. Ahora mismo, él está en Australia. Su compañía lo envió allá por un año. Todos lo extrañamos mucho. Le escribimos cartas cada semana. Yo quiero que él venga a casa pronto.";

        LanguageDetector detector = new LanguageDetector();
        System.out.println(detector.isEnglish(spanishText)); // false
        System.out.println(detector.isEnglish(englishText)); // true
    }
}