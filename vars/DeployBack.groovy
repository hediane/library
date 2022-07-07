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
                                sh "ssh ${config.DevServer} 'docker pull ${config.ImagePull}'"
                                sh "ssh ${config.DevServer} 'docker pull ${config.ImageNginxPull}'"
                                sh "ssh ${config.DevServer} 'mkdir -p ${config.DestinationFolder}'"
                                sh "scp ${config.dockerComposeDestination} ${config.DevServer}:${config.dockerComposeDestination}"
                                sh "ssh  ${config.DevServer} docker-compose -f ${config.dockerComposeDestination} up -d"        
                                input message: "YOU CAN CHECK LOGS USING THIS LINKS ${config.url_Elasticsearch_Kibana} "
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