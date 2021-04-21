package com.saulo.webstore.utils;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    // Gera um código único baseado em 3 letras 7 números e 2 letras, no total 5 letras e 7 números.
    public static String code5L7N(){

        List<Integer> numero = new ArrayList<>();
        for(int x = 0; x < 7; x++){
            int na = randomNumberOnInterval(0,9);
            numero.add(na);
        }
        String result = numero.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(""));

        return stringAlphabetic(3,true)+result+stringAlphabetic(2,true);
    }

    // Gera um número aleatório entre o intervalo mínimo e máximo.
    public static int randomNumberOnInterval(int minimo, int maximo) {

        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }

    // Retorna o tempo em Millis
    public static long seconds(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return (calendar.getTimeInMillis());
    }

    // Gera uma String alfabética, o primeiro parametro é um número para tamanho e o segundo é um boolean, true para UpperCase e false para LowerCase.
    public static String stringAlphabetic(int len, boolean upOrLower){

        int leftLimit = 97; // letra 'a'
        int rightLimit = 122; // letra 'z'
        int targetStringLength = len; // tamanho da string
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(len);

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        if(upOrLower){
            return generatedString.toUpperCase();
        }else {
            return generatedString;
        }

    }

    // Gera uma String alfanumérica, o primeiro parâmetro é um número para tamanho e o segundo é um boolean, true para UpperCase e false para LowerCase.
    public static String stringAlphanumeric(int len, boolean upOrLower){
        int leftLimit = 48; // numero '0'
        int rightLimit = 122; // letra 'z'
        int targetStringLength = len; // tamanho da string
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        if(upOrLower){
            return generatedString.toUpperCase();
        }else {
            return generatedString;
        }

    }

}
