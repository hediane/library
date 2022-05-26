def call (Map config)
{
    node
        { 
            stage('check source scmurl')
            {
                echo "checking out the source scmurl "
                echo "${config.scmurl}"
            }
           stage("Checkout") {
            checkout([$class: 'GitSCM', 
            branches: [[name: 'refs/heads/main']], 
            userRemoteConfigs: [[
                //refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                url:"${config.scmurl}"]]
                //url: 'https://github.com/hediane/teeeeeest.git']]
        ])
         }
            /*stage('location of dockerfile') 
                {  
                    sh "docker build -t teeeeeest -f ${config.dockerfileLocation} ."
                    echo "checking out the source dockerfile "
                    //echo "${config.dockerfileLocation}",
                }*/
            stage ('copy all file from FRONT')
            {    
                 sh "ls -la ${pwd()}"
                 sh "ls -la ${pwd()}/source"
                 sh "ls -la ${pwd()}/source/DevOpsFront/"
                 //sh "cd /var/jenkins_home/workspace/"
                 sh "mkdir -p ${config.DestinationFileFront} "
                 //sh "rm -rf /var/jenkins_home/workspace/aoso/DevOps/back"
                 sh "ls -a /var/jenkins_home/workspace/aoso/"
                 sh "ls -a /var/jenkins_home/workspace/aoso/DevOps"
                 sh "ls -a ${config.DestinationFileFront}"
                 //sh "rm -rf ${config.DestinationFile}"
                 //sh "cp -r ${pwd()}/source/DevOpsProject ${config.DestinationFile}"
                 //sh "rm -rf ${config.DestinationFileFront}/Dockerfile"
                 sh "cp -r ${pwd()}/source/DevOpsFront ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}/scripts/Front/Dockerfile ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}/scripts/Front/docker-compose-front.yml ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}/scripts/Front/nginx ${config.DestinationFileFront}"
                  sh "cp -r ${pwd()}/source/DevOpsFront/package.json ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}/source/DevOpsFront/package-lock.json ${config.DestinationFileFront}"
                 sh "ls -la ${config.DestinationFileFront} "
            }
            stage('location of docker-compose') 
                {  
                    sh "${config.dockerComposeLocation} -f ${config.DestinationFileFront}/docker-compose-front.yml up -d"
                    echo "Build Image with docker-compose "
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