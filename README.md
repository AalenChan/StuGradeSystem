# StuGradeSystem
------------


#### 更新

    每小时(整点)更新一次位置信息给服务器；
    间隔可调整参数去调整；


#### 分数 :需要把IP地址改成部署服务端的电脑的IP地址
```
ScoreMainActivity line 101 :
URL oracle = new URL("http://192.168.1.104:8080/StuSystem/DataServlet?account=" + params[0]);
```

```
MainActivity line 336 :
URL url = new URL("http://192.168.1.104:8080/StuSystem/HandleLocationServlet?location=" + loc + "&type=" + type);
```


### 课程表
  课程表添加后 有长按功能操作
  课程表内容也是存放在数据库内 使用OrmLite作为数据库存储框架
  
### 天气
  仿360天气UI，图片都是从360里抠出来的
  使用百度天气API

### 备忘录
  简单实现备忘录，内容存储在数据库内

### 评分规则
  由于包含一些复杂数学公式的显示，直接使用pdf显示
