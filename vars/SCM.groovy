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
            script { 
                def version = "latest"
                sh 'docker build -f "${config.Dockerfile}" -t "nexus_docker/aoso" + ":${version}" '
                //dockerImage = docker.build "nexus_docker/aoso" + ":$version" 
        }
        
    }
             
        }
}