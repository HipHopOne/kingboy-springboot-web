### 一、环境准备
在项目中添加以下依赖

gradle
```
org.hibernate:hibernate-validator:5.3.5.Final
```
maven
```
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>5.3.5.Final</version>
</dependency>
```

> 如果是SpringBoot项目，只需要引入web的starter即可，里面包含了所需依赖

### 二、常用的校验注解及示例

```
    //该参数必须为空
    @Null(message = "无需ID")
    private Integer id;

    //根据正则校验手机号是否是由数字组成
    @Pattern(regexp = "^\\d{11}$", message = "手机格式不正确,不是11位")
    private String telephone;

    //校验该对象是否为null
    //对于String来说，空字符串可通过校验，所以String应该使用@NotBlank进行校验，此处仅做示例而已。
    @NotNull(message = "联系人不能为空")
    private String friendName;

    //校验对象是否是空对象，可用于Array,Collection,Map,String
    @NotEmpty(message = "家庭成员不能为空")
    private List families;

    //校验长度，可以用于Array,Collection,Map,String
    @Size(min = 4, max = 8, message = "用户名长度错误 by size")
    //校验长度，只能用于String
    @Length(min = 4, max = 8, message = "用户名长度错误 by length")
    private String username;

    //javax校验
    @Max(value = 200, message = "年龄一般不会超过200 by max")
    @Min(value = 1, message = "年龄一般不能小于1 by min")
    //hibernate校验，效果等同
    @Range(min = 0, max = 200, message = "年龄范围在0-200之间 by range")
    private Integer age;

    //校验参数是否是False, 相反的是@AssertTrue
    @AssertFalse(message = "用户初始化无需冻结")
    private Boolean lock;

    //String专用
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 12, message = "密码长度不对")
    private String password;

    //使用自定义校验注解->校验时间
    @Past(message = "生日只能为以前的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date birth;

    //校验Email
    @Email(message = "邮件地址不正确")
    private String email;
```

### 三、校验类方法中的普通参数

1. 在类上加`@Validated`注解
2. 在参数上加上校验注解

以controller层作示例如下：
```
@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 校验请求参数
     */
    @GetMapping
    public String getUser(@Size(min = 5, max = 8, message = "用户名长度超出限制") String username) {
	 return username;       
    }
}
```

### 四、校验类方法中的自定义对象

1. 在类上加`@Validated`注解(同普通参数一样都需要加)
2. 在参数上加`@Valid`，(或者加@Validated也是可以的)
```
@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 校验请求中的自定义对象
     */
    @PostMapping
    public UserDTO saveUserOuter(@RequestBody @Valid UserDTO userDTO) {
        return userDTO;
    }

}
```

3.在自定义对象中的属性上加上校验注解
```
public class UserDTO {

    //该参数必须为空
    @Null(message = "无需ID")
    private Integer id;

    //根据正则校验手机号是否是由数字组成
    @Pattern(regexp = "^\\d{11}$", message = "手机格式不正确,不是11位")
    private String telephone;

    //校验该对象是否为null
    //对于String来说，空字符串可通过校验，所以String应该使用@NotBlank进行校验，此处仅做示例而已。
    @NotNull(message = "联系人不能为空")
    private String friendName;

    //校验对象是否是空对象，可用于Array,Collection,Map,String
    @NotEmpty(message = "家庭成员不能为空")
    private List families;

    //校验长度，可以用于Array,Collection,Map,String
    @Size(min = 4, max = 8, message = "用户名长度错误 by size")
    //校验长度，只能用于String
    @Length(min = 4, max = 8, message = "用户名长度错误 by length")
    private String username;

    //javax校验
    @Max(value = 200, message = "年龄一般不会超过200 by max")
    @Min(value = 1, message = "年龄一般不能小于1 by min")
    //hibernate校验，效果等同
    @Range(min = 0, max = 200, message = "年龄范围在0-200之间 by range")
    private Integer age;

    //校验参数是否是False, 相反的是@AssertTrue
    @AssertFalse(message = "用户初始化无需冻结")
    private Boolean lock;

    //String专用
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 12, message = "密码长度不对")
    private String password;

    //使用自定义校验注解->校验时间
    @Past(message = "生日只能为以前的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date birth;

    //校验Email
    @Email(message = "邮件地址不正确")
    private String email;

	//setter  getter ....
}
```

### 五、关于@Valid和@Validated的区别联系

一直对@Valid和@Validated这两个注解非常疑惑，不知道怎么区分和使用。

1.包位置
- @Valid: javax.validation， 是javax，也是就是jsr303中定义的规范注解
- @Validated: org.springframework.validation.annotation， 是spring自己封装的注解。

2.功能
@Valid就不用说了，是jsr303的规范。我们打开@Validated的源码，可以看到以下注释，
```
Variant of JSR-303's {@link javax.validation.Valid}, supporting the
specification of validation groups. Designed for convenient use with
Spring's JSR-303 support but not JSR-303 specific.
```
大致意思就是说@Validated是@Valid的一个变种，扩展了@Valid的功能，支持group分组校验的写法。
那么我们对于@Valid和@Validated就可以这么理解：
1. 能用@Valid的地方通常可以用@Validated替代。
2. 需要使用分组校验的时候使用@Validated注解。

### 六、分组校验

我们有一个用户DTO,其中有id,username两个属性。当保存时，id不需要有值，由数据库自动生成，我们使用@Null注解校验。当更新时，id需要有值，根据ID去更新用户名，我们使用@NotNull注解校验。无论是保存用户还是更新用户，都需要校验用户名，我们使用@NotBlank注解校验。

UserGroupValidDTO
```java
public class UserGroupValidDTO {

    public interface SaveGroup extends Default {}

    public interface UpdateGroup extends Default {}

    @Null(groups = {SaveGroup.class}, message = "不需要传入用户ID")
    @NotNull(groups = {UpdateGroup.class}, message = "用户ID不能为空")
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;
    
    //Setter Getter ...
}
```
定义相应类型的公开接口(SaveGroup,UpdateGroup)，给每个校验注解指定groups属性，如果不指定则默认为javax.validation.groups.Default.class。

我们让SaveGroup和UpdateGroup继承了Default, 那么三个接口之间的关系类似如下：
```
save --> SaveGroup |-->id  @Null
                   |-->Default --> username @NotBlank
                   
update--> UpdateGroup |-->id @NotNull
                      |-->Default --> username @NotBlank
```

UserController
```java
@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 分组校验：保存用户，不能传ID
     */
    @PostMapping("/save")
    public void validSaveUser(@RequestBody @Validated(value = UserGroupValidDTO.SaveGroup.class) UserGroupValidDTO userDTO) {
        //save user
    }

    /**
     * 分组校验：更新用户信息，需要传ID
     */
    @PostMapping("/update")
    public void validUpdateUser(@RequestBody @Validated(value = UserGroupValidDTO.UpdateGroup.class) UserGroupValidDTO userDTO) {
        //update user
    }

}
```
在方法参数的对象上加@Validated属性，填写Value为对应的接口的class即可，这时候访问不同的接口就会进行不同的校验了。


### 七、自定义校验注解

有时候默认提供的校验注解无法满足我们的需要，我们需要自定义。例如现有校验注解不支持java8中的LocalDateTime。那么接下来我们自定义一个校验LocalDateTime的注解。该注解判断传入的时间是否是一个过去的时间。

1.创建校验注解
```
/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/12/29 下午7:24
 * @desc ${DESCRIPTION}.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastTimeValidate.class)
public @interface PastDate {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
```
2.编写校验规则
校验规则也就是枚举PastDate中指定的validateBy属性

```
/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/12/29 下午7:28
 * @desc .
 */
@CommonsLog
public class PastTimeValidate implements ConstraintValidator<PastDate, LocalDateTime> {

   @Override
   public void initialize(PastDate constraintAnnotation) {
      log.info("init enum PastDate");
   }

   @Override
   public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext context) {
      return localDateTime.isBefore(LocalDateTime.now()) ? true : false;
   }
}
```

写完这些，我们就可以像@Past对Date校验一样使用@PastDate对LocalDateTime进行校验了。



可以参考我的[github项目](https://github.com/KingBoyWorld/kingboy-springboot-web/tree/feature_validate)查看完整示例，示例中额外包含了校验异常的捕捉方式。