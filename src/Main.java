import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //转换为对应的输入数据
//        System.out.println("输入你的测试用例：");
//        Integer[] num = {};
//        BuildTree buildTree = new BuildTree();
//        TreeNode root = buildTree.buildTree(num);
        Integer[] nu = {1, 2, 3};

        //调用函数
        BuildTree buildTree = new BuildTree();
        TreeNode root = buildTree.buildTree(nu);

        List<String> ss = new ArrayList<>(Arrays.asList("ale", "apple", "monkey", "plea"));

//        int[] nums = {0, 3, 2, 1, 5, 6, 4};
//
//        String s1 = "abba";
//        String s2 = "dog cat cas dog";
//
//        Solution solution = new Solution();
//        DynamicProgram dp = new DynamicProgram();
//        int[] numArray = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
//        int ret = dp.wiggleMaxLength(numArray);

        Integer[] array = {2, 3, -2, 4};
        int[] arrays = {1, 2, 5};
        BuildTree buildTree1 = new BuildTree();
        TreeNode node = buildTree1.buildTree(array);
        DynamicProgram solution = new DynamicProgram();
        DFS dfs = new DFS();
        int ret = solution.coinChange(arrays, 10);
        //输出结果
//        for (int num : ret)
        System.out.println("你的计算机结果是：" + ret);
//        for (int s : ret) {
//            System.out.println(s);
//        }

        //多线程
//        MyThreadRunable mr = new MyThreadRunable();
//        Thread thread1 = new Thread(mr);
//        thread1.start();
//
//        MyThreadCallable mc = new MyThreadCallable();
//        FutureTask<Integer> ft = new FutureTask<>(mc);
//        Thread thread2 = new Thread(ft);
//        thread2.start();
//
//        MyThreadExtendThread me = new MyThreadExtendThread();
//        me.start();

        //线程池
//        SynchronizedExample mr1 = new SynchronizedExample();
//        SynchronizedExample mr2 = new SynchronizedExample();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(()->mr1.func1());
//        executorService.execute(()->mr2.func1());
//        executorService.shutdown();

        //join方法
//        JoinExample example = new JoinExample();
//        example.test();

        //subString
//        String s = "0123456";
//        System.out.println(s.substring( 1));
    }
}
