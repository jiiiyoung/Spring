package hello.core.singleton;

public class StatefulService {

    // 다음 코드를 무상태로 유지하기 위해서는 price를 받은 것을 다시 되돌려 주면 된다.
    // 여기에 price를 선언하는 것이 아닌,
    private int price; // 상태를 유지하는 필드

    // return price를 해주면 된다!
    public void order(String name, int price){
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; // 여기가  문제가 된다.
    }

    // getPrice를 할 이유가 없음. order에서 자동으로 return 해주기 때문에
    public int getPrice(){
        return price;
    }
}
