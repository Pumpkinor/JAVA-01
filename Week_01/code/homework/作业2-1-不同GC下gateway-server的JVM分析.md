### 作业2-1-不同GC下gateway-server的JVM分析
#### 测试环境：
       - windows10
       - jdk8
       - SuperBenchmarker
       - gateway-server-0.0.1-SNAPSHOT.jar
#### 压测命令：
       sb -u http://localhost:8088/api/hello -c 20 -N 60 （20个线程 压测60s）
       -u 可用来指定要压测的网站或是API
       -c 可用来指定Concurrent Request数量     
       -m 可用来指定要使用的HTTP Method
       -h 可用来指定要显示HTTP Header
       -q 可用来指定要显示Cookie
       -n 可用来指定要压的Request数量
       -N 压测时间


#### 串行GC
> java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseSerialGC -jar gateway-server-0.0.1-SNAPSHOT.jar 
##### 压测结果
```
RPS: 4127.9 (requests/second)
Max: 515ms
Min: 0ms
Avg: 0.2ms
```
##### jstat结果分析
```
// 未进行压力测试之前
S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
34944.0 34944.0  0.0    0.0   279616.0  6498.8   699072.0   30636.2   41128.0 38283.4 5288.0 4786.7     26    0.153   3      0.273    0.426

// 进行压力测试到大概30s时候
S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
34944.0 34944.0  0.0   136.2  279616.0 203734.8  699072.0   30636.2   41256.0 38390.1 5416.0 4801.8     35    0.176   3      0.273    0.449

// 压测完成之后
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
34944.0 34944.0  0.0   177.1  279616.0 137402.2  699072.0   30728.1   41256.0 38400.5 5416.0 4801.8     49    0.214   3      0.273    0.487

```

#### 并行GC
> java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC -jar gateway-server-0.0.1-SNAPSHOT.jar 
##### 压测结果
```
RPS: 4072.7 (requests/second)
Max: 48ms
Min: 0ms
Avg: 0.2ms
```
##### jstat结果
```
// 未进行压力测试之前
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
43520.0 43520.0 15594.3  0.0   262144.0 179727.6  699392.0   14912.9   32432.0 30936.7 4272.0 4006.3      2    0.028   1      0.027    0.055

// 进行压力测试到大概30s时候
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
43520.0 43520.0  0.0   2720.6 262144.0 112617.5  699392.0   18702.0   38824.0 36509.8 5032.0 4610.7     13    0.075   2      0.076    0.150

// 压测完成之后
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
43520.0 43520.0 160.0   0.0   262144.0 118519.8  699392.0   21336.0   38824.0 36522.6 5032.0 4612.1     48    0.150   2      0.076    0.225
```


#### CMS
> java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar gateway-server-0.0.1-SNAPSHOT.jar 
##### 压测结果
```
RPS: 4072.7 (requests/second)
Max: 48ms
Min: 0ms
Avg: 0.2ms
```
##### jstat结果
```
// 未进行压力测试之前
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
43520.0 43520.0 15594.3  0.0   262144.0 179727.6  699392.0   14912.9   32432.0 30936.7 4272.0 4006.3      2    0.028   1      0.027    0.055

// 进行压力测试到大概30s时候
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
43520.0 43520.0  0.0   2720.6 262144.0 112617.5  699392.0   18702.0   38824.0 36509.8 5032.0 4610.7     13    0.075   2      0.076    0.150

// 压测完成之后
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
43520.0 43520.0 160.0   0.0   262144.0 118519.8  699392.0   21336.0   38824.0 36522.6 5032.0 4612.1     48    0.150   2      0.076    0.225
```

#### G1
> java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -jar gateway-server-0.0.1-SNAPSHOT.jar 
##### 压测结果
 ```
 RPS: 4072.7 (requests/second)
 Max: 48ms
 Min: 0ms
 Avg: 0.2ms
 ```
##### jstat结果
 ```
 // 未进行压力测试之前
  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 43520.0 43520.0 15594.3  0.0   262144.0 179727.6  699392.0   14912.9   32432.0 30936.7 4272.0 4006.3      2    0.028   1      0.027    0.055
 
 // 进行压力测试到大概30s时候
  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 43520.0 43520.0  0.0   2720.6 262144.0 112617.5  699392.0   18702.0   38824.0 36509.8 5032.0 4610.7     13    0.075   2      0.076    0.150
 
 // 压测完成之后
  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 43520.0 43520.0 160.0   0.0   262144.0 118519.8  699392.0   21336.0   38824.0 36522.6 5032.0 4612.1     48    0.150   2      0.076    0.225
 ```
##### jstack结果