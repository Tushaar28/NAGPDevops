pipeline{
    agent any
    environment{
        registry 'tushaar28/nagp_devops'
        username 'tushaar28'
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
        stage('package'){
            steps{
                bat 'mvn clean package'
            }
        }
        stage('Docker image'){
            steps{
                bat 'docker build -t i_${username}_master --no-cache -f Dockerfile .'
            }
        }
        stage('Move image to Docker hub'){
            steps{
                script{
                    withDockerRegistry([credentialsId: 'docker', url: '']){
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
