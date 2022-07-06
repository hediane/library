def call (Map config)
{  
    node
        {
                stage('CREATING OUR IMAGE'){      
                         sh "ls -la ${pwd()}"
                 sh "ls -la ${pwd()}/source"
                 sh "mkdir -p /srv/aoso/DevOps/backend "
                 sh "ls -a /srv/aoso/DevOps/backend/"
                 sh "cp -r ${config.DestinationFolder} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
                 sh "ls -la ${config.DestinationProject} "
                 sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFile}"
                 sh "ls -la ${config.DestinationNginx} "
                 sh "ls -la ${config.DestinationFile} "

                        dir("${config.Dockerfile}")
                            {
                            sh "ls -a"
                            dockerImage = docker.build "image-back/aoso" + ":${config.ImageVersion}" 
                            }
                        dir("${config.DestinationNginx}")
                            {
                            dockerImagenginx = docker.build "image-nginx-backend/aoso" + ":${config.ImageVersion}" 
                            }
                    }
                        stage('PUSH IMAGE IN NEXUS'){      
                                docker.withRegistry( 'http://149.102.138.184:8082/repository/Aosora', 'NexusSecret' ) { 
                                    dockerImage.push() 
                                    dockerImagenginx.push() 
                                }
                    
                             }
             
        }
          
        
        
}