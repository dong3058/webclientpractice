package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor//final붙은 애가지고 생성자를 만듬.
public class BasicItemController {

    private final ItemRepository itemrepository;
    /*@Autowired
    public BasicItemController(ItemRepository itemrepository) {

        this.itemrepository = itemrepository;}*/
    //위의 생성자는 autowired의 경우 새엉자

    @GetMapping
    public String items(Model model){

        List<Item> itemlist=itemrepository.FindAll();

        model.addAttribute("items",itemlist);

        return "basic/items";

    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long id,Model model){
        //오류가 발생하니까 패스바이레블이나 reqparam의 경우 저렇게 변수명을 적어주도록하자.
        Item item=itemrepository.FindItem(id);
        model.addAttribute("item",item);



        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
    /*@PostMapping("/add")
    public String save(@RequestParam("itemName") String itemname,@RequestParam("price") Integer price
    ,@RequestParam("quantity") Integer quantity,Model model){

        Item item=new Item();
        item.setItemName(itemname);
        item.setQuantity(quantity);
        item.setPrice(price);
        itemrepository.ItemSave(item);


        model.addAttribute("item",item);



        return "basic/item";
    }*/
    //@PostMapping("/add")
    public String save2(@ModelAttribute("item") Item item,Model model){

        itemrepository.ItemSave(item);


        //문제점이 존재하는대 이페이지에서 새로고침을 할경우 다시 보냇던 데이터를
        //재전송하게된다. 이는 웹브라우저가 마지막으로 보낸 요청을 다시 보내기떄문에
        //발생하는 문제이다.
        //즉 이 SAVE2는 사용자가 요청을 보내고 그에 대한 답으로 페이지를
        //띄워서 보내주므로 웹 브라우저가 보낸 마지막 요청은 이 POST 인것이다.
        //이것을 해결하기위해서 응답을 redirect로 보내는거싱다
        // 리다이렉트는 아에 새로 접속하는 것으로 즉 페이지를 스프링이찾아서  상품상세 화면을주는것이 아닌
        //재접속 하라는 명령을 response로 보내서 웹브라우저가 이것을 다시 요청 해서
        //상품이 상세 화면을 보여주는것이다
        //그러면 웹브라우저가 보낸 마지막요청은 바로 상품의 상세화면을 요청하는것이되므로
        // 새로고침을 해도 중복문제가 발생하지않는다.




        //model.addAttribute("item",item);
        //모델 어트리뷰트를 쓱 이름을 정해주면 자동으로 모델에다가 대입을 해줌. 즉 이렇게생략이가능.
        //파라미터로 넣은 Model model 도 생략이가능.
        //즉 모델 어트리뷰트는 게터세터로 값을 넣어주고 model에다가 자동으로 주입해주는 기능이존재.
        //이름을 정해주지않아고 클래스이름의 첫글자를 소문자로한값이 디폴트가 되서들어감
        //모델 어트리뷰트를 생략해도 똑같이 작동함.



        //return "basic/item";


        // 클래스 레벨에 달아둔 맨위의 basic items에다가 아이템 아이디를 붙여서
        //요청하는것.이러면위의 item 메서드가 호출이되서 상품의 상세페이지를 보여주게된다.
        return "redirect:/basic/items/"+item.getId();
    }



    @PostMapping("/add")
    public String save2(Item item, RedirectAttributes redirectAttributes){

        Item saveditem =itemrepository.ItemSave(item);
        redirectAttributes.addAttribute("itemIds",saveditem.getId());
        redirectAttributes.addAttribute("status",true);

        return "redirect:/basic/items/{itemIds}";//애는 걍 redirect에다가
        //적한 변수명대로 써주면된다.
        //리 다이렉트에 넣은 itemId이름의 값을 꺼내오고 남은 애들은 status로 ?status?=true
        //이런식으로 들어가게돈다.
    }




    @GetMapping("/{itemID}/edit")
    public String update(@PathVariable("itemID") Long id,Model model){
        Item item=itemrepository.FindItem(id);
        model.addAttribute("item",item);
        return "basic/editForm";

    }
    @PostMapping("/{itemID}/edit")
    public String update2(@PathVariable("itemID") Long id ,Item item){
        itemrepository.update(id,item);

        return "redirect:/basic/items/{itemID}";//경로에적힌 변수를 재활용.path에적힌 이름대로
        //써야되는것으로 보인다.

    }
    /*
    테스트용 데이터.
     */
    @PostConstruct
    public void init(){
        itemrepository.ItemSave(new Item("itema",100,200));
        itemrepository.ItemSave(new Item("itemv",100,200));

    }
}
