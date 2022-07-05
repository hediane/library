def call (Map config)
{ 
    
    node
        {
            
            stage('Creating our image'){      
                    sh "ls -la ${pwd()}"
                    sh "ls -la ${pwd()}/source"
                    sh "mkdir -p /srv/aoso/DevOps/frontend "
                    sh "cp -r ${config.DestinationFolder} ${config.DestinationFile}"
                    sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
                    sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFile}"
                    sh "ls -a ${config.DestinationFile}"
                    sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFile}"
                    sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFile}"
                    sh "ls -la ${config.DestinationNginx} "
                    sh "ls -la ${config.DestinationFile} "

                    dir("${config.Dockerfile}")
                            {
                            dockerImage = docker.build "image-front/aoso" + ":latest" 
                            }
                        
                    }
            stage('push image in nexus'){      
                                
                    docker.withRegistry( 'http://149.102.138.184:8082/repository/Aosora', 'NexusSecret' ) { 
                        dockerImage.push() 
                                } 
                             }
             
        

               }
        
        
}