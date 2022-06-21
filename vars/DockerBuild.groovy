def call (Map config)
{
    node
        { 
            stage('DEPLOY APP ') 
                {  
                    wrap([$class: 'BuildUser']) {
                    def user = env.BUILD_USER_ID
                    echo "${user}"
                    //def list = ['AmaniGHADDAB']
                    echo "${config.devValidator}"
                    if ("${config.devValidator}".contains("${user}"))
                    {
                       sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} up -d"
                    }
                    else
                    {    
                        //slackSend color: 'danger', channel: '#devops', message: "<${currentBuild.absoluteUrl}|Server build ${env.BUILD_NUMBER}> failed to deploy build "
	                       timeout(time: 2, unit: “HOURS”) {
                                input message: 'Approve Deploy?', ok: 'Yes'
                                    }
                    }
                 }
                }

        }
           
}