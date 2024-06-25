package com.example.webclientpractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.util.UriBuilder;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class Controllers {

    @Autowired
    WebClient webClient;

    @Autowired
    EntityManager em;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate<String,Object> redisTemplate;
    @GetMapping("/home")
    @ResponseBody
    public ResponseEntity<List<User>> test(HttpServletRequest req, HttpServletResponse resp){


        LocalDateTime start=LocalDateTime.now();
        /*List<String> users = Arrays.asList("암기잇", "노원구살아요", "훈내권","white");
        List<CompletableFuture<User>> futures = users.stream()
                .peek(x->log.info("namedata check:{}",x))
                .map(x->CompletableFuture.supplyAsync(()->webClient.get()
                        .uri(uriBuilder ->
                                uriBuilder.path("/v1/user/nickname")
                                        .queryParam("query", x)
                                        .build())
                        .retrieve()
                        .bodyToMono(User.class)
                                .block()

                        ))
                .collect(Collectors.toList());*/

       /* for (String username : users) {
            CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> {
                return webClient.get()
                        .uri(uriBuilder ->
                                uriBuilder.path("/v1/user/nickname")
                                        .queryParam("query", username)
                                        .build())
                        .retrieve()
                        .bodyToMono(User.class)
                       .block();
            });
            log.info("users:{}",username);
            futures.add(future);
        }*/
       /* CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<Mono<User>>> allResults = allOf.thenApply(v -> futures.stream()
                .map(x->x.join())
                .collect(Collectors.toList()));

        try {
            // 결과를 반환
            List<Mono<User>> userList = allResults.get();
            List<User>users2= userList.stream().map(x->x.block())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(users2, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error occurred while fetching users", e);
           return null;
        }*/

        List<String> users = Arrays.asList("","암기잇", "노원구살아요", "훈내권","제1배측골간근","하이용" ,"white");
    try{


      /* List<CompletableFuture<Mono<User>>> futures=users.stream()
                .map(x->CompletableFuture.supplyAsync(()->{
                    return webClient.get()
                            .uri(uriBuilder->uriBuilder.path("/v1/user/nickname")
                                    .queryParam("query",x)
                                    .build())
                            .retrieve()
                            .bodyToMono(User.class);


                }))
                .collect(Collectors.toList());*/

        List<CompletableFuture<User>> futures=users.stream()
                .map(x->{
                        HashMap<String,String> dummy=new HashMap<>();
                        CompletableFuture<User> userfuture=new CompletableFuture<>();
                        CompletableFuture.runAsync(()-> {
                                    webClient.get()
                                            .uri(uriBuilder -> uriBuilder.path("/v1/user/nickname")
                                                    .queryParam("query", x)
                                                    .build())
                                            .retrieve()
                                            .bodyToMono(User.class)
                                            //.onErrorResume(throwable -> {return Mono.error(new RuntimeException(throwable.getMessage()));})
                                            .subscribe(y-> {
                                                log.info("usrename:{}",x);
                                                userfuture.complete(y);});
                                }
                        );

                return userfuture;}
                )
                .collect(Collectors.toList());




        /*List<CompletableFuture<User>> futures=new ArrayList<>();
        for (String username : users) {
            CompletableFuture<User> future=new CompletableFuture<>();
             CompletableFuture.runAsync(() -> {
                webClient.get()
                        .uri(uriBuilder->uriBuilder.path("/v1/user/nickname")
                                .queryParam("query",username)
                                .build())
                        .retrieve()
                        .bodyToMono(User.class)
                        .subscribe(y->{
                            log.info("----userdata:{}------",y);
                            future.complete(y);
                        });
            });
            log.info("users:{}",username);
            futures.add(future);
        }*/


        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<User>> allResults = allOf.thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));



            log.info("start");
            List<User> userList = allResults.get();

            LocalDateTime end=LocalDateTime.now();
            Duration duration = Duration.between(start, end);
            log.info("start:{} end:{}",start,end);
            log.info("time:{}",duration.toMillis());

            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
         catch (InterruptedException | ExecutionException | RuntimeException e) {
            throw new RuntimeException("HI");}

    }


    @PostMapping("/file")
    @ResponseBody
    public String filetest(@ModelAttribute FilesTest filesTest) throws IOException {

        Path paths= Paths.get("filetest").resolve(filesTest.getMultipartFile().getOriginalFilename());

        log.info("name:{}",filesTest.getMultipartFile().getOriginalFilename());
        log.info("pahts:{}",paths.getParent());
        if(!Files.exists(paths.getParent())){
            log.info("here");
            Files.createDirectory(paths.getParent());
        }

        Files.copy(filesTest.getMultipartFile().getInputStream(), paths, StandardCopyOption.REPLACE_EXISTING);


        return "success";

    }


    @GetMapping("/getimg")
    @ResponseBody
    public String getimg() throws IOException{

        Path paths=Paths.get("filetest").resolve("delete.png");

        byte[] files=Files.readAllBytes(paths);


        return Base64.getEncoder().encodeToString(files);


    }
    @GetMapping("/resolveimg/{s}")
    public void resolves(@PathVariable("s") String url){
        byte[] files=Base64.getDecoder().decode(url);
        log.info("decoded file:{}",files);
    }


    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<String> saves(@ModelAttribute TextData textData) throws IOException{
        String texts=textData.getTextdata();

        BufferedWriter writer=new BufferedWriter(new FileWriter("filetest/text/test1"));
        writer.write(texts);

        writer.close();


        BufferedReader bufferedReader=new BufferedReader(new FileReader("filetest/text/test1"));

        String texts2="";
        String texts3="";
        while((texts3=bufferedReader.readLine())!=null){
            texts2+=texts3+"\n";
        }


        return new ResponseEntity<>(texts2,HttpStatus.OK);
    }

    @GetMapping("/chache")
    @Transactional
    @ResponseBody
    public String redisstave() throws JsonProcessingException {
        Member member=new Member("dong3058");
        Test test1=new Test("test1",member);
        Test test2=new Test("test2",member);
        em.persist(member);
        em.persist(test1);
        em.persist(test2);
        em.flush();
       // em.clear();
        ObjectMapper objectMapper=new ObjectMapper();
       // Member members=em.find(Member.class,1L);
        //Test test3=em.find(Test.class,2L);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("hi",objectMapper.writeValueAsString(member),100, TimeUnit.SECONDS);
        operations.set("hi2",objectMapper.writeValueAsString(test2),100,TimeUnit.SECONDS);
        String x=(String) redisTemplate.opsForValue().get("hi");
        Member mebmer2=objectMapper.readValue(x,Member.class);
        HashMap<String,String> str=new HashMap<>();
        str.put("hello","world");
        str.put("hi","world");
        HashOperations<String,String,Object> op=redisTemplate.opsForHash();
        op.put("hashtest","test","test3");
        redisTemplate.expire("hashtest",60,TimeUnit.SECONDS);
        op.put("hashtest","test","change");
        log.info("hash:{}",redisTemplate.opsForHash().get("hashtest","test"));
        log.info("member:{}",mebmer2.getName());
        return x;
    }

}
