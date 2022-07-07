def call (Map config)
{ 
    
    node
        {
            
            stage('Creating our image'){
                    
                    sh "mkdir -p ${config.DestinationFolder} "
                    sh "cp -r ${config.source} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFolder}"
                    dir("${config.DestinationProject}")
                            {
                            dockerImage = docker.build "${config.ImageBuild}" + ":${config.ImageVersion}" 
                            }
                        
                    }
            stage('push image in nexus'){      
                                
                    docker.withRegistry( "${config.nexusRepository}", "${config.credentiel_id}" ) { 
                        dockerImage.push() 
                                } 
                             }
             
        

               }
        
        
}