pipeline {
    agent any

    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/vyshnavi1411/test-automationlive1.git'
            }
        }

        stage('Check Docker') {
            steps {
                sh 'which docker'
                sh 'docker --version'
            }
        }

        stage('Run Container') {
            steps {
                sh 'docker run --rm test-automativelive1'
            }
        }
        }
    }
}