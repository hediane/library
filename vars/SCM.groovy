def call (Map config)
{ 
    node
        { 
            stage("GitSCM") {
            checkout([$class: 'GitSCM', 
            branches: [[name: 'refs/heads/main']], 
            userRemoteConfigs: [[
                //refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                url:"${config.scmurl}"]]
               
            ])
        }

        stage('Creating our image'){      
                //def version = "latest"
                //sh 'docker build -f "${config.Dockerfile}" -t nexus_docker/aoso '
                dir("${config.Dockerfile}"){
                dockerImage = docker.build "nexus-udd/aoso" + ":latest" 
        }
    }
        stage('push image in nexus'){      
                //def version = "latest"
                //sh 'docker build -f "${config.Dockerfile}" -t nexus_docker/aoso '
                docker.withRegistry( 'http://192.168.56.115:8082/repository/nexus-udd', 'nexus-udd' ) { 
                    dockerImage.push() 
                } 
    }
             
        }
}