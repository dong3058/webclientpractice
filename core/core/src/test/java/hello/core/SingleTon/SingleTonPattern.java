package hello.core.SingleTon;

public class SingleTonPattern {

    private static final SingleTonPattern instance=new SingleTonPattern();
    public static SingleTonPattern getInstance(){
    return instance;}


    private SingleTonPattern(){


    }//private로 new로 생성을 못하게 막아둠. 어..근대.. 스프링 컨테이너를쓰면 기본적으로 싱글톤패턴을 해준다고합니다.
    //직접 구현하긴했는대 여러문제떄문에 그냥 스프링에게 모든걸 맡기면 마음이 편합니다 ㅇ;스프링은...신이야!.
    public void logic(){
        System.out.println("싱글톤 객체 호출.");
    }



}