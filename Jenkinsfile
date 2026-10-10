pipeline {
  agent any
  tools { jdk 'jdk17'; maven 'maven3' }
  stages {
    stage('Test') { steps { sh 'mvn -B test' } }
    stage('Package') { steps { sh 'mvn -B -DskipTests package' } }
    stage('Build images') { steps { sh 'docker compose build' } }
  }
  post { always { junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml' } }
}
