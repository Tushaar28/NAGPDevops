pipeline{
    agent any
    tools{
        maven 'Maven3'
    }
    stages{
        stage('Build'){
            steps{
                bat 'mvn clean test'
            }
        }
        stage('Sonar Quality check'){
            steps{
                script{
                    withSonarQubeEnv(credentialsId: 'sonarserver'){
                        bat "mvn sonar:sonar"
                    
                        timeout(time: 1, unit: 'HOURS'){
                            def qg = waitForQualityGate()
                                if(qg.status != 'OK'){
                                    error "Quality check failure: ${qg.status}"
                                }
                        }
                        bat "mvn clean install"
                    }
                }
            }
        }
    }
}
