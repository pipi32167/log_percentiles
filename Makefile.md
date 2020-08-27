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

```kotlin
    // 读取和解析日志的时间复杂度O(n)，空间复杂度O(n)
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

    // 默认排序是快速排序，时间复杂度O(n*log2n)，空间复杂度O(log2n)~O(n)之间
    respTimes.sort()
```


