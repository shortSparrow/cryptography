public class Main {
    public static void main(String[] args) {
        String cipherText = "PACQDPHCLVCNHYLQCDQGCLCOLNHCVRIWZDUHCHQJLQHHULQJB"; // "my name is Kevin and I like software engineering!" with key 3
        CrackCaesar crackCaesar = new CrackCaesar();
        crackCaesar.crack(cipherText);
    }
}