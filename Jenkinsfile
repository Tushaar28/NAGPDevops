pipeline{
    agent any
    environment{
        registry = 'tushaar28/nagp_devops'
        username = 'tushaar28'
        registryCredential = 'dockerhub'
        dockerImage = ''
        dockerImageLatest = ''
    }
    tools{
        maven 'Maven3'
    }
    stages{
        stage('Build'){
            steps{
                bat 'mvn clean install'
            }
        }
        
        stage('Unit Testing'){
            steps{
                bat 'mvn clean test'
            }
        }
        stage('Docker image'){
            steps{
                script{
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                    dockerImageLatest = docker.build registry + ":latest"
                }
            }
        }
        stage('Login to docker and push'){
            steps{
                script{
                    docker.withRegistry('', registryCredential){
                        dockerImage.push()
                        dockerImageLatest.push()
                    }
                }
            }
        }
        stage('Docker deployment'){
            steps{
                bat 'docker run -d -p 8080:7200 tushaar28/nagp_devops:latest'
            }
        }
    }
}
