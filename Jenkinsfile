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
        stage('Docker image'){
            steps{
                script{
                    dockerImage = docker.build registry + "i-tushaartiwari-master:$BUILD_NUMBER"
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
                bat 'docker run --name c-tushaartiwari-master -d -p 8080:7300 tushaar28/nagp_devopsi-tushaartiwari-master:latest'
            }
        }
    }
}
