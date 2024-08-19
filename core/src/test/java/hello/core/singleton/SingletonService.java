package hello.core.singleton;

public class SingletonService {

    // static으로 가지고 있어서 딱 하나만 생성(프로그램 실행 시)
    private static final SingletonService instance = new SingletonService();

    // ㅔpublic으로 열어서 객체 인스턴스가 필요하면 getInstance(static메서드)를 통해서 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService(){
    }

    public void login(){
        System.out.println("싱글톤 객체 로직 호출");
    }


}
