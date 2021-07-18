pipeline{
    agent any
    tools{
        maven "3.8.1"
    }
    stages{
        stage("maven version and test"){
              steps{
                  sh "mvn -version"
                  sh "mvn clean test"
              }
        }
        stage('Sonar Quality check'){
            steps{
                script{
                    withSonarQubeEnv('sonarserver'){
                        sh "mvn sonar:sonar"
                    
                        timeout(time: 1, unit: 'HOURS'){
                            def qg = waitForQualityGate()
                                if(qg.status != 'OK'){
                                    error "Quality check failure: ${qg.status}"
                                }
                        }
                        sh "mvn clean install"
                    }
                }
            }
        }
    }
}
