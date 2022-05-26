def call (Map config)
{
    node
        { 
            stage('check source scmurl')
            {
                echo "checking out the source scmurl "
                echo "${config.scmurl}"
            }
           stage("testCheckout") {
            checkout([$class: 'GitSCM', 
            branches: [[name: 'refs/heads/main']], 
            userRemoteConfigs: [[
                //refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                url:"${config.scmurl}"]]
               
        ])
         }
            /*stage('location of dockerfile') 
                {  
                    sh "docker build -t teeeeeest -f ${config.dockerfileLocation} ."
                    echo "checking out the source dockerfile "
                    //echo "${config.dockerfileLocation}",
                }*/
            stage ('copy all file from BACK')
            {    
                 sh "ls -la ${pwd()}"
                 sh "ls -la ${pwd()}/source"
                 sh "ls -la ${pwd()}/source/DevOpsProject/"
                 //sh "cd /var/jenkins_home/workspace/"
                 //sh "mkdir /var/jenkins_home/workspace/aoso/ | mkdir /var/jenkins_home/workspace/aoso/DevOps | mkdir /var/jenkins_home/workspace/aoso/DevOps/backend "
                 //sh "rm -rf /var/jenkins_home/workspace/aoso/DevOps/back"
                 sh "ls -a /var/jenkins_home/workspace/aoso/"
                 sh "ls -a /var/jenkins_home/workspace/aoso/DevOps"
                 sh "ls -a /var/jenkins_home/workspace/aoso/DevOps/backend"
                 //sh "rm -rf ${config.DestinationFile}"
                 //sh "cp -r ${pwd()}/source/DevOpsProject ${config.DestinationFile}"
                 sh "rm -rf ${config.DestinationFile}/Dockerfile"
                 sh "cp -r ${pwd()}/source/DevOpsProject ${config.DestinationFile}"
                 sh "cp -r ${pwd()}/scripts/Back/Dockerfile ${config.DestinationFile}/DevOpsProject/DevOpsProject"
                 sh "cp -r ${pwd()}/scripts/Back/docker-compose-back.yml ${config.DestinationFile}"
                 sh "cp -r ${pwd()}/scripts/Back/nginx ${config.DestinationFile}"
                 sh "cp -r ${pwd()}/source/DevOpsFront/package.json ${config.DestinationFile}"
                 sh "cp -r ${pwd()}/source/DevOpsFront/package-lock.json ${config.DestinationFile}"

                 sh "ls -la /var/jenkins_home/workspace/aoso/DevOps/backend/nginx "
                 sh "ls -la ${config.DestinationFile} "
            }
            stage('location of docker-compose') 
                {  
                    sh "${config.dockerComposeLocation} -f ${config.DestinationFile}/docker-compose-back.yml up -d"
                    echo "Buid Image with docker-compose "
                    //echo "${config.dockerfileLocation}",
                }
            stage('GetUserJenkins') 
                {  
                    wrap([$class: 'BuildUser']) {
                    def user = env.BUILD_USER_ID
                    echo "${user}"
                    //def list = ['AmaniGHADDAB']
                    echo "${config.devValidator}"
                    if ("${config.devValidator}".contains("${user}"))
                    {
                        echo"Validate"
                    }
                    else
                    {
                           
                        //slackSend color: 'danger', channel: '#devops', message: "<${currentBuild.absoluteUrl}|Server build ${env.BUILD_NUMBER}> failed to deploy build "
	                       echo"Don't have access"
                    }
                 }
                }
               
            
    }

}