def call (Map config)
{
    node
        { 
               stage ('COPY all file from FRONT')
            {    
                sh "ls -la ${pwd()}"
                sh "ls -la ${pwd()}/source"
                sh "mkdir -p /srv/aoso/DevOps/frontend "
                sh "cp -r ${config.DestinationFolder} ${config.DestinationFile}"
                sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
                sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFile}"
                sh "ls -a ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFile}"
                 //sh "cp -r ${pwd()}${config.elasticsearch} ${config.DestinationFile}"
                 sh "ls -la ${config.DestinationNginx} "
                 sh "ls -la ${config.DestinationFile} "
            }
           

        }
           
}