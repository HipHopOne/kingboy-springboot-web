### 一、简介

> 首先restfule只是一种风格，并不是具体的某项技术或框架。就好比我们的坐姿，没使用rest之前，我们会翘着二郎腿，歪着头，而使用rest之后，就要求我们抬头，挺背，端正的坐着。不用rest我们就不可以坐着嘛？当然不是的，但是我们的精神面貌是不同的。

> 从代码角度来说，不用restful也可以进行正常开发，但是写出来的url的质量就参差不齐了。

### 二、传统url的理解

在以前的url中，我们主要使用get和post方法进行对服务器资源的请求。在数据量不是很大的时候使用get拼接参数请求，有一定数据量或者需要保证数据安全的时候使用post。

在这一点上，以前的方式中，get和post是没有本质区别的，都是用来发送请求，只是请求数据的封装不一样，get是在url上，而post是在请求的body中。这样导致没有统一的规范，每个人写出来的风格各有不同，导致项目接口很乱。如下的接口格式不言而喻。

```
/user/get/1
/findUser/1
/DeleteUser
```

### 三、资源和行为
使用传统的交互方式上，我们没有使用到资源和行为的相关概念。

(规范的)请求应该包含资源和行为的，那么什么是资源和行为呢？

> 资源：无论我们请求什么接口，返回的都是资源，可以理解为返回的html，json,xml等都是资源，那么我们的url就是对该资源的定位。

> 行为：行为可以理解为我们要对这个资源做什么，我们通过url已经可以指向这个资源了，那么我们怎么表述对这个资源的操作呢？例如怎么删除、获取这个资源呢?其实这就是行为。在http的方法请求中，我们将post/delete/put/get/方法对应为对资源的增删改查。

例如：

- 使用put方法请求/user/1,就是将id为1的用户进行修改
- 使用get方法请求/user/1,就是获取id为1的用户的详情
- 使用post方法请求/user,就是要添加一个user
- 使用delete方法请求/user/1,就是将id为1的用户删除


### 四、rest风格

先看下我们没有使用restful之前的url的风格

|操作|url|请求方法|方法名|说明|
|--|--|--|--|--|
|C| /save/user   |post   | saveUser()/insertUser()|新增用户|
|R| /get/user/list  |get    | getUserList()    | 查询用户(分页，条件) |
|R| /user/detail?id=1 |get    | getUserDetail()|  获取单个用户详情|
|U| /update/user?id=1 |post    | updateUser()| 更新用户 |
|D| /delete/user/?id=1 |post | removeUser()/deleteUser| 删除用户 |

那么如果我们使用restful的风格该是什么样子的？如下

|操作|url|请求方法|方法名|说明|
|--|--|--|--|--|
|C| /user   |post   | saveUser()/insertUser()|新增用户|
|R| /user   |get    | listUser()    | 查询用户(分页，条件) |
|R| /user/1 |get    | getUser()|  获取单个用户详情|
|U| /user/1 |put    | updateUser()| 更新用户 |
|D| /user/1 |delete | removeUser()/deleteUser| 删除用户 |

### 我们该怎么定义url

关于这一点，个人觉得要理解好资源的含义才能更好的定义url。例如user就是一个资源，那么对应的post、put、delete、get方法进行增删改查操作，传的参数简单且非安全那么就可以使用pathVariable。

风格规定是死的，项目中才能找到适合自己的一套最佳实践。


个人项目实例[kingboy-springboot-rest](https://github.com/KingBoyWorld/kingboy-springboot-rest.git),其中包含了对参数校验，JsonView的视图定制等功能的实现，欢迎查看并指正。