# Springboot demo
## Start Application
```
$ mvn spring-boot:run
```

## Docker commands
```
$ docker build -t dockernvkk/springbootapp .
$ docker run --rm -p 8080:8080 dockernvkk/springbootapp:latest
$ docker push dockernvkk/springbootapp:latest
```

## Monitoring
- [Actuator Endpoints](http://localhost:8180/actuator)
- [Prometheus end-point URL](http://localhost:8180/actuator/prometheus)
```
# TYPE venkata_hello_seconds histogram
venkata_hello_seconds{exception="None",method="GET",outcome="SUCCESS",status="200",uri="/hello",version="1.0",quantile="0.95",} 0.025100288

# TYPE http_server_requests_seconds summary
http_server_requests_seconds_count{exception="None",method="GET",outcome="SUCCESS",status="200",uri="/actuator/prometheus",} 1.0
```
- Integrating with grafana/prometheus, Add below scrape configuration to prom operator / yaml.
```
ScrapeConfigs: 
    - job_name: 'springbootdemo-actuator'
      metrics_path: '/actuator/prometheus'
      scrape_interval: 5s
      static_configs:
      - targets: ['10.110.109.119:8180']  # replace 10.110.109.119 with cluster-ip / loadbalancer
```

### References
- [micrometer-spring-boot-2](https://spring.io/blog/2018/03/16/micrometer-spring-boot-2-s-new-application-metrics-collector)