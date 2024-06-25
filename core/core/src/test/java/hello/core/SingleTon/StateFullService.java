package hello.core.SingleTon;

public class StateFullService {

    private int price;
    public void order(String home,int price){

        System.out.println("name="+home+"price"+price);
    //this.price=price;
    //return price;//이걸 지역변수 넘겨서 다른 변수에 저장하도록 하면된다. 그럼 stateless가된다.
}
public int getprice(){
    return price;
}
}
