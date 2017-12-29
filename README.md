## SpringMVC使用JsonView针对统一实体返回不同信息

### 一、随便说说

项目中不同的接口需要返回不同的信息，而信息一般通过不同的对象实例去承载。例如有接口A和接口B，A需要返回不包含用户地址的用户信息，
而B需要返回包含用户地址的用户信息，其它信息和A的返回信息一致。

这个时候应该建立两个不同的用户类，一个包含address,一个不包含，这样做显然是麻烦的，那么JsonView就可以帮你很优雅的解决这个问题。

接下来就直接开始撸代码了。


### 二、User信息载体类

```java
public class UserDTO {

    //不显示地址
    public interface CommonView {}

    //显示地址
    public interface AdminView extends commonView {}

    @JsonView(value = CommonView.class)
    private String username;

    @JsonView(value = AdminView.class )
    private String address;
    
    //Getter Setter ...
}
```
1.接口是public的
2.在类中写两个内部接口，分别标志不同的返回属性。接口是可以相互继承的，例如AdminView继承了CommonView，那么相当于AdminView包含了
commonView标记的属性。
```

    CommonView --> username
    
    AdminView |--> password
              |--> CommonView --> username
```

### 三、UserController的写法

在需要返回不同视图的方法上加上@JsonView注解，注解中的value属性填写我们在UserDTO中定义的代表不同标记的接口类即可。

```java
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 返回普通视图，不带地址
     * @return
     */
    @GetMapping(value = "/common")
    @JsonView(value = UserDTO.CommonView.class)
    public UserDTO getCommonView() {
        return new UserDTO("kingboy", "北京");
    }

    /**
     * 返回管理员视图，显示地址
     * @return
     */
    @GetMapping(value = "/admin")
    @JsonView(value = UserDTO.AdminView.class)
    public UserDTO getAdminView() {
        return new UserDTO("kingboy", "北京");
    }

}
```

访问localhost:8080/user/common返回
```json
{
"username": "kingboy"
}
```

访问lcoalhost:8080/user/admin返回
```json
{
"username": "kingboy",
"address": "北京"
}
```