
def call (Map config) {
    node
        {
        stage ('BUILD PROJECT ')
            {
            sh "mkdir -p ${config.DestinationFolder} "
            sh "cp -r ${config.source} ${config.DestinationFolder}"
            sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
            sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFolder}"
            sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFolder}"
            sh "cp -r ${config.nunit} ${config.DestinationFolder}"
            sh "cp -r ${config.projectTest} ${config.DestinationFolder}"

            dir("${config.DestinationProject}")
               {
                sh'dotnet restore'
                sh'dotnet build'
               }
            }
        }
}
