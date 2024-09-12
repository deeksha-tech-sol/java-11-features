package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");

        //1. String class: isBlank, lines, strip, stripLeading, stripTrailing, and repeat.
        String multilineString = "It helps \n \n developers \n explore Java.";
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
        System.out.println(lines);

        String s = " This is a string ";
        System.out.println(s.stripLeading());
        System.out.println(s.stripTrailing());
        System.out.println(s.strip());
        System.out.println(s.repeat(3));


        //2. File Methods readString writeString
        Path filePath = Files.writeString(Files.createTempFile( "demo", ".txt"), "Sample text");
        String fileContent = Files.readString(filePath);
        System.out.println(fileContent);

        //3. Collection to Array
        List<String> sampleList = Arrays.asList("Java", "Kotlin");
        String[] sampleArray = sampleList.toArray(String[]::new);
        System.out.println(Arrays.toString(sampleArray));

        //4. Not Predicate
        List<String> arr = Arrays.asList("Java", "Kotlin", "\n \n"," ");
        List<String> withoutBlanks = arr.stream().filter(Predicate.not(String::isBlank)).collect(Collectors.toList());
        System.out.println(withoutBlanks);

        //5. var
        //.map((@Nonnull var x) -> x.toUpperCase())
        sampleList.stream().map((var x) -> x.toLowerCase()).forEach(System.out::println);

        //6. Http Client
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:" + 8080))
                .build();
        //HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}