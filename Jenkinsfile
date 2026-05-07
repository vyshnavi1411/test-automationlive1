pipeline {
    agent any

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/vyshnavi1411/test-automationlive1.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t test-automativelive1 .'
            }
        }

        stage('Run Container') {
            steps {
                sh 'docker run --rm test-automativelive1'
            }
        }

        stage('Push Image') {
            steps {
                sh 'docker tag testautomationlive1 vyshnavi1411/test-automativelive1:v1'
                sh 'docker push vyshnavi1411/test-automativelive1:v1'
            }
        }
    }
}