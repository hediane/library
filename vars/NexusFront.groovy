def call (Map config)
{ 
    
    pipeline
        {
        agent any
        stages
        {
            
            stage('Creating our image'){
                    steps{
                    sh "mkdir -p ${config.DestinationFolder} "
                    sh "cp -r ${config.source} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFolder}"
                    dir("${config.DestinationFolder}")
                            {
                            dockerImage = docker.build "${config.ImageBuild}" + ":${config.ImageVersion}" 
                            }
                        
                    }}
            stage('push image in nexus'){      
                steps{
                    docker.withRegistry( "${config.nexusRepository}", "${config.credentiel_id}" ) { 
                        dockerImage.push() 
                                } 
                             }
            }
        

               }
        
        
}}