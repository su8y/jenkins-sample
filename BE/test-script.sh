# ex-web-server 라는 이름의 image 구축
docker build -t ex-web-server .

# 현재 돌고있는 도커 Container 삭제
docker rm -f  $(docker ps | grep ex-web-server | awk '{print $1}')

# 도커 실행
docker run -d  -p 8080:8080 ex-web-server