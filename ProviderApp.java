public class ProviderApp {
    public static void main(String[] args) {
        // 创建服务注册中心
        ServiceRegistry registry = new ZkServiceRegistry("localhost:2181");

        // 注册服务
        HelloService helloService = new HelloServiceImpl();
        registry.register(HelloService.class.getName(), new InetSocketAddress("localhost", 8080));

        // 将服务实例添加到本地注册表
        registry.addService(HelloService.class.getName(), helloService);

        // 启动RPC服务器
        RpcServer server = new RpcServer(8080, registry);
        server.start();
    }
}