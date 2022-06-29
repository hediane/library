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
                dockerImage = docker.build "nexus_docker/aoso" + ":latest" 
        
    }
             
        }
}