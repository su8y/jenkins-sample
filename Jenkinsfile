pipeline {
    agent any
    tools {
        gradle 'gradle-latest'
    }
    stages {
        stage('Git Clone') {
            steps {
                git branch: 'devlop', url: 'https://github.com/su8y/jenkins-sample'
            }
        }
        stage('BE Lint') {
            steps {
                dir('BE') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew checkstyleMain'
                }
            }
        }
        stage('BE Build') {
            steps {
                sh 'echo BE build'
                sh 'ls'
                dir('BE'){
                    sh 'ls -a'
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean build'
                }
            }
        }
        stage('Be Docker Image Build') {
            steps {
                dir('BE') {
                    sh 'docker build -t ex-web-server .'
                }
            }
        }
        stage('Be Docker Depoly') {
            steps {
                dir('BE') {
                    sh 'docker rm -f  $(docker ps | grep ex-web-server | awk "{print $1}")'
                    sh 'docker run -d -p 8080:8080 ex-web-server'
                }
            }
        }
    }
}