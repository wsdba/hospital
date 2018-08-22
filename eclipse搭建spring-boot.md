​					**Eclispe搭建Spring Boot**

一、安装STS工具（Spring Tool Suite for Eclipse)

Help -> Eclipse Marktplace

Search或选择“Popular"标签，选择Spring Tool Suite  （STS） for Eclipse插件安装。（需要等待一段时间）

二、new project ，选择spring -> spring starter project

![1534948014439](C:\Users\walss\AppData\Local\Temp\1534948014439.png)

### 3、按自己的信息填写，我这里项目名叫hospital

![1534948087525](C:\Users\walss\AppData\Local\Temp\1534948087525.png)

### 4、选择版本和组件

![1534948183196](C:\Users\walss\AppData\Local\Temp\1534948183196.png)

我这里选了2.0.4版本，选了mysql和web，因为是web项目，都会选择web这个选项，其他的可以按自己需要选择，点击 Finish ，就会有一个新项目，不过需要等待几分钟，sts工具会生成spring boot目录的结构及文件 

### 5、可以看到，项目结构已经生成完毕

![1534948246687](C:\Users\walss\AppData\Local\Temp\1534948246687.png)

### 6、写一个自己的Controller测试

```
@RestController
public class TestController {

    @RequestMapping("/hello1")
    public String test(){
        return "hello,this is a springboot demo";  
    }
}
这里用了@RestController进行测试，也可以写@Controller，写自己的页面
```

### 7、运行spring-boot的入口文件`Demo2Application.java`

```
@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
}
```