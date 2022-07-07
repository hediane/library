def call (Map config)
{
    node
        {  
        
            stage('DEPLOY DEV SERVEUR ') 
                {  
                   
                    wrap([$class: 'BuildUser']) {
                    def user = env.BUILD_USER_ID
                        
                     if ("${config.devValidator}".contains("${user}"))
                    {     
                        if(input (id: 'someId',message: 'Do you want to approve the deploy in production?',
                            parameters: [choice( choices: ['No', 'Yes'], description: 'DO YOU CONFIRME', name: 'some name')]) == 'Yes')
                            {   

                               //sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} up -d"
                                sh "ssh ${DevServer} 'docker pull ${ImagePull}'"
                                sh "ssh ${DevServer} 'docker pull ${ImageNginxPull}'"
                                sh "ssh ${DevServer} 'mkdir -p ${DestinationFolder}'"
                                sh "scp ${dockerComposeDestination} ${DevServer}:${dockerComposeDestination}"
                                sh "ssh  ${DevServer} docker-compose -f ${dockerComposeDestination} up -d"        
                                input message: "YOU CAN CHECK LOGS USING THIS LINKS ${dockerComposeDestination} "
                            }
                        else 
                        {
                            input message: "ABORD"
                        }
                    
                    }
                    else 
                     {
                        input message: "SORRY YOU DON'T HAVE ACCESS"
                     }
                }
            }

            
        
   }
   
}