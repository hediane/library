def call (Map config) {
    node
        {
        stage('CREATING OUR IMAGE') {
            sh "mkdir -p ${config.DestinationFolder} "
            sh "cp -r ${config.source} ${config.DestinationFolder}"
            sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationProject}"
            sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFolder}"
            sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFolder}"
            sh "cp -r ${config.nunit} ${config.DestinationFolder}"
            sh "cp -r ${config.projectTest} ${config.DestinationFolder}"
            dir("${config.DestinationProject}")
                            {
                dockerImage = docker.build "${config.ImageBuild}" + ":${config.ImageVersion}"
                            }
            dir("${config.DestinationNginx}")
                            {
                dockerImagenginx = docker.build "${config.ImagenginxBuild}" + ":${config.ImageVersion}"
                            }
                }


                
        stage('PUSH IMAGE IN NEXUS') {
            docker.withRegistry( "${config.nexusRepository}", "${config.credentiel_id}" ) {
                dockerImage.push()
                dockerImagenginx.push()
            }
        }
        }
}
