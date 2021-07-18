pipeline{
    agent any
    environment{
        registry = 'tushaar28/nagp_devops'
        username = 'tushaar28'
        registryCredential = 'dockerhub'
    }
    tools{
        maven 'Maven3'
    }
    stages{
        stage('Clean and test'){
            steps{
                bat 'mvn clean test'
            }
        }
        stage('Sonar Quality check'){
            steps{
                withSonarQubeEnv('sonarserver'){
                    bat "mvn sonar:sonar"
                }
            }
        }
        stage('Quality gate'){
            steps{
                script{
                    timeout(time: 1, unit: 'HOURS'){
                            def qg = waitForQualityGate()
                                if(qg.status != 'OK'){
                                    error "Quality check failure: ${qg.status}"
                                }
                    }
                }
            }
        }
        stage('install'){
            steps{
                bat 'mvn clean install'
            }
        }
        stage('Docker image'){
            steps{
                bat 'docker build . -t tushaar28/nagp_devops:latest'
            }
        }
        stage('Login to docker and push'){
            steps{
                script{
                    docker.withRegistry('', registryCredential){
                        bat 'docker push ${registry}:devops'
                    }
                }
            }
        }
        stage('Docker deployment'){
            steps{
                bat 'docker run --name NAGPDevops -d -p 7100:80 ${registry}:devops'
            }
        }
    }
}
