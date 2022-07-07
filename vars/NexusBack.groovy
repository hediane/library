def call (Map config)
{  
    node
        {
                stage('CREATING OUR IMAGE'){      
                    
                        dir("${config.DestinationProject}")
                            {
                            dockerImage = docker.build "${config.ImageBuild}" + ":${config.ImageVersion}" 
                            }
                        dir("${config.DestinationNginx}")
                            {
                            dockerImagenginx = docker.build "${config.ImagenginxBuild}"+ ":${config.ImageVersion}" 
                            }
                    }
                        stage('PUSH IMAGE IN NEXUS'){      
                                docker.withRegistry( "${config.nexusRepository}", "${config.credentiel_id}" ) { 
                                    dockerImage.push() 
                                    dockerImagenginx.push() 
                                }
                    
                             }
             
        }
          
        
        
}