# 编译运行

```shell
gradle build
gradle run
```

# Dockerize

### 打包

```shell
gradle build && docker build -t log_percentiles .
```

### 运行

```
docker run --rm -v /var/log/httpd:/var/log/httpd log_percentiles
```

# 算法分析

读取和解析日志的时间复杂度O(n)，空间复杂度O(n)
```kotlin
    for(file in ftw.iterator()) {
        if(file.isDirectory || !file.name.endsWith(".log")) {
            continue
        }
        val newRespTimes = file.readLines().asSequence()
            .map { regex.matchEntire(it)?.destructured?.component5()?.toInt() }
            .filterNotNull()
            .toList()
        respTimes.addAll(newRespTimes)
    }
```

kotlin中的默认排序算法是快速排序，时间复杂度O(n*logn)，空间复杂度O(n*logn)
```kotlin
    respTimes.sort()
```

另外，当日志数据量过大时，会导致程序运行时间过久，应使用随机采样或者TDigest 算法等方式来进行优化。

# 其他问题


这个项目使用Gradle来打包，需要先安装Gradle。也可以使用docker的Gradle镜像来编译。



如果遇到Maven包下载得很慢的情况，请修改Maven镜像源：

```kotlin
// 配置本地 Gradle 全局使用 Maven 镜像源
// 请将本文件置于： ~/.gradle/init.d/mirrors.init.gradle.kts

settingsEvaluated {


    allprojects {
        repositories {
            maven("https://maven.aliyun.com/repository/public/")
            maven("https://maven.aliyun.com/repository/spring")
            maven("https://maven.aliyun.com/repository/google")
            // 关于 mavenLocal()
            // 如果你在别的地方看见过 mavenLocal() 作为“缓存”仓库项，这里要明确纠正
            // Gradle 不推荐启用本地 Maven 源，更不推荐将其置于第一位
            // 1. 本地 Maven 本质上是 Maven 自己专用的 Cache，Gradle 无法对其溯源（无法得知是否完整、是否被篡改）
            // 2. 因此 Gradle 不会对本地 Maven 进行缓存操作，这将会降低构建速度
            // 3. 只有在真正需要本地制品仓库（即：发布到本地）的情况下，才启用 mavenLocal()
            // 信息来源： https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:case-for-maven-local
        }
    }


    pluginManagement {
        repositories {
            maven("https://maven.aliyun.com/repository/gradle-plugin")
            maven("https://maven.aliyun.com/repository/spring-plugin")
        }
    }

}
```


