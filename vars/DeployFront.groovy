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
                                sh "ssh root@87.106.205.95 'docker pull 149.102.138.184:8082/image-front/aoso'"
                                sh "ssh root@87.106.205.95 'mkdir -p /var/Aosora/DevOps/Frontend'"
                                sh "scp /srv/aoso/DevOps/frontend/docker-compose-front.yml root@87.106.205.95:/var/Aosora/DevOps/Frontend"
                                sh "ssh root@87.106.205.95 docker-compose -f /var/Aosora/DevOps/Frontend/docker-compose-front.yml up -d"        

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