def call (Map config)
{
    node
        { 
               stage ('copy all file from BACK')
            {    
                 sh "ls -la ${pwd()}"
                 sh "ls -la ${pwd()}/source"
                 sh "ls -la ${pwd()}/${config.BackPath}"
                 //sh "cd /var/jenkins_home/workspace/"
                 sh "mkdir -p /srv/aoso/DevOps/backend "
                 //sh "rm -rf /var/jenkins_home/workspace/aoso/DevOps/back"
                sh "ls -a ${config.DestinationFolder1}"
                sh "ls -a ${config.DestinationFolder2}"
                sh "ls -a ${config.DestinationFile}"
                 //sh "rm -rf ${config.DestinationFile}"
                 //sh "cp -r ${pwd()}/source/DevOpsProject ${config.DestinationFile}"
                 //sh "rm -rf ${config.DestinationFile}/Dockerfile"
                 sh "cp -r ${pwd()}${config.BackPath} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFile}${config.ProjectName}"
                 sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.elasticsearch} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.dockerComposeSonarQube} ${config.dockerComposeSonarQubeDestintion}"

                 sh "ls -la ${config.DestinationFile}/nginx "
                 sh "ls -la ${config.DestinationFile} "
            }
           

        }
           
}