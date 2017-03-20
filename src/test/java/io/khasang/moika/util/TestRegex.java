package io.khasang.moika.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRegex {
    @Test
    public void test(){
        String regex = "^[A-Za-z]+|^[А-Яа-я]+";
        String regex1 = ".+@.+\\..+";
        System.out.println("Alex".matches(regex));
        System.out.println("Алекс".matches(regex));
        System.out.println("Alex123".matches(regex));
        System.out.println("Алексandr".matches(regex));
        System.out.println("abc.df".matches(regex1));
        System.out.println("ab@c.df".matches(regex1));
        Map<String,Object> map = new HashMap<>();
        map.put("odin","dvar");
        System.out.println("key: odin"+"  val: "+map.get("odin").toString());
    }

    @Test
    public void jSessionIdExtraction(){

        List<String> setCookies = Arrays.asList(new String[]{
                "Как мало денег",
                "JSESSIONID=69EC996846355734788F6D9D721C8359; Path=/; HttpOnly",
                "Но жизнь прекрасна!"});


            String jsessionId = jsessionId = setCookies.stream().filter((a)->a.startsWith("JSESSIONID="))
                    .map(s -> s.substring(0, s.indexOf(";"))).findAny().orElse("");

        System.out.println(jsessionId);

    }
}
