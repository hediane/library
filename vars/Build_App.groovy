
def call (Map config) {
    node
        {
        stage ('BUILD PROJECT ')
            {
            sh 'mkdir -p /srv/aoso/DevOps/backend '
            sh "cp -r ${config.DestinationFolder} ${config.DestinationFile}"
            sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
            sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFile}"
            sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFile}"
            sh "cp -r ${config.test} ${config.DestinationFile}"
                sh "cp -r ${config.test2} ${config.DestinationFile}"

            dir("${config.DestinationProject}")
               {
                sh'dotnet restore'
                sh'dotnet build'
               }
            }
        }
}
