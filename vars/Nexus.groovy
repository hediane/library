def call (Map config)
{  
    node
        {
                stage('CREATING OUR IMAGE'){      
                    
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