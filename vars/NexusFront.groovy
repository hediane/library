def call (Map config)
{ 
    
    node
        {
            
            stage('Creating our image'){      
                    
                    sh "mkdir -p ${config.DestinationFolder} "
                    sh "cp -r ${config.source} ${config.DestinationFolder}"
                    //sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
                    sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFolder}"
                    dir("${config.DestinationProject}")
                            {
                            dockerImage = docker.build "image-front/aoso" + ":${config.ImageVersion}" 
                            }
                        
                    }
            stage('push image in nexus'){      
                                
                    docker.withRegistry( "${config.nginxLocation}", "${config.credentiel_id}" ) { 
                        dockerImage.push() 
                                } 
                             }
             
        

               }
        
        
}