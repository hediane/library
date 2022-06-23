def call (Map config)
{
    node
        {
            stage('Install') 
                {    
                   
                    sh "${config.dockerComposeLocation} -f ${config.elasticsearch} up -d"
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} up -d"
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeNexus} up -d"
                
                } 
        }
}