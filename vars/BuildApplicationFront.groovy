
def call (Map config) {
    node
        {
        stage ('BUILD PROJECT ')
            {
                            
                    sh "mkdir -p ${config.DestinationFolder} "
                    sh "cp -r ${config.source} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFolder}"
                    sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFolder}"

            dir("${config.source}")
               {
               
                    sh'ng build'
               }
            }
        }
}
