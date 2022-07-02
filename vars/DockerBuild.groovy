def call (Map config)
{
    node
        {   stage("ssh_cnx") {
                    sh 'touch tsthediane' 
                    sh 'scp tsthediane root@87.106.205.95:/srv'
                    sh 'ssh root@87.106.205.95 '
                }
            stage('DEPLOY APP ') 
                {  
                   
                    wrap([$class: 'BuildUser']) {
                    def user = env.BUILD_USER_ID
                    //echo "${user}"
                    //def list = ['AmaniGHADDAB']
                    //echo "${config.devValidator}"
                
                   /* if ("${config.devValidator}".contains("${user}"))
                    {
                       sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} up -d"
                    }
                    else
                    {    
                        //slackSend color: 'danger', channel: '#devops', message: "<${currentBuild.absoluteUrl}|Server build ${env.BUILD_NUMBER}> failed to deploy build "
	                       
                            //input message: 'Do you want to approve the deploy in production?', ok: 'Yes'
                           if( isApproved = input(id: 'someId',message: 'Approve?', submitter: "${config.devValidator}.contains('${user}')",
                            parameters: [choice( 
                                        choices: ['No', 'Yes'],
                                        description: 'some description',
                                        name: 'some name')]
                                ) == 'Yes')
                                            
                                }*/
                        
                     if ("${config.devValidator}".contains("${user}"))
                    {     
                        if(input (id: 'someId',message: 'Do you want to approve the deploy in production?',
                            parameters: [choice( choices: ['No', 'Yes'], description: 'DO YOU CONFIRME', name: 'some name')]) == 'Yes')
                            {   docker pull <nexus-hostname>:<repository-port>/<image>

                                sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} up -d"
 
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