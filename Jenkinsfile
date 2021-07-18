pipeline{
    agent any
    tools{
        maven 'Maven3'
    }
    stages{
        stage('Clean'){
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
        stage('Installing'){
            steps{
                bat 'mvn clean install'
            }
        }
    }
}
