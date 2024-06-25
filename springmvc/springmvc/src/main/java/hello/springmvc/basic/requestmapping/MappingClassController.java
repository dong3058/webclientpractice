package hello.springmvc.basic.requestmapping;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {



    //@GetMapping("/mapping/users")
    @GetMapping
    public String user(){
        return "get users";

    }
    //@PostMapping("/mapping/users")
    @PostMapping
    public String adduser(){

        return "post user";
    }
    //@GetMapping("/mapping/users/{userid}")
    @GetMapping("/{userid}")
    public String finduser(@PathVariable String userid){

        return "get userid="+userid;

    }


    //@PatchMapping("/mapping/users/{userid}")
    @PatchMapping("/{userid}")
    public String patchuser(@PathVariable String userid){


        return "updata userod="+userid;
    }

    //@DeleteMapping("/mapping/users/{userid}")
    @DeleteMapping("/{userid}")
    public String deluser(@PathVariable String userid){

    return "del user="+userid;

    }
}
