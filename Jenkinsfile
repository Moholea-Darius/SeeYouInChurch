pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                withMaven(maven: '3.8.5') {
                    sh "mvn clean verify -DskipTests"
                }
            }
        }
        stage('Test') {
            steps {
                withMaven(maven: '3.8.5') {
                    sh "mvn test"
                }
            }
        }
        stage('Sonar') {
            steps {
                echo '[ITea Home] Please define "Sonar" stage steps...'
            }
        }
        stage('Snapshot') {
            when {
                branch 'PR**'
            }
            steps {
                echo '[ITea Home] Please define "Snapshot" stage steps...'
            }
        }
        stage('Release') {
            when {
                branch 'main'
            }
            steps {
                echo '[ITea Home] Please define "Release" stage steps...'
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo '[ITea Home] Please define "Deploy" stage steps...'
            }
        }
        stage('Clean') {
            steps {
                echo '[ITea Home] Please use "Workspace Cleanup Plugin" in "Clean" stage steps...'
            }
        }
    }

    post {
        always {
            echo '[ITea Home] This is a "post" message for "always" scenario'
        }
        success {
            echo '[ITea Home] This is a "post" message for "success" scenario'
//             As of May 30 2022, Google no longer supports authentication using only email and password
//             if (env.BRANCH_NAME == 'main') {
//                 emailext
//                     subject: 'Jenkins pipeline success',
//                     body: 'Jenkins pipeline completed successfully on main branch'
//                     recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']]
//             }
        }
        failure {
            echo '[ITea Home] This is a "post" message for "failure" scenario'
//             As of May 30 2022, Google no longer supports authentication using only email and password
//             if (env.BRANCH_NAME == 'main') {
//                 emailext
//                     subject: 'Jenkins pipeline failure',
//                     body: 'Jenkins pipeline failed on main branch'
//                     recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']]
//             }
        }
    }
}