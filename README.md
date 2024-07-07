# Jenkins Start

## Start Jenkins with Docker

**Jenkins Volume & Network 생성**

```bash 
# volume
sudo docker volume create ex-jenkins-volume
# network
sudo docker network create ex-jenkins 
```

**Jenkins Container 실행**

```bash
$ sudo docker run -d \
-p 8080:8080 \
-p 50000:50000 \
-v ex-jenkins-volume:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock:ro \
-v /var/lib/docker/containers:/var/lib/docker/containers:ro \
--name ex-jenkins \
--network ex-jenkins \
jenkins/jenkins:lts
```

**initialPassword 확인**

```bash
# Option 
# 1 직접 initialPassword 확인
cat var/jenkins_home/secrets/initialAdminPassword
# 2. 첫 생성 로그 확인
docker logs ex-jenkins
```
## Jenkins 설정
### Gradle 
Gradle configruration 에서 다운로드 해준다. 

### GithubWeb Token 
깃헙 토큰을 연결 해주어야 한다.  
그런데 screcttext로하지 않으면 key가 정상적으로 동작하지 않는다. 

## BE

### Hello API 
**Reqeust**
```bash
curl -X GET "http://{yourUrl}/hello/test"
```

```json 
METHOD: 'GET'
URL: '/hello/{username}'
CONTENT-TYPE: 'application/json'
RESPONSE: "안녕하십니까? {username}"
```

```
curl -d '{"key1":"value1", "key2":"value2"}' \
-H "Content-Type: application/json" \
-X POST http://localhost:8000/data
```

