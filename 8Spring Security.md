### Spring Security

#### 一、简述

> springsecurity底层实现为一条过滤器链，就是用户请求进来，判断有没有请求的权限，抛出异常，重定向跳转。

#### 二、基本使用

> 创建一个springboot项目，添加一个HelloController，导入spring-security的启动器依赖，运行即可。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

```java
@Controller
public class HelloController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
```

> 启动项目，控制台会打印一个动态生成的密码，访问/hello，会自动跳转到一个自带的登录页面。
>
> 输入用户名：user
>
> 密码就是控制台输出的密码，登录后会跳转到/hello路径，说明springsecurity已经成功使用。

#### 三、自定义登录

##### 3.1 自定义登录页面

> 此时登录页面是系统提供的，如果需要自定义页面，需要如下操作：

> 添加页面：

login.html

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>$Title$</title>
    <base th:href="${#request.getContextPath()}+'/'">
</head>
<body>
<form action="/aaa" method="post">
    用户名：<input type="text" name="username" /><br>
    密码：<input type="password" name="password"/><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
```

home.html

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>$Title$</title>
    <base th:href="${#request.getContextPath()}+'/'">
</head>
<body bgcolor="pink">
<h2 align="center">欢迎来到主页面！</h2>
</body>
</html>
```

```java
@EnableWebSecurity // 允许使用安全框架
@Configuration // 配置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //所有的请求需要使用安全验证
                .antMatchers( "/home").permitAll() // 该路径允许访问
                .anyRequest().authenticated() // 其他的请求需要使用安全验证
                .and()
                .formLogin() // 使用表单登录
                .loginPage("/login").permitAll() // 登录页面路径，该路径直接允许访问
                .loginProcessingUrl("/aaa") // 登录提交路径，需要与表单action中的路径一直，实际上是一个虚拟路径
                .defaultSuccessUrl("/home") // 默认成功后跳转路径
                .and()
                .logout().permitAll() // 登出操作允许直接访问
                .and()
                .csrf().disable();//关闭跨站请求伪造攻击拦截;
    }
}
```

> 修改HelloController

```java
@Controller
public class HelloController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    // 登录页面
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/home")
    public String home(){
        return "/home";
    }
}
```

> 此时再尝试登录，会出现自己的登录页面。

> [注意：]()此时表单的用户名name必须设置为username，密码必须设置为password。如果也需要自定义，可以再defaultSuccessUrl('/home')后面添加代码usernameParameter()以及passwordParameter()

##### 3.2 自定义数据库操作

> 如果需要自定义登录验证去操作数据库，可以添加以下类：

```java
// 安全验证类
@Component
public class MyUserDetailService implements UserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;

    // 根据用户名查找
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名查找用户信息

        //将查找到的密码进行bcrypt加密
        String pwd = "123456";
        //String cryptPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
        String cryptPwd = passwordEncoder.encode(pwd);
        System.out.println("加密后的密码为：" + cryptPwd);

        return new User(s, cryptPwd,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,add,delete")); //账号 密码 权限
    }
}
```

> 并且修改WebSecurityConfig类，在里面添加方法：

```java
@Bean
public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

##### 3.3 角色权限验证

> 如果需要使用鉴权功能，有多种方案，此处介绍注解方案：

> 修改WebSecurityConfig类，在类上添加注解：

```java
@EnableWebSecurity // 允许使用安全框架
@Configuration // 配置
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) // 允许使用注解权限
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
}
```

> 修改HelloController

```java
@Controller
public class HelloController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    // 登录页面
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/home")
    public String home(){
        return "/home";
    }

    @PreAuthorize("hasRole('admin')") // 可以使用表达式，作用方式比较广
    @ResponseBody
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @Secured("ROLE_manager") // 写死权限或者角色
    @ResponseBody
    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }

    @PreAuthorize("hasAuthority('add')") // 可以使用表达式，作用方式比较广
    @ResponseBody
    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @PreAuthorize("hasAuthority('update')") // 可以使用表达式，作用方式比较广
    @ResponseBody
    @GetMapping("/update")
    public String update(){
        return "update";
    }
}
```

> 测试各种路径的使用，查看权限和角色是否对路径访问产生影响。

参考文档：

https://blog.csdn.net/qq_42640067/article/details/113062222

https://blog.csdn.net/qq_33517844/article/details/98482089