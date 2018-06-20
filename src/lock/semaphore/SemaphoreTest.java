package lock.semaphore;

/**
 * Created by jiangyunxiong on 2018/6/20.
 * <p>
 * 信号量锁
 */
public class SemaphoreTest implements Runnable {

    private ResourceManage resourceManage;
    private int userId;

    public SemaphoreTest(ResourceManage resourceManage, int userId) {
        this.resourceManage = resourceManage;
        this.userId = userId;
    }

    @Override
    public void run() {
        System.out.println("userId:" + userId + "准备使用资源...\n");
        resourceManage.useResource(userId);
        System.out.println("userId:" + userId + "使用资源完毕...\n");
    }

    public static void main(String[] args) {
        ResourceManage resourceManage = new ResourceManage();
        for (int i = 1;i<= 30;i++){
            new Thread(new SemaphoreTest(resourceManage, i)).start();// 创建多个资源使用者
        }
    }
}
