node {
   stage('Preparation') {
     git 'https://github.com/tfarrelly01/java-spring-building-api.git'
   }
   stage('Clean') {
     sh "mvn -Dmaven.test.failure.ignore clean"
   }
   stage('Package') {
     sh "mvn -Dmaven.test.failure.ignore package"
   }
   stage('Docker Build') {
     sh "docker build -t mybuildings ."
   }
}