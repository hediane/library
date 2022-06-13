def call (Map config)
{
    node
        { 
           stage('Docker BUILD') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} build"
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} up -d"
                }
        }
}