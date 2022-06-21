def call (Map config)
{
    node
        { 
            stage('DEPLOY APP ') 
                {  
                    wrap([$class: 'BuildUser']) {
                    def user = env.BUILD_USER_ID
                    //echo "${user}"
                    //def list = ['AmaniGHADDAB']
                    //echo "${config.devValidator}"
                
                    if ("${config.devValidator}".contains("${user}"))
                    {
                       sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} up -d"
                    }
                    else
                    {    
                        //slackSend color: 'danger', channel: '#devops', message: "<${currentBuild.absoluteUrl}|Server build ${env.BUILD_NUMBER}> failed to deploy build "
	                       
                            //input message: 'Do you want to approve the deploy in production?', ok: 'Yes'
                            isApproved = input(id: 'someId',message: 'Approve?', submitter: "${config.devValidator}",
                            parameters: [choice( 
                                        choices: ['No', 'Yes'],
                                        description: 'some description',
                                        name: 'some name')]
                                ) == 'Yes'
                                            
                    }
                                }
                                }

        }
           
}