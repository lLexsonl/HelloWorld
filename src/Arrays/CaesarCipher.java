/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arrays;

/**
 *
 * @author MIPC
 */
public class CaesarCipher {
    protected char[] encoder = new char[26];
    protected char[] decoder = new char[26];
    public CaesarCipher(int rotation){
        for(int k = 0; k < 26;k++){
            encoder[k] = (char)('A' + (k + rotation) % 26);
            decoder[k] = (char)('A' + (k - rotation + 26)% 26);
        }
    }
     /** Returns String representing encrypted message.
     * @param message
     * @return  */
    public String encrypt(String message){
        return transform(message, encoder);
    }
    /** Returns decrypted message given encrypted secret.
     * @param secret
     * @return  */
    public String decrypt(String secret){
        return transform(secret, decoder);
    }
    /** Returns transformation of original String using given code. */
    private String transform(String original, char[] code){
        char[] msg = original.toCharArray();
        for(int k = 0; k < msg.length;k++){
            if(Character.isUpperCase(msg[k])){ // we have a letter to change
                int j = msg[k] - 'A';
                msg[k] = code[j];
            }
        }
        return new String(msg);
    }
    public static void main(String[] args){
        CaesarCipher cipher = new CaesarCipher(3);
        System.out.println("Encryption code = " + new String(cipher.encoder));
        System.out.println("Encryption code = " + new String(cipher.decoder));
        String message = "THE EAGLE IS IN PLAY; MEET AT JOE'S.";
        String coded = cipher.encrypt(message);
        System.out.println("Secret: " + coded);
        String anwer = cipher.decrypt(coded);
        System.out.println("Message: " + anwer);
    }
}
