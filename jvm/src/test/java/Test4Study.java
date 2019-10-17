import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 学习测试类
 *
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2019-09-10 16:25
 **/

public class Test4Study {

    private static HashMap<Integer,String> map=new HashMap<>();
    private static ConcurrentHashMap<Integer,String> cmap=new ConcurrentHashMap<>();

    public static void main(String[] args) {
        map.put(1,"tom");
        map.put(2,"Stave");
        map.put(3,"Sugar");
       System.out.println(map.get(3));

       cmap.put(1,"jack");
       cmap.put(2,"Mic");
       cmap.put(3,"Lily");
      System.out.println(cmap.get(3));
    }

}
