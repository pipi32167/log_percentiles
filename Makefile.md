



## 编译

```shell
gradle build
```

## 运行

```shell
gradle run
```

## Dockerize

### 打包

```shell
gradle build && docker build -t log_percentiles .
```

### 运行

```
docker run --rm -v /var/log/httpd:/var/log/httpd log_percentiles
```


