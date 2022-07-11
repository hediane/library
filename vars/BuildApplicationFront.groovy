
def call (Map config) {
    pipeline
        {
        agent any
        stages{
            stage ('BUILD PROJECT ')
                {
                    steps
                    {            
                        sh "mkdir -p ${config.DestinationFolder} "
                        sh "cp -r ${config.source} ${config.DestinationFolder}"
                        sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFolder}"
                        sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFolder}"
                        sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFolder}"

                /*dir("${config.source}")
                {
                
                        sh'ng build'
                }*/
                      }
            }
        }
        }
}
