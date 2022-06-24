def call (Map config)
{
    node
        { 
               stage ('copy all file from BACK')
            {    
                 sh "ls -la ${pwd()}"
                 sh "ls -la ${pwd()}/source"
                 sh "mkdir -p /srv/aoso/DevOps/backend "
                 sh "ls -a /srv/aoso/DevOps/backend/"
                //sh "ls -a ${config.DestinationFolder1}"
                //sh "ls -a ${config.DestinationFolder2}"
                //sh "ls -a ${config.DestinationFile}"
                 //sh "rm -rf ${config.DestinationFile}"
                 //sh "cp -r ${pwd()}/source/DevOpsProject ${config.DestinationFile}"
                 //sh "rm -rf ${config.DestinationFile}/Dockerfile"
                 sh "cp -r ${config.DestinationFolder} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
                 sh "ls -la ${config.DestinationProject} "
                 sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.elasticsearch} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.dockerComposeNexus} ${config.DestinationFile}"
                 sh "ls -la ${config.DestinationNginx} "
                //h "ls -la /srv/aoso/DevOps/backend/nexus"
                 sh "ls -la ${config.DestinationFile} "
                 
            }
           

        }
           
}