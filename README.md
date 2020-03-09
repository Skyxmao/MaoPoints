# MaoPoints

## 插件介绍

Nukkit上的点券插件，简单易用的API。

需要前置 DbLib

## 指令介绍

/points => 打开GUI界面

/points me => 查询剩余点券

/points pay <id> <amount> => 向玩家转账

/points give <id> <amount> => 给玩家点券

/points take <id> <amount> => 拿走玩家点券

/points set <id> <amount> => 设置玩家点券

/points look <id> => 查看玩家点券

## API介绍

```java
PlayerPoint mp1 = new PlayerPoint(sender.getName());
mp1.has(1000); #该玩家是否有1000点券
mp1.set(1000); #设置玩家点券为1000点券
mp1.give(1000); #给玩家1000点券
mp1.take(1000); #拿走玩家1000点券
```

