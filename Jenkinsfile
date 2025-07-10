pipeline {
  agent any

  tools {
    jdk 'JDK17'           // Nome configurado no Jenkins
    maven 'Maven 3.9.6'   // Nome configurado no Jenkins
  }

  parameters {
    string(name: 'TAGS', defaultValue: '@login', description: 'Tags Cucumber a executar')
  }

  environment {
    MAVEN_OPTS = "-Dcucumber.filter.tags=${params.TAGS}"
  }

  stages {
    stage('Checkout') {
      steps {
        git url: 'https://github.com/repoe2e/demo-webshop.git', branch: 'main'
      }
    }

    stage('Executar testes') {
      steps {
        sh 'mvn clean test -Dtest=runner.RunCucumberTest'
      }
    }

    stage('Publicar Relatório HTML') {
      steps {
        publishHTML(target: [
          reportDir: 'target',
          reportFiles: 'cucumber-report.html',
          reportName: 'Cucumber Report'
        ])
      }
    }
  }
}
