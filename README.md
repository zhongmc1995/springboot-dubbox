# springboot-dubbox
springboot整合dubbox简单示例
## 准备工作
  1. 安装了zookeeper
  2. IDEA编译器
## 新建项目
  1. 在idea中新建一个空项目
  2. 在空项目的基础上新建三个module
  3. 第一个module为interface，其中定义暴露的接口(这个项目可以为普通的Maven项目)
  4. 第二个module为provider，这个为接口的提供者，依赖interface模块，这个模块为springboot项目
    其中要实现暴露的接口，同时发布到dubbo上去，配置如下：
```xml
<!-- 提供方应用名称信息，这个相当于起一个名字，我们dubbo管理页面比较清晰是哪个应用暴露出来的  -->
<dubbo:application name="dubbo-provider"></dubbo:application>
<!--使用注解方式暴露接口
<dubbo:annotation package="com.zmc" />  -->
<!-- 使用zookeeper注册中心暴露服务地址 -->
<dubbo:registry address="zookeeper://192.168.154.130:2181" check="false" subscribe="false" register=""></dubbo:registry>
<!-- 要暴露的服务接口,其中dubboxInterfaceImpl名字要和类名一致，第一个字母小写，否则无法注入-->
<dubbo:service interface="com.zmc.DubboxInterface" ref="dubboxInterfaceImpl" />
```
   然后在新建一个启动类，并加载配置文件：
```java
/**
 * Created by zhongmc on 2017/5/21.
 */
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }
}
```
  5.第三个module为consumer，这个为消费者，这个项目必须是一个springboot web项目
    同时定义配置文件：
```xml
<!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
<dubbo:application name="dubbo-consumer"/>
<dubbo:registry address="zookeeper://192.168.154.130:2181" />
<!-- 生成远程服务代理，可以和本地bean一样使用demoService -->
<dubbo:reference id="dubboxInterface" interface="com.zmc.DubboxInterface" />
```
  然后定义加载类：
```java
/**
 * Created by zhongmc on 2017/5/21.
 */
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
```
  6.最后先启动提供者module，然后再启动consumer即可
  
